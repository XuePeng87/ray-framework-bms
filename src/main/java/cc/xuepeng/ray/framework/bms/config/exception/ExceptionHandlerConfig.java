package cc.xuepeng.ray.framework.bms.config.exception;

import cc.xuepeng.ray.framework.bms.exception.JigsawVerificationExpiredException;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.sdk.mgt.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台管理微服务的异常统一处理类。
 *
 * @author xuepeng
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerConfig {

    /**
     * UserAuthenticationException的处理方法。
     *
     * @param e UserAuthenticationException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysUserAuthenticationException.class)
    @ResponseBody
    public HttpResult<String> sysUserAuthenticationExceptionHandler(SysUserAuthenticationException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("用户鉴权失败。", e.getMessage());
    }

    /**
     * UserAccountDuplicateException的处理方法。
     *
     * @param e UserAccountDuplicateException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysUserAccountDuplicateException.class)
    @ResponseBody
    public HttpResult<String> sysUserAccountDuplicateExceptionHandler(SysUserAccountDuplicateException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("用户账号已被使用。", e.getMessage());
    }

    /**
     * UserOldSecretIncorrectException的处理方法。
     *
     * @param e UserOldSecretIncorrectException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysUserOldSecretIncorrectException.class)
    @ResponseBody
    public HttpResult<String> sysUserOldSecretIncorrectExceptionHandler(SysUserOldSecretIncorrectException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("输入的旧密码不正确", e.getMessage());
    }

    /**
     * UserNotFoundException的处理方法。
     *
     * @param e UserNotFoundException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysUserNotFoundException.class)
    @ResponseBody
    public HttpResult<String> sysUserNotFoundExceptionHandler(SysUserNotFoundException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("要查找的用户不存在。", e.getMessage());
    }

    /**
     * MenuCannotBeDeletedException的处理方法。
     *
     * @param e MenuCannotBeDeletedException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysMenuCannotBeDeletedException.class)
    @ResponseBody
    public HttpResult<String> sysMenuCannotBeDeletedExceptionHandler(SysMenuCannotBeDeletedException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("菜单不能被删除", e.getMessage());
    }

    /**
     * DepartmentCannotBeDeletedException的处理方法。
     *
     * @param e DepartmentCannotBeDeletedException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysDeptCannotBeDeletedException.class)
    @ResponseBody
    public HttpResult<String> departmentCannotBeDeletedExceptionHandler(SysDeptCannotBeDeletedException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("部门不能被删除", e.getMessage());
    }

    /**
     * RoleCannotBeDeletedException的处理方法。
     *
     * @param e RoleCannotBeDeletedException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SysRoleCannotBeDeletedException.class)
    @ResponseBody
    public HttpResult<String> roleCannotBeDeletedExceptionHandler(SysRoleCannotBeDeletedException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("角色不能被删除", e.getMessage());
    }

    /**
     * SendingEmailException的处理方法。
     *
     * @param e SendingEmailException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = SendingEmailException.class)
    @ResponseBody
    public HttpResult<String> passwordSendingEmailExceptionHandler(SendingEmailException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("忘记密码发送邮件失败。", e.getMessage());
    }

    /**
     * RepeatSendingVerificationCodeException的处理方法。
     *
     * @param e PinCodeIntervalException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = PinCodeIntervalException.class)
    @ResponseBody
    public HttpResult<String> repeatSendingVerificationCodeExceptionHandler(PinCodeIntervalException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("每分钟只可发送一次验证码。", e.getMessage());
    }

    /**
     * VerificationCodeErrorException的处理方法。
     *
     * @param e PinCodeErrorException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = PinCodeErrorException.class)
    @ResponseBody
    public HttpResult<String> verificationCodeErrorExceptionHandler(PinCodeErrorException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("验证码错误。", e.getMessage());
    }

    /**
     * VerificationCodeTimedOutException的处理方法。
     *
     * @param e PinCodeTimeOutException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = PinCodeTimeOutException.class)
    @ResponseBody
    public HttpResult<Boolean> verificationCodeTimedOutExceptionHandler(PinCodeTimeOutException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("验证码超时。", Boolean.FALSE);
    }

    /**
     * JigsawVerificationExpiredException的处理方法。
     *
     * @param e JigsawVerificationExpiredException。
     * @return 错误信息。
     */
    @ExceptionHandler(value = JigsawVerificationExpiredException.class)
    @ResponseBody
    public HttpResult<Boolean> jigsawVerificationExpiredExceptionExceptionHandler(JigsawVerificationExpiredException e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.success("验证码超时。", Boolean.FALSE);
    }

    /**
     * Exception的处理方法。
     *
     * @param e Exception。
     * @return 错误信息。
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HttpResult<String> exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return DefaultHttpResultFactory.fail("业务异常：" + e.getMessage(), e.getMessage());
    }

}
