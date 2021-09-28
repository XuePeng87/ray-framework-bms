package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志的响应对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class OperationLogResponseBean implements Serializable {

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
     * 创建时间。
     */
    private LocalDateTime createTime;

}
