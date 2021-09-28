package cc.xuepeng.ray.framework.bms.bean.request;

import cc.xuepeng.ray.framework.sdk.mgt.enums.ForgetPasswordType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 忘记密码请求对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserRetrievePasswordRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 联系方式。
     */
    private String contact;
    /**
     * 找回方式。
     */
    private ForgetPasswordType type;
    /**
     * 新密码。
     */
    private String password;
    /**
     * 验证码。
     */
    private String code;

}
