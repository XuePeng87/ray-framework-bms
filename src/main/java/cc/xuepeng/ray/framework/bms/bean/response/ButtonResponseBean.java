package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 按钮管理的响应实体。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class ButtonResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 菜单主键。
     */
    private String menuId;
    /**
     * 名称。
     */
    private String buttonTitle;
    /**
     * 图标。
     */
    private String buttonIcon;
    /**
     * 备注。
     */
    private String buttonRemark;

}
