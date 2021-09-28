package cc.xuepeng.ray.framework.bms.bean.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 创建和修改部门的请求参数对象。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class DeptRequestBean extends BaseRequestBean {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String deptName;

    /**
     * 父部门主键
     */
    private Long deptPid;

    /**
     * 电话
     */
    private String deptPhoneNumber;

    /**
     * 级别
     */
    private Integer deptLevel;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 备注
     */
    private String deptRemark;

    /**
     * 是否可用
     */
    private Boolean deptAvailabled;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String modifyUser;

}
