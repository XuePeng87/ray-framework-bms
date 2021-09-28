package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * BMS网关的请求参数父类。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class BaseRequestBean implements Serializable {

    /**
     * 创建人。
     */
    private String createUser;
    /**
     * 修改人。
     */
    private String modifyUser;

}
