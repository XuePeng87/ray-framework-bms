package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 创建或修改按钮的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ButtonRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 菜单主键。
     */
    @NotNull
    private Long menuId;
    /**
     * 名称。
     */
    @NotNull
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
