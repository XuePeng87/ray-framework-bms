package cc.xuepeng.ray.framework.bms.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门管理的响应实体。
 *
 * @author xuepeng
 */
@Data
@ToString
@EqualsAndHashCode
public class DeptResponseBean implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 子部门。
     */
    private List<DeptResponseBean> children = new ArrayList<>();

}
