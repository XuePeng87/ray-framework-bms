package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.DeptRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.SaveDeptUserRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.DeptResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysDept;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 部门管理的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/depts")
@Slf4j
public class DeptController extends BaseController {

    /**
     * 创建部门。
     *
     * @param departmentRequestBean 创建部门的请求参数。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "部门管理", description = "创建部门")
    public HttpResult<Boolean> create(@Valid @RequestBody final DeptRequestBean departmentRequestBean) {
        if (sysDeptService.create(BeanUtil.objToObj(departmentRequestBean, SysDept.class))) {
            return DefaultHttpResultFactory.success("创建部门成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建部门失败。", Boolean.FALSE);
    }

    /**
     * 修改部门。
     *
     * @param departmentRequestBean 修改部门的请求参数。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "部门管理", description = "修改部门")
    public HttpResult<Boolean> update(@Valid @RequestBody final DeptRequestBean departmentRequestBean) {
        if (sysDeptService.update(BeanUtil.objToObj(departmentRequestBean, SysDept.class))) {
            return DefaultHttpResultFactory.success("修改部门成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改部门失败。", Boolean.FALSE);
    }

    /**
     * 删除部门。
     *
     * @param id 部门主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @ModifyUser
    @Operation(module = "部门管理", description = "删除部门")
    public HttpResult<Boolean> deleteById(@NotNull @PathVariable(value = "id") final Long id) {
        if (sysDeptService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除部门成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除部门失败。", Boolean.FALSE);
    }

    /**
     * 根据主键查询部门下的用户。
     *
     * @param id 部门主键。
     * @return 部门下的用户。
     */
    @GetMapping("/v1/{id}/users")
    public HttpResult<List<Long>> findDeptUsersById(@NotNull @PathVariable(value = "id") final Long id) {
        return DefaultHttpResultFactory.success("查询部门下的用户的主键成功。",
                sysDeptService.findDeptUsersById(id));
    }

    /**
     * 保存部门下的用户。
     *
     * @param saveDepartmentUserRequestBean 保存部门下的用户的请求参数。
     * @return 是否保存成功。
     */
    @PutMapping("/v1/save-dept-user")
    @Operation(module = "部门管理", description = "保存部门与人员的关系")
    public HttpResult<Boolean> saveDepartmentUser(@Valid @RequestBody final SaveDeptUserRequestBean saveDepartmentUserRequestBean) {
        if (sysDeptService.saveDeptUser(saveDepartmentUserRequestBean.getId(), saveDepartmentUserRequestBean.getUserIds())) {
            return DefaultHttpResultFactory.success("保存部门与人员的关系成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("保存部门与人员的关系失败。", Boolean.FALSE);
    }

    /**
     * @return 查询全部部门。
     */
    @GetMapping("/v1")
    public HttpResult<List<DeptResponseBean>> findAll() {
        final List<SysDept> departments = sysDeptService.findAll();
        return DefaultHttpResultFactory.success("查询全部部门成功。",
                BeanUtil.listObjToListObj(departments, DeptResponseBean.class));
    }

    /**
     * 自动装配部门管理服务接口。
     *
     * @param sysDeptService 部门管理服务接口。
     */
    @Autowired
    public void setSysDeptService(SysDeptService sysDeptService) {
        this.sysDeptService = sysDeptService;
    }

    /**
     * 部门管理服务接口。
     */
    private SysDeptService sysDeptService;

}
