package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理的响应实体。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class MenuResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 父级主键。
     */
    private Long menuPid;
    /**
     * 标题。
     */
    private String menuTitle;
    /**
     * 英文标题。
     */
    private String menuTitleEn;
    /**
     * 图标。
     */
    private String menuIcon;
    /**
     * 是否缓存。
     */
    private Boolean menuCache;
    /**
     * 路径。
     */
    private String menuPath;
    /**
     * 层级。
     */
    private Short menuLevel;
    /**
     * 排序。
     */
    private Short menuSort;
    /**
     * 备注。
     */
    private String menuRemark;
    /**
     * 子菜单。
     */
    private List<MenuResponseBean> children = new ArrayList<>();
    /**
     * 按钮。
     */
    private List<ButtonResponseBean> buttons = new ArrayList<>();

}