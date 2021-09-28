package cc.xuepeng.ray.framework.bms.bean.request;

import cc.xuepeng.ray.framework.core.util.entity.page.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 查询操作日志参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OpLogQueryRequestBean extends BaseRequestBean {

    private static final long serialVersionUID = 1L;

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
     * 开始时间。
     */
    private LocalDateTime startCreateTime;
    /**
     * 结束时间。
     */
    private LocalDateTime endCreateTime;
    /**
     * 分页信息。
     */
    private PageParam page;

}
