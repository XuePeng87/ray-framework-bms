package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 操作日志内容的请求参数。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OpLogContentRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 构造函数。
     */
    public OpLogContentRequestBean() {
    }

    /**
     * 构造函数。
     *
     * @param content 详情。
     */
    public OpLogContentRequestBean(String content) {
        this.content = content;
    }

    /**
     * 主键。
     */
    private Long id;
    /**
     * 日志主键。
     */
    private Long logId;
    /**
     * 操作内容。
     */
    private String content;

}
