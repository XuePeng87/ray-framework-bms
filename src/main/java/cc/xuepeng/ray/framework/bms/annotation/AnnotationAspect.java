package cc.xuepeng.ray.framework.bms.annotation;

import cc.xuepeng.ray.framework.bms.bean.request.BaseRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.OpLogContentRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.OpLogRequestBean;
import cc.xuepeng.ray.framework.bms.controller.BaseController;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.web.ip.IPUtil;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysOpLog;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysOpLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前登录用户的信息，并写入请求参数中。
 *
 * @author xuepeng
 */
@Component
@Aspect
@Slf4j
public class AnnotationAspect {

    /**
     * 设置本次操作的创建人
     */
    @Pointcut("@annotation(cc.xuepeng.ray.framework.bms.annotation.CreateUser)")
    private void createUser() {
        // 创建之前设置创建人
    }

    /**
     * 设置本次操作的创建人。
     *
     * @param joinPoint 连接点。
     */
    @Before("createUser()")
    public void beforeCreate(final JoinPoint joinPoint) {
        for (Object requestBean : joinPoint.getArgs()) {
            if (requestBean instanceof BaseRequestBean) {
                // 获取当前登录人
                final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
                final BaseRequestBean gatewayBaseRequestBean = (BaseRequestBean) requestBean;
                gatewayBaseRequestBean.setCreateUser(currentUser);
                gatewayBaseRequestBean.setModifyUser(currentUser);
            }
        }
    }

    /**
     * 设置本次操作的修改人。
     */
    @Pointcut("@annotation(cc.xuepeng.ray.framework.bms.annotation.ModifyUser)")
    private void modifyUser() {
        // 修改之前设置修改人
    }

    /**
     * 设置本次操作的创建人。
     *
     * @param joinPoint 连接点。
     */
    @Before("modifyUser()")
    public void beforeModify(JoinPoint joinPoint) {
        for (Object requestBean : joinPoint.getArgs()) {
            if (requestBean instanceof BaseRequestBean) {
                final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
                final BaseRequestBean gatewayBaseRequestBean = (BaseRequestBean) requestBean;
                gatewayBaseRequestBean.setModifyUser(currentUser);
            }
        }
    }

    /**
     * 设置本次操作的修改人。
     */
    @Pointcut("@annotation(cc.xuepeng.ray.framework.bms.annotation.Operation)")
    private void operation() {
        // 修改之前设置修改人
    }

    /**
     * 设置本次操作的创建人。
     *
     * @param joinPoint 连接点。
     */
    @Before("operation()")
    public void beforeOperation(JoinPoint joinPoint) {
        Object controller = joinPoint.getTarget();
        if (controller instanceof BaseController) {
            // 创建日志操作对象
            final OpLogRequestBean opLogRequestBean = new OpLogRequestBean();
            // 设置操作人
            final HttpServletRequest req = ((BaseController) controller).getHttpServletRequest();
            opLogRequestBean.setAccount(SecurityContextHolder.getContext().getAuthentication().getName());
            // 设置日志信息
            final Operation operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Operation.class);
            opLogRequestBean.setModule(operation.module());
            opLogRequestBean.setDescription(operation.description());
            opLogRequestBean.setSrcIp(IPUtil.getRealIpAddress(req));
            // 设置操作日志详情
            OpLogContentRequestBean opLogContentRequestBean = new OpLogContentRequestBean();
            StringBuilder sb = new StringBuilder();
            if (joinPoint.getArgs().length > 0) {
                sb.append("{\"data\":").append(BeanUtil.getObjToStr(joinPoint.getArgs()[0])).append("}");
            }
            opLogContentRequestBean.setContent(sb.toString());
            opLogRequestBean.setSysOpLogContent(opLogContentRequestBean);
            try {
                sysOpLogService.create(BeanUtil.objToObj(opLogRequestBean, SysOpLog.class));
            } catch (IllegalArgumentException e) {
                log.error("保存操作日志时异常，原因为：{}", e.getMessage());
            }
        }
    }

    /**
     * 自动装配日志操作的业务处理接口。
     *
     * @param sysOpLogService 日志操作的业务处理接口。
     */
    @Autowired
    public void setSysOpLogService(SysOpLogService sysOpLogService) {
        this.sysOpLogService = sysOpLogService;
    }

    /**
     * 日志操作的业务处理接口。
     */
    private SysOpLogService sysOpLogService;

}
