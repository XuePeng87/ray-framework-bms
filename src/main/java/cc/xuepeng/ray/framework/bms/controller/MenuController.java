package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.MenuRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.MenuResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysMenu;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单管理的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/menus")
@Slf4j
public class MenuController extends BaseController {

    /**
     * 创建菜单。
     *
     * @param menuRequestBean 创建菜单的请求参数。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "菜单管理", description = "创建菜单")
    public HttpResult<Boolean> create(@Valid @RequestBody final MenuRequestBean menuRequestBean) {
        if (sysMenuService.create(BeanUtil.objToObj(menuRequestBean, SysMenu.class))) {
            return DefaultHttpResultFactory.success("创建菜单成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建菜单失败。", Boolean.FALSE);
    }

    /**
     * 修改菜单。
     *
     * @param menuRequestBean 修改菜单的请求参数。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "菜单管理", description = "修改菜单")
    public HttpResult<Boolean> update(@Valid @RequestBody final MenuRequestBean menuRequestBean) {
        if (sysMenuService.update(BeanUtil.objToObj(menuRequestBean, SysMenu.class))) {
            return DefaultHttpResultFactory.success("修改菜单成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改菜单失败。", Boolean.FALSE);
    }

    /**
     * 根据主键删除菜单。
     *
     * @param id 菜单主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @Operation(module = "菜单管理", description = "删除菜单")
    public HttpResult<Boolean> deleteById(@NotNull @PathVariable(value = "id") final Long id) {
        if (sysMenuService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除菜单成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除菜单失败。", Boolean.FALSE);
    }

    /**
     * @return 查询全部菜单。
     */
    @GetMapping("/v1")
    public HttpResult<List<MenuResponseBean>> findAll() {
        final List<SysMenu> sysMenus = sysMenuService.findAll();
        return DefaultHttpResultFactory.success("查询全部菜单成功。",
                BeanUtil.listObjToListObj(sysMenus, MenuResponseBean.class));
    }

    /**
     * 自动装配菜单管理的业务处理接口。
     *
     * @param sysMenuService 菜单管理的业务处理接口。
     */
    @Autowired
    public void setSysMenuService(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 菜单管理的业务处理接口。
     */
    private SysMenuService sysMenuService;

}
