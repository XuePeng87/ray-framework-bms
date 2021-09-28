package cc.xuepeng.ray.framework.bms.bean.request;

import cc.xuepeng.ray.framework.sdk.mgt.enums.FunctionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 保存角色下的功能的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SaveRoleFunctionRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    @NotNull
    private Long id;
    /**
     * 功能集合。
     */
    @NotNull
    @Valid
    private List<FunctionRequestBean> functions;

    /**
     * 功能请求对象。
     */
    @Data
    static class FunctionRequestBean implements Serializable {

        /**
         * 功能主键。
         */
        @NotNull
        private Long id;
        /**
         * 功能类型。
         */
        @NotNull
        private FunctionType functionType;

    }

}
