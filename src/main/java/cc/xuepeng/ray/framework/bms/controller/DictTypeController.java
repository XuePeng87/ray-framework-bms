package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.DictTypeRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.DictTypeResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysDictType;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据字典类型的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/dict-types")
@Slf4j
public class DictTypeController extends BaseController {

    /**
     * 创建SysDictType。
     *
     * @param dictTypeRequestBean SysDictType的请求实体类。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "字典类型管理", description = "创建字典类型信息")
    public HttpResult<Boolean> create(@Valid @RequestBody final DictTypeRequestBean dictTypeRequestBean) {
        final SysDictType sysDictType = BeanUtil.objToObj(dictTypeRequestBean, SysDictType.class);
        if (sysDictTypeService.create(sysDictType)) {
            return DefaultHttpResultFactory.success("创建字典类型成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建字典类型失败。", Boolean.FALSE);
    }

    /**
     * 修改SysDictType。
     *
     * @param dictTypeRequestBean SysDictType的请求实体类。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "字典类型管理", description = "修改字典类型信息")
    public HttpResult<Boolean> update(@Valid @RequestBody final DictTypeRequestBean dictTypeRequestBean) {
        final SysDictType sysDictType = BeanUtil.objToObj(dictTypeRequestBean, SysDictType.class);
        if (sysDictTypeService.update(sysDictType)) {
            return DefaultHttpResultFactory.success("修改字典类型成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改字典类型失败。", Boolean.FALSE);
    }

    /**
     * 根据主键删除SysDictType。
     *
     * @param id SysDictType的主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @Operation(module = "字典类型管理", description = "删除字典类型信息")
    public HttpResult<Boolean> deleteById(@NotNull @PathVariable(value = "id") final Long id) {
        if (sysDictTypeService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除字典类型成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除字典类型失败。", Boolean.FALSE);
    }

    @GetMapping("/v1")
    public HttpResult<List<DictTypeResponseBean>> findAll() {
        final List<SysDictType> sysDictTypes = sysDictTypeService.findAll();
        return DefaultHttpResultFactory.success(
                "查询全部字典类型成功",
                BeanUtil.listObjToListObj(sysDictTypes, DictTypeResponseBean.class)
        );
    }

    /**
     * 自动装配系统字典类型的服务接口。
     *
     * @param sysDictTypeService 系统字典类型的服务接口。
     */
    @Autowired
    public void setSysDictTypeService(SysDictTypeService sysDictTypeService) {
        this.sysDictTypeService = sysDictTypeService;
    }

    /**
     * 系统字典类型的服务接口。
     */
    private SysDictTypeService sysDictTypeService;

}
