package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 验证数据是否存在的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ExistsRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @NotNull
    private Long id;
    /**
     * 要验证的内容。
     */
    @NotNull
    private String context;

}
