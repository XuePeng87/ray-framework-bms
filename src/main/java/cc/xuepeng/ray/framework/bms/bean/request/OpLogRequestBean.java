package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 操作日志的请求参数。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OpLogRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键。
     */
    private Long id;
    /**
     * 操作账号。
     */
    private String account;
    /**
     * 模块。
     */
    private String module;
    /**
     * 描述。
     */
    private String description;
    /**
     * 访问IP。
     */
    private String srcIp;
    /**
     * 操作时间。
     */
    private LocalDateTime createTime;
    /**
     * 日志详情。
     */
    private OpLogContentRequestBean sysOpLogContent = new OpLogContentRequestBean();

}
