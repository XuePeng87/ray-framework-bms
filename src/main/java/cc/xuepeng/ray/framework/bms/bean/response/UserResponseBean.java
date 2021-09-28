package cc.xuepeng.ray.framework.bms.bean.response;

import cc.xuepeng.ray.framework.bms.bean.request.RoleRequestBean;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理的响应对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class UserResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 账号。
     */
    private String userAccount;
    /**
     * 姓名。
     */
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
     * 角色。
     */
    private List<RoleRequestBean> roles = new ArrayList<>();
    /**
     * 菜单。
     */
    private List<UserMenuResponseBean> menus = new ArrayList<>();
    /**
     * 部门。
     */
    private List<DeptResponseBean> departments = new ArrayList<>();
    /**
     * 令牌。
     */
    private String token;

}
