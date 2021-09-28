package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.ExistsRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.UpdateSecretRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.UserQueryRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.UserRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.UserResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.core.util.entity.page.PageResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysUser;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户管理的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController extends BaseController {

    /**
     * 创建用户。
     *
     * @param userRequestBean 创建用户的请求参数。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "用户管理", description = "创建用户")
    public HttpResult<Boolean> create(@Valid @RequestBody final UserRequestBean userRequestBean) {
        if (sysUserService.create(BeanUtil.objToObj(userRequestBean, SysUser.class))) {
            return DefaultHttpResultFactory.success("创建用户成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建用户失败。", Boolean.FALSE);
    }

    /**
     * 修改用户。
     *
     * @param userRequestBean 修改用户的请求参数。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "用户管理", description = "修改用户")
    public HttpResult<Boolean> update(@Valid @RequestBody final UserRequestBean userRequestBean) {
        if (sysUserService.update(BeanUtil.objToObj(userRequestBean, SysUser.class))) {
            return DefaultHttpResultFactory.success("修改用户成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改用户失败。", Boolean.FALSE);
    }

    /**
     * 根据主键删除用户。
     *
     * @param id 用户主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @Operation(module = "用户管理", description = "删除用户")
    public HttpResult<Boolean> deleteById(@NotNull @PathVariable(value = "id") final Long id) {
        if (sysUserService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除用户成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除用户失败。", Boolean.FALSE);
    }

    /**
     * 根据主键批量删除用户。
     *
     * @param ids 用户主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1")
    @Operation(module = "用户管理", description = "批量删除用户")
    public HttpResult<Boolean> deleteByIds(@NotNull @RequestBody final List<Long> ids) {
        if (sysUserService.deleteByIds(ids)) {
            return DefaultHttpResultFactory.success("批量删除用户成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("批量删除用户失败。", Boolean.FALSE);
    }

    /**
     * 修改密码。
     *
     * @param updateSecretRequestBean 修改密码的请求参数。
     * @return 是否修改成功。
     */
    @PutMapping("/v1/secret")
    @Operation(module = "用户管理", description = "修改密码")
    public HttpResult<Boolean> updateSecret(@Valid @RequestBody final UpdateSecretRequestBean updateSecretRequestBean) {
        if (sysUserService.updateSecret(
                updateSecretRequestBean.getId(),
                updateSecretRequestBean.getOldSecret(),
                updateSecretRequestBean.getNewSecret())
        ) {
            return DefaultHttpResultFactory.success("修改密码成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改密码失败。", Boolean.FALSE);
    }

    /**
     * 重置密码。
     *
     * @param id 用户主键。
     * @return 是否重置成功。
     */
    @PutMapping("/v1/{id}/secret/reset")
    @Operation(module = "用户管理", description = "重置密码")
    public HttpResult<Boolean> resetSecret(@PathVariable(value = "id") final Long id) {
        if (sysUserService.resetSecret(id)) {
            return DefaultHttpResultFactory.success("重置密码成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("重置密码失败。", Boolean.FALSE);
    }

    /**
     * 查询要创建的账号是否已存在。
     *
     * @param existsRequestBean 验证是否存在的请求对象。
     * @return 是否已存在。
     */
    @PostMapping("/v1/exists/account")
    public HttpResult<Boolean> accountExists(@Valid @RequestBody final ExistsRequestBean existsRequestBean) {
        final boolean result = sysUserService.accountExists(existsRequestBean.getId(), existsRequestBean.getContext());
        return DefaultHttpResultFactory.success("查询用户账号是否存在。", result);
    }

    /**
     * 查询要创建的电话号码是否已存在。
     *
     * @param existsRequestBean 验证是否存在的请求对象。
     * @return 是否已存在。
     */
    @PostMapping("/v1/exists/phone")
    public HttpResult<Boolean> phoneExists(@Valid @RequestBody final ExistsRequestBean existsRequestBean) {
        final boolean result = sysUserService.phoneExists(existsRequestBean.getId(), existsRequestBean.getContext());
        return DefaultHttpResultFactory.success("查询电话号码是否存在。", result);
    }

    /**
     * 查询要创建的电子邮件是否已存在。
     *
     * @param existsRequestBean 验证是否存在的请求对象。
     * @return 是否已存在。
     */
    @PostMapping("/v1/exists/email")
    public HttpResult<Boolean> emailExists(@Valid @RequestBody final ExistsRequestBean existsRequestBean) {
        final boolean result = sysUserService.emailExists(existsRequestBean.getId(), existsRequestBean.getContext());
        return DefaultHttpResultFactory.success("查询电子邮件是否存在。", result);
    }

    /**
     * 根据账号查询用户信息。
     *
     * @param account 账号。
     * @return 用户信息。
     */
    @GetMapping("/v1/account/{account}")
    public HttpResult<UserResponseBean> findByAccount(@PathVariable(value = "account") final String account) {
        final SysUser sysUser = sysUserService.findByAccount(account);
        return DefaultHttpResultFactory.success("根据账号查询用户信息。",
                BeanUtil.objToObj(sysUser, UserResponseBean.class));
    }

    /**
     * 根据主键查找用户。
     *
     * @param id 用户主键。
     * @return 用户信息。
     */
    @GetMapping("/v1/{id}")
    public HttpResult<UserResponseBean> findById(@NotNull @PathVariable(value = "id") final Long id) {
        final SysUser sysUser = sysUserService.findById(id);
        return DefaultHttpResultFactory.success("根据主键查询用户信息成功。",
                BeanUtil.objToObj(sysUser, UserResponseBean.class));
    }

    /**
     * 根据主键批量查找用户。
     *
     * @param ids 用户主键。
     * @return 用户信息集合。
     */
    @PostMapping("/v1/ids")
    public HttpResult<List<UserResponseBean>> findByIds(@NotNull @RequestBody final List<Long> ids) {
        final List<SysUser> sysUsers = sysUserService.findByIds(ids);
        return DefaultHttpResultFactory.success("根据主键批量查找用户成功。",
                BeanUtil.listObjToListObj(sysUsers, UserResponseBean.class));
    }

    /**
     * 查询全部用户。
     *
     * @return 用户信息集合。
     */
    @GetMapping("/v1")
    public HttpResult<List<UserResponseBean>> findAll() {
        final List<SysUser> sysUsers = sysUserService.findAll();
        return DefaultHttpResultFactory.success("查询全部用户成功。",
                BeanUtil.listObjToListObj(sysUsers, UserResponseBean.class));
    }

    /**
     * @return 查询没有部门的员工。
     */
    @GetMapping("/v1/no-dept/{deptId}")
    public HttpResult<List<UserResponseBean>> findNoDeptUsers(@NotNull @PathVariable(value = "deptId") final Long deptId) {
        final List<SysUser> sysUsers = sysUserService.findNoDepartmentUsers(deptId);
        return DefaultHttpResultFactory.success("查询没有部门的用户成功。",
                BeanUtil.listObjToListObj(sysUsers, UserResponseBean.class));
    }

    /**
     * 根据条件分页查询用户。
     *
     * @param userQueryRequestBean 查询用户条件的参数对象。
     * @return 分页后的用户信息集合。
     */
    @PostMapping("/v1/conditions")
    public HttpResult<PageResult<UserResponseBean>> findByConditionAndPage(@RequestBody final UserQueryRequestBean userQueryRequestBean) {
        final SysUser sysUser = BeanUtil.objToObj(userQueryRequestBean, SysUser.class);
        final Page<SysUser> page = pageParamToPage(userQueryRequestBean.getPage());
        final Page<SysUser> result = sysUserService.findByPageAndCondition(page, sysUser);
        return DefaultHttpResultFactory.success(
                "根据条件分页查询用户成功。",
                pageToPageResult(result, UserResponseBean.class)
        );
    }

    /**
     * 自动装配用户管理的业务处理接口。
     *
     * @param sysUserService 用户管理的业务处理接口。
     */
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 用户管理的业务处理接口。
     */
    private SysUserService sysUserService;

}
