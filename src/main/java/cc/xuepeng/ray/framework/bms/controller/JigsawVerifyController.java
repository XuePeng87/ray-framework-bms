package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.bms.bean.response.JigsawResponseBean;
import cc.xuepeng.ray.framework.bms.service.JigsawVerifyService;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import cc.xuepeng.ray.framework.core.util.jigsaw.Jigsaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拼图验证码控制器。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/jigsaw-verify")
public class JigsawVerifyController {

    /**
     * 创建拼图验证码。
     *
     * @return 拼图验证码信息。
     */
    @PostMapping("/v1/create")
    public HttpResult<JigsawResponseBean> create() {
        Jigsaw jigsaw = jigsawVerifyService.create();
        return DefaultHttpResultFactory.success("验证码生成成功。",
                BeanUtil.objToObj(jigsaw, JigsawResponseBean.class));
    }

    /**
     * 自动装配拼图验证码服务接口。
     *
     * @param jigsawVerifyService 拼图验证码服务接口。
     */
    @Autowired
    public void setJigsawVerifyService(JigsawVerifyService jigsawVerifyService) {
        this.jigsawVerifyService = jigsawVerifyService;
    }

    /**
     * 拼图验证码服务接口。
     */
    private JigsawVerifyService jigsawVerifyService;

}
