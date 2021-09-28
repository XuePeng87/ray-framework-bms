package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.bean.request.UserRetrievePasswordRequestBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysUserRetrievePassword;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysUserRetrievePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 找回密码控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/forget-password")
public class UserRetrievePasswordController {

    /**
     * 找回密码操作时，验证联系方式是否存在。
     *
     * @param userRetrievePasswordRequestBean 找回密码请求对象。
     * @return 是否验证成功。
     */
    @PostMapping("/v1/exists")
    public HttpResult<Boolean> emailOrPhoneNumberExist(@RequestBody final UserRetrievePasswordRequestBean userRetrievePasswordRequestBean) {
        if (sysUserRetrievePasswordService.checkInfomation(
                BeanUtil.objToObj(userRetrievePasswordRequestBean, SysUserRetrievePassword.class)
        )) {
            return DefaultHttpResultFactory.success("信息验证通过。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.success("信息验证未通过。", Boolean.FALSE);
    }

    /**
     * 找回密码操作时，发送验证码。
     *
     * @param userRetrievePasswordRequestBean 找回密码请求对象。
     * @return 是否发送成功。
     */
    @PostMapping("/v1/send")
    public HttpResult<String> sendVerificationCode(@RequestBody final UserRetrievePasswordRequestBean userRetrievePasswordRequestBean) {
        sysUserRetrievePasswordService.sendVerificationCode(
                BeanUtil.objToObj(userRetrievePasswordRequestBean, SysUserRetrievePassword.class)
        );
        return DefaultHttpResultFactory.success("验证码发送成功。", "验证码发送成功。");
    }

    /**
     * 找回密码操作时，重置密码。
     *
     * @param userRetrievePasswordRequestBean 找回密码请求对象。
     * @return 是否重置成功。
     */
    @PutMapping("/v1/update")
    public HttpResult<Boolean> resetPassword(@RequestBody UserRetrievePasswordRequestBean userRetrievePasswordRequestBean) {
        if (sysUserRetrievePasswordService.resetPassword(
                BeanUtil.objToObj(userRetrievePasswordRequestBean, SysUserRetrievePassword.class)
        )) {
            return DefaultHttpResultFactory.success("修改密码成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改密码失败。", Boolean.FALSE);
    }

    /**
     * 找回密码操作时，根据发送次数，判断是否需要弹出验证码。
     *
     * @param userRetrievePasswordRequestBean 找回密码请求对象。
     * @return 是否需要验证码。
     */
    @PostMapping("/v1/need-verify")
    public HttpResult<Boolean> checkSendCount(@RequestBody UserRetrievePasswordRequestBean userRetrievePasswordRequestBean) {
        if (sysUserRetrievePasswordService.isUpperLimit(
                BeanUtil.objToObj(userRetrievePasswordRequestBean, SysUserRetrievePassword.class))
        ) {
            return DefaultHttpResultFactory.success("需要验证码。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.success("无需验证码。", Boolean.FALSE);
    }

    /**
     * 自动装配用户管理的业务处理接口。
     *
     * @param sysUserRetrievePasswordService 用户管理的业务处理接口。
     */
    @Autowired
    public void setSysUserRetrievePasswordService(SysUserRetrievePasswordService sysUserRetrievePasswordService) {
        this.sysUserRetrievePasswordService = sysUserRetrievePasswordService;
    }

    /**
     * 用户管理的业务处理接口。
     */
    private SysUserRetrievePasswordService sysUserRetrievePasswordService;

}
