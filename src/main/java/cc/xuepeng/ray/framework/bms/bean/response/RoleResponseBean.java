package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色管理的响应实体。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class RoleResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 编号。
     */
    private String roleCode;
    /**
     * 名称。
     */
    private String roleName;
    /**
     * 备注。
     */
    private String roleRemark;

}