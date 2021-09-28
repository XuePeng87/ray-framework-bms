package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 创建或修改数据字典的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class DictRequestBean extends BaseRequestBean {

    /**
     * 主键。
     */
    private Long id;

    /**
     * 字典类型。
     */
    @NotNull
    private Long dictType;

    /**
     * 字典标签。
     */
    @NotNull
    private String dictCode;

    /**
     * 字典键值。
     */
    @NotNull
    private String dictValue;

    /**
     * 字典排序。
     */
    @NotNull
    private Integer dictSort;

    /**
     * 是否默认。
     */
    @NotNull
    private Boolean dictDefault;

    /**
     * 备注。
     */
    private String dictRemark;

}
