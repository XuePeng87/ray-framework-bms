package cc.xuepeng.ray.framework.bms.bean.request;

import cc.xuepeng.ray.framework.core.util.entity.page.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 查询用户条件的参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserQueryRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

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
     * 分页信息。
     */
    private PageParam page;

}
