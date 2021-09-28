package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 菜单下的按钮对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class UserMenuButtonResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称。
     */
    private String buttonTitle;

}
