package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.ButtonRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.ButtonResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysButton;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysButtonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 按钮管理的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/buttons")
@Slf4j
public class ButtonController extends BaseController {

    /**
     * 创建按钮。
     *
     * @param buttonRequestBean 创建按钮的请求参数。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "按钮管理", description = "创建按钮")
    public HttpResult<Boolean> create(@Valid @RequestBody final ButtonRequestBean buttonRequestBean) {
        if (sysButtonService.create(BeanUtil.objToObj(buttonRequestBean, SysButton.class))) {
            return DefaultHttpResultFactory.success("创建按钮成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建按钮失败。", Boolean.FALSE);
    }

    /**
     * 修改按钮。
     *
     * @param buttonRequestBean 修改按钮的请求参数。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "按钮管理", description = "修改按钮")
    public HttpResult<Boolean> update(@Valid @RequestBody final ButtonRequestBean buttonRequestBean) {
        if (sysButtonService.update(BeanUtil.objToObj(buttonRequestBean, SysButton.class))) {
            return DefaultHttpResultFactory.success("修改按钮成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建按钮失败。", Boolean.FALSE);
    }

    /**
     * 根据主键删除按钮。
     *
     * @param id 按钮主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @Operation(module = "按钮管理", description = "删除按钮")
    public HttpResult<Boolean> deleteById(@PathVariable(value = "id") final Long id) {
        if (sysButtonService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除按钮成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除按钮失败。", Boolean.FALSE);
    }

    /**
     * 根据菜单主键查询按钮。
     *
     * @param menuId 菜单主键。
     * @return 按钮集合。
     */
    @GetMapping("/v1/menus/{id}")
    public HttpResult<List<ButtonResponseBean>> findByMenuId(@PathVariable(value = "id") final Long menuId) {
        final List<SysButton> sysButtons = sysButtonService.findByMenuId(menuId);
        return DefaultHttpResultFactory.success("根据菜单主键查询按钮成功。",
                BeanUtil.listObjToListObj(sysButtons, ButtonResponseBean.class));
    }

    /**
     * 自动装配按钮管理的业务处理接口。
     *
     * @param sysButtonService 按钮管理的业务处理接口。
     */
    @Autowired
    public void setSysButtonService(SysButtonService sysButtonService) {
        this.sysButtonService = sysButtonService;
    }

    /**
     * 按钮管理的业务处理接口。
     */
    private SysButtonService sysButtonService;

}
