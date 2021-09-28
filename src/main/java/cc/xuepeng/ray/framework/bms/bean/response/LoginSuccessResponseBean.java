package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 登录成功需要返回的数据。
 *
 * @author xuepeng
 */
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginSuccessResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 登录成功的账号。
     */
    private String account;
    /**
     * 访问令牌。
     */
    private String token;

}
