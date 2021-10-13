package cc.xuepeng.ray.framework.bms.config.filter;

import cc.xuepeng.ray.framework.bms.service.JigsawVerifyService;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.util.jigsaw.Jigsaw;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class VerificationCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals(request.getRequestURI(), "/bms/signin")) {
            Jigsaw jigsaw = new Jigsaw();
            jigsaw.setX(Integer.parseInt(request.getParameter("left")));
            jigsaw.setToken(request.getParameter("token"));
            if (!jigsawVerifyService.validate(jigsaw)) {
                writeJson(
                        response,
                        BeanUtil.getObjToStr(DefaultHttpResultFactory.fail("验证码错误。"))
                );
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 写入JSON到响应流。
     *
     * @param response HttpServletResponse。
     * @param json     内容。
     */
    private void writeJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.write(json);
        }
    }

    @Autowired
    public void setJigsawVerifyService(JigsawVerifyService jigsawVerifyService) {
        this.jigsawVerifyService = jigsawVerifyService;
    }

    private JigsawVerifyService jigsawVerifyService;


}
