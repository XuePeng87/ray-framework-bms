package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 拼图信息请求信息。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class JigsawRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * x坐标。
     */
    private int x;
    /**
     * 令牌。
     */
    private String token;

}
