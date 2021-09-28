package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.bean.request.OpLogQueryRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.OperationLogResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.core.util.entity.page.PageResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysOpLog;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysOpLogService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/operation-logs")
@Slf4j
public class OpLogController extends BaseController {

    /**
     * 根据条件分页查询操作日志。
     *
     * @param operationLogQueryRequestBean 查询条件。
     * @return 日志信息。
     */
    @PostMapping("/v1/conditions")
    public HttpResult<PageResult<OperationLogResponseBean>> findByConditionAndPage(@RequestBody final OpLogQueryRequestBean operationLogQueryRequestBean) {
        final SysOpLog sysOpLog = BeanUtil.objToObj(operationLogQueryRequestBean, SysOpLog.class);
        final Page<SysOpLog> result = sysOpLogService.findByPageAndCondition(
                pageParamToPage(operationLogQueryRequestBean.getPage()),
                sysOpLog
        );
        return DefaultHttpResultFactory.success(
                "根据条件分页查询操作日志成功。",
                pageToPageResult(result, OperationLogResponseBean.class)
        );
    }

    /**
     * 根据主键查询操作内容。
     *
     * @param id 主键。
     * @return 操作日志内容。
     */
    @GetMapping("/v1/{id}/content")
    public HttpResult<String> findContent(@PathVariable(value = "id") final Long id) {
        final String result = sysOpLogService.findContent(id);
        return DefaultHttpResultFactory.success("根据主键查询操作内容成功。", result);
    }

    /**
     * 设置操作日志服务接口。
     *
     * @param sysOpLogService 操作日志服务接口。
     */
    @Autowired
    public void setSysOpLogService(SysOpLogService sysOpLogService) {
        this.sysOpLogService = sysOpLogService;
    }

    /**
     * 操作日志服务接口。
     */
    private SysOpLogService sysOpLogService;

}
