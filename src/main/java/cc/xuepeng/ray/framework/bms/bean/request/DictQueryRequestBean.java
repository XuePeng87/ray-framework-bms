package cc.xuepeng.ray.framework.bms.bean.request;

import cc.xuepeng.ray.framework.core.util.entity.page.PageParam;
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
public class DictQueryRequestBean extends BaseRequestBean {

    /**
     * 字典类型。
     */
    @NotNull
    private Long dictType;

    /**
     * 字典标签。
     */
    private String dictCode;

    /**
     * 字典键值。
     */
    private String dictValue;

    /**
     * 是否默认。
     */
    private Boolean dictDefault;

    /**
     * 分页信息。
     */
    private PageParam page;

}
