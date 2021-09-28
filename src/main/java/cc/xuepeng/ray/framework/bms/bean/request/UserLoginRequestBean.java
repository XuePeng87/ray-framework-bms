package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 登录的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserLoginRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 账号。
     */
    @NotNull
    private String userAccount;
    /**
     * 密码。
     */
    @NotNull
    private String userSecret;

}
