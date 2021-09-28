package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 创建或修改菜单的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class MenuRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 父级主键。
     */
    @NotNull
    private Long menuPid;
    /**
     * 标题。
     */
    @NotNull
    private String menuTitle;
    /**
     * 英文标题。
     */
    @NotNull
    private String menuTitleEn;
    /**
     * 图标。
     */
    private String menuIcon;
    /**
     * 是否缓存。
     */
    @NotNull
    private Boolean menuCache;
    /**
     * 路径。
     */
    @NotNull
    private String menuPath;
    /**
     * 层级。
     */
    @NotNull
    private Short menuLevel;
    /**
     * 排序。
     */
    @NotNull
    private Short menuSort;
    /**
     * 备注。
     */
    private String menuRemark;

}
