package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 创建或修改数据字典类型的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class DictTypeRequestBean extends BaseRequestBean {

    /**
     * 主键。
     */
    private Long id;

    /**
     * 字典类型名称
     */
    @NotNull
    private String dictTypeName;

    /**
     * 备注
     */
    private String dictRemark;

}
