package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 拼图信息响应信息。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class JigsawResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 背景图。
     */
    private String byImageBase64;
    /**
     * 拼图。
     */
    private String ckImageBase64;
    /**
     * 拼图y坐标。
     */
    private int y;
    /**
     * 令牌。
     */
    private String token;

}
