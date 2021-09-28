package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建和修改用户的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 账号。
     */
    @NotNull
    private String userAccount;
    /**
     * 姓名。
     */
    @NotNull
    private String userName;
    /**
     * 电话。
     */
    private String userPhone;
    /**
     * 邮箱。
     */
    private String userEmail;
    /**
     * 头像。
     */
    private String userAvator;
    /**
     * 备注。
     */
    private String userRemark;
    /**
     * 角色主键。
     */
    private List<RoleRequestBean> roles = new ArrayList<>();

}
