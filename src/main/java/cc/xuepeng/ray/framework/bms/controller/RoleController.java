package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.*;
import cc.xuepeng.ray.framework.bms.bean.response.RoleFunctionsResponseBean;
import cc.xuepeng.ray.framework.bms.bean.response.RoleResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.core.util.entity.page.PageResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.Function;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysRole;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysRoleFunctions;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色管理的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/roles")
@Slf4j
public class RoleController extends BaseController {

    /**
     * 创建角色。
     *
     * @param roleRequestBean 创建角色的请求参数。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "角色管理", description = "创建角色")
    public HttpResult<Boolean> create(@Valid @RequestBody final RoleRequestBean roleRequestBean) {
        if (sysRoleService.create(BeanUtil.objToObj(roleRequestBean, SysRole.class))) {
            return DefaultHttpResultFactory.success("保存角色成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("保存角色失败。", Boolean.FALSE);
    }

    /**
     * 修改角色。
     *
     * @param roleRequestBean 修改角色的请求参数。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "角色管理", description = "修改角色")
    public HttpResult<Boolean> update(@Valid @RequestBody final RoleRequestBean roleRequestBean) {
        if (sysRoleService.update(BeanUtil.objToObj(roleRequestBean, SysRole.class))) {
            return DefaultHttpResultFactory.success("修改角色成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改角色失败。", Boolean.FALSE);
    }

    /**
     * 根据主键删除角色。
     *
     * @param id 角色主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @Operation(module = "角色管理", description = "删除角色")
    public HttpResult<Boolean> deleteById(@NotNull @PathVariable(value = "id") final Long id) {
        if (sysRoleService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除角色成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除角色失败。", Boolean.FALSE);
    }

    /**
     * 根据主键批量删除角色。
     *
     * @param ids 角色主键集合。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/ids")
    @Operation(module = "角色管理", description = "批量删除角色")
    public HttpResult<Boolean> deleteByIds(@NotNull @RequestBody final List<Long> ids) {
        if (sysRoleService.deleteByIds(ids)) {
            return DefaultHttpResultFactory.success("批量删除角色成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("批量删除角色失败。", Boolean.FALSE);
    }

    /**
     * 保存角色下的用户。
     *
     * @param saveRoleUserRequestBean 保存角色下的用户的请求参数。
     * @return 是否保存成功。
     */
    @PutMapping("/v1/save-role-user")
    @Operation(module = "角色管理", description = "保存角色与人员的关系")
    public HttpResult<Boolean> saveRoleUser(@Valid @RequestBody final SaveRoleUserRequestBean saveRoleUserRequestBean) {
        if (sysRoleService.saveRoleUser(saveRoleUserRequestBean.getId(), saveRoleUserRequestBean.getUserIds())) {
            return DefaultHttpResultFactory.success("保存角色与人员的关系成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("保存角色与人员的关系失败。", Boolean.FALSE);
    }

    /**
     * 保存角色下的功能。
     *
     * @param saveRoleFunctionRequestBean 保存角色下的功能的请求参数。
     * @return 是否保存成功。
     */
    @PutMapping("/v1/save-role-function")
    @Operation(module = "角色管理", description = "保存角色与功能的关系")
    public HttpResult<Boolean> saveRoleFunction(@Valid @RequestBody final SaveRoleFunctionRequestBean saveRoleFunctionRequestBean) {
        final List<Function> functions = BeanUtil.listObjToListObj(saveRoleFunctionRequestBean.getFunctions(), Function.class);
        if (sysRoleService.saveRoleFunction(saveRoleFunctionRequestBean.getId(), functions)) {
            return DefaultHttpResultFactory.success("保存角色与功能的关系成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("保存角色与功能的关系失败。", Boolean.FALSE);
    }

    /**
     * 查询全部角色。
     *
     * @return 分页后的用户信息集合。
     */
    @GetMapping("/v1")
    public HttpResult<List<RoleResponseBean>> findAll() {
        final List<SysRole> roles = sysRoleService.findAll();
        return DefaultHttpResultFactory.success("查询全部角色成功。",
                BeanUtil.listObjToListObj(roles, RoleResponseBean.class));
    }

    /**
     * 根据条件分页查询角色。
     *
     * @param roleQueryRequestBean 查询用户条件的参数对象。
     * @return 分页后的用户信息集合。
     */
    @PostMapping("/v1/conditions")
    public HttpResult<PageResult<RoleResponseBean>> findByConditionAndPage(@RequestBody final RoleQueryRequestBean roleQueryRequestBean) {
        final Page<SysRole> result = sysRoleService.findByPageAndCondition(
                super.pageParamToPage(roleQueryRequestBean.getPage()),
                BeanUtil.objToObj(roleQueryRequestBean, SysRole.class)
        );
        return DefaultHttpResultFactory.success(
                "根据条件分页查询角色成功。",
                pageToPageResult(result, RoleResponseBean.class)
        );
    }

    /**
     * 查询角色下的用户的主键。
     *
     * @param id 主键。
     * @return 角色下的用户的主键。
     */
    @GetMapping("/v1/{id}/users")
    public HttpResult<List<Long>> findRoleUsersById(@NotNull @PathVariable(value = "id") final Long id) {
        return DefaultHttpResultFactory.success("查询角色下的用户的主键成功。",
                sysRoleService.findRoleUsersById(id));
    }

    /**
     * 查询角色下的功能。
     *
     * @param id 主键。
     * @return 角色下功能。
     */
    @GetMapping("/v1/{id}/functions")
    public HttpResult<RoleFunctionsResponseBean> findRoleFunctionsById(@NotNull @PathVariable(value = "id") final Long id) {
        final SysRoleFunctions sysRoleFunctions = sysRoleService.findRoleFunctionsById(id);
        return DefaultHttpResultFactory.success("查询角色下的功能",
                BeanUtil.objToObj(sysRoleFunctions, RoleFunctionsResponseBean.class));
    }


    /**
     * 查询要创建的角色编号是否已存在。
     *
     * @param existsRequestBean 验证是否存在的请求对象。
     * @return 是否已存在。
     */
    @PostMapping("/v1/exists/code")
    public HttpResult<Boolean> codeExists(@Valid @RequestBody final ExistsRequestBean existsRequestBean) {
        final boolean result = sysRoleService.codeExists(existsRequestBean.getId(), existsRequestBean.getContext());
        return DefaultHttpResultFactory.success("查询要创建的角色编号是否已存在。", result);
    }

    /**
     * 保存角色与API的鉴权关系。
     *
     * @param id   角色主键。
     * @param urls API的路径。
     * @return 是否保存成功。
     */
    @PutMapping("/v1/{id}/save-role-api")
    public HttpResult<Boolean> saveRoleApi(
            @PathVariable(value = "id") final Long id,
            @Valid @RequestBody final List<String> urls) {
        // 保存角色与API的鉴权关系
        final boolean result = sysRoleService.saveRoleApi(id, urls);
        if (result) {
            return DefaultHttpResultFactory.success("保存角色与API的鉴权关系成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("保存角色与API的鉴权关系失败。", Boolean.FALSE);
    }

    /**
     * 根据主键查找角色下的API路径。
     *
     * @param id 角色主键。
     * @return 角色下的API路径。
     */
    @GetMapping("/v1/{id}/apis")
    public HttpResult<List<String>> findRoleApiById(@PathVariable(value = "id") final Long id) {
        final List<String> roleApiRelations = sysRoleService.findRoleApiById(id);
        return DefaultHttpResultFactory.success("根据主键查找角色下的API路径。", roleApiRelations);
    }

    /**
     * 查询要创建的角色名称是否已存在。
     *
     * @param existsRequestBean 验证是否存在的请求对象。
     * @return 是否已存在。
     */
    @PostMapping("/v1/exists/name")
    public HttpResult<Boolean> nameExists(@Valid @RequestBody final ExistsRequestBean existsRequestBean) {
        final boolean result = sysRoleService.nameExists(existsRequestBean.getId(), existsRequestBean.getContext());
        return DefaultHttpResultFactory.success("查询要创建的角色名称是否已存在。", result);
    }

    /**
     * 自动装配角色管理的业务处理接67u8口。
     *
     * @param sysRoleService 角色管理的业务处理接口。
     */
    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    /**
     * 角色管理的业务处理接口。
     */
    private SysRoleService sysRoleService;

}
