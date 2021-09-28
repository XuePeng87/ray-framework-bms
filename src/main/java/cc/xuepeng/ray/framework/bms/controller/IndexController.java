package cc.xuepeng.ray.framework.bms.controller;

import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.entity.http.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringSecurity认为登录失败，默认会返回登录页。
 * 该类自定义一个API，返回一个JSON接口，代替默认的登录页。
 *
 * @author xuepeng
 */
@RestController
@RequestMapping("/v1/index")
public class IndexController {

    @GetMapping("/v1")
    public HttpResult<Boolean> index() {
        return DefaultHttpResultFactory.timeout("登录超时。", Boolean.TRUE);
    }

}
