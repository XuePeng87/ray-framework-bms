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
public class DictTypeResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;

    /**
     * 字典类型名称
     */
    private String dictTypeName;

    /**
     * 备注
     */
    private String dictRemark;

}
