package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 修改密码的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UpdateSecretRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @NotNull
    private Long id;
    /**
     * 旧密码。
     */
    @NotNull
    private String oldSecret;
    /**
     * 新密码。
     */
    @NotNull
    private String newSecret;

}
