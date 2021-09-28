package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 创建或修改数据字典类型的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class DictResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;

    /**
     * 字典标签。
     */
    private String dictCode;

    /**
     * 字典键值。
     */
    private String dictValue;

    /**
     * 字典排序。
     */
    private Integer dictSort;

    /**
     * 是否默认。
     */
    private Boolean dictDefault;

    /**
     * 备注。
     */
    private String dictRemark;

}
