package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.annotation.CreateUser;
import cc.xuepeng.ray.framework.bms.annotation.ModifyUser;
import cc.xuepeng.ray.framework.bms.annotation.Operation;
import cc.xuepeng.ray.framework.bms.bean.request.DictQueryRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.DictRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.DictResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.core.util.entity.page.PageResult;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysDict;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysDictService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 数据字典的控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/dicts")
@Slf4j
public class DictController extends BaseController {

    /**
     * 创建SysDict。
     *
     * @param dictRequestBean SysDict的请求实体类。
     * @return 是否创建成功。
     */
    @PostMapping("/v1")
    @CreateUser
    @Operation(module = "字典管理", description = "创建字典信息")
    public HttpResult<Boolean> create(@Valid @RequestBody final DictRequestBean dictRequestBean) {
        final SysDict sysDict = BeanUtil.objToObj(dictRequestBean, SysDict.class);
        if (sysDictService.create(sysDict)) {
            return DefaultHttpResultFactory.success("创建字典成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("创建字典失败。", Boolean.FALSE);
    }

    /**
     * 修改SysDict。
     *
     * @param dictRequestBean SysDict的请求实体类。
     * @return 是否修改成功。
     */
    @PutMapping("/v1")
    @ModifyUser
    @Operation(module = "字典管理", description = "修改字典信息")
    public HttpResult<Boolean> update(@Valid @RequestBody final DictRequestBean dictRequestBean) {
        final SysDict sysDict = BeanUtil.objToObj(dictRequestBean, SysDict.class);
        if (sysDictService.update(sysDict)) {
            return DefaultHttpResultFactory.success("修改字典成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("修改字典失败。", Boolean.FALSE);
    }

    /**
     * 根据主键删除SysDict。
     *
     * @param id SysDict的主键。
     * @return 是否删除成功。
     */
    @DeleteMapping("/v1/{id}")
    @Operation(module = "字典管理", description = "删除字典信息")
    public HttpResult<Boolean> deleteById(@NotNull @PathVariable(value = "id") final Long id) {
        if (sysDictService.deleteById(id)) {
            return DefaultHttpResultFactory.success("删除字典成功。", Boolean.TRUE);
        }
        return DefaultHttpResultFactory.fail("删除字典失败。", Boolean.FALSE);
    }

    /**
     * 根据条件分页查询SysDict。
     *
     * @param dictQueryRequestBean SysDict的参数对象。
     * @return 分页后的SysDict信息集合。
     */
    @PostMapping("/v1/conditions")
    public HttpResult<PageResult<DictResponseBean>> findByConditionAndPage(@RequestBody final DictQueryRequestBean dictQueryRequestBean) {
        final SysDict sysDict = BeanUtil.objToObj(dictQueryRequestBean, SysDict.class);
        final Page<SysDict> page = pageParamToPage(dictQueryRequestBean.getPage());
        final Page<SysDict> result = sysDictService.findByPageAndCondition(page, sysDict);
        return DefaultHttpResultFactory.success(
                "根据条件分页查询SysDict成功。",
                pageToPageResult(result, DictResponseBean.class)
        );
    }

    /**
     * 自动装配SysDict的业务处理接口。
     *
     * @param sysDictService SysDict的业务处理接口。
     */
    @Autowired
    public void setSysDictService(SysDictService sysDictService) {
        this.sysDictService = sysDictService;
    }

    /**
     * SysDict的业务处理接口。
     */
    private SysDictService sysDictService;

}
