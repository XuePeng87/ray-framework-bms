package cc.xuepeng.ray.framework.bms.bean.request;

import cc.xuepeng.ray.framework.core.util.entity.page.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 查询角色条件的参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleQueryRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 名称。
     */
    private String roleName;

    /**
     * 分页信息。
     */
    private PageParam page;

}
