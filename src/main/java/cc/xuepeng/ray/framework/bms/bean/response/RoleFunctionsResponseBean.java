package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色下的功能。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class RoleFunctionsResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色下的菜单。
     */
    private List<Long> menus = new ArrayList<>();
    /**
     * 角色下的按钮。
     */
    private List<Long> buttons = new ArrayList<>();

}
