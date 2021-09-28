package cc.xuepeng.ray.framework.bms.config.security;

import cc.xuepeng.ray.framework.bms.bean.request.OpLogContentRequestBean;
import cc.xuepeng.ray.framework.bms.bean.request.OpLogRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.LoginSuccessResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.core.util.entity.http.DefaultHttpResultFactory;
import cc.xuepeng.ray.framework.core.web.ip.IPUtil;
import cc.xuepeng.ray.framework.core.web.security.url.WebSecurityUrlProperties;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysOpLog;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysOpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 安全配置器。
 *
 * @author fanjiubin, xuepeng
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 安全配置。
     *
     * @param http HttpSecurity。
     * @throws Exception 配置异常。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 开启跨域功能
                .cors()
                .and()
                // 开启防御CSRF攻击，服务端会在Cookie中写入XSRF-TOKEN
                .csrf().csrfTokenRepository(createCookieCsrfTokenRepository())
                // 配置不需要鉴权的路径
                .ignoringAntMatchers(getUrlIgnores())
                .and().authorizeRequests()
                //.antMatchers("/**").hasRole("ADMIN").anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                // loginPage自定义登录页
                .and().formLogin().usernameParameter("userAccount").passwordParameter("userSecret")
                // 替换SpringSecurity的默认登录页
                .loginPage(webSecurityUrlProperties.getLoginPage())
                // 设置登录失败返回数据
                .failureHandler(authenticationFailureHandler())
                // 设置登录成功返回数据
                .successHandler(authenticationSuccessHandler())
                // 设置登录请求地址，同时验证码过滤器拦截此url
                .loginProcessingUrl(webSecurityUrlProperties.getSignin()).permitAll()
                // 设置登出请求地址，登出时删除Cookies
                .and().logout().logoutUrl(webSecurityUrlProperties.getSignout())
                .deleteCookies("XSRF-TOKEN", "SESSION", "JSESSIONID").permitAll()
                // 登出成功返回数据
                .logoutSuccessHandler(logoutSuccessHandler())
                // 无权限返回数据
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                // 登录身份超时返回数据
                .and().sessionManagement().invalidSessionUrl(webSecurityUrlProperties.getLoginPage())
                // 设置是否只允许一个用户登录
                .maximumSessions(-1)
                .sessionRegistry(sessionRegistry())
                .expiredSessionStrategy(sessionInformationExpiredStrategy());
    }

    /**
     * 认证配置。
     *
     * @param auth 认证管理器。
     * @throws Exception 认证异常。
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //重写userDetailsService
        auth.userDetailsService(securityUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * @return 创建session注册表。
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * @return 密码验证处理器。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(this.encode(rawPassword));
            }
        };
    }

    /**
     * @return 登入成功处理器。
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            writeJson(httpServletResponse, BeanUtil.getObjToStr(DefaultHttpResultFactory.success(
                    "登录成功。",
                    new LoginSuccessResponseBean(
                            authentication.getName(),
                            ((DefaultCsrfToken) httpServletRequest.getAttribute("_csrf")).getToken()
                    )
            )));
            // 写入登录日志
            OpLogRequestBean operationLogRequestBean = new OpLogRequestBean();
            operationLogRequestBean.setAccount(authentication.getName());
            operationLogRequestBean.setModule("用户登录");
            operationLogRequestBean.setDescription("根据账号密码进行登录");
            operationLogRequestBean.setSrcIp(IPUtil.getRealIpAddress(httpServletRequest));
            operationLogRequestBean.setSysOpLogContent(new OpLogContentRequestBean("{\"data\":\"" + authentication.getName() + "\"}"));
            sysOpLogService.create(BeanUtil.objToObj(operationLogRequestBean, SysOpLog.class));
        };
    }

    /**
     * @return 登录失败处理器。
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (httpServletRequest, httpServletResponse, authenticationException) ->
                writeJson(httpServletResponse, BeanUtil.getObjToStr(DefaultHttpResultFactory.fail("登录失败。")));
    }

    /**
     * @return 登出成功处理器。
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            writeJson(httpServletResponse, BeanUtil.getObjToStr(DefaultHttpResultFactory.success("登出成功。")));
            OpLogRequestBean operationLogRequestBean = new OpLogRequestBean();
            operationLogRequestBean.setAccount(authentication.getName());
            operationLogRequestBean.setModule("用户登出");
            operationLogRequestBean.setDescription("用户登出系统");
            operationLogRequestBean.setSrcIp(IPUtil.getRealIpAddress(httpServletRequest));
            operationLogRequestBean.setSysOpLogContent(new OpLogContentRequestBean("{\"data\":\"" + authentication.getName() + "\"}"));
            sysOpLogService.create(BeanUtil.objToObj(operationLogRequestBean, SysOpLog.class));
        };
    }

    /**
     * @return 无权限处理器。
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (httpServletRequest, httpServletResponse, accessDeniedException) ->
                writeJson(
                        httpServletResponse,
                        BeanUtil.getObjToStr(DefaultHttpResultFactory.permissions("无权限操作。"))
                );
    }

    /**
     * @return 登录信息超时策略。
     */
    @Bean
    public InvalidSessionStrategy sessionTimeout() {
        return (httpServletRequest, httpServletResponse) -> {
            // 清空XSRF-TOKEN
            Cookie xsrfTokenCookie = new Cookie("XSRF-TOKEN", null);
            xsrfTokenCookie.setMaxAge(0);
            xsrfTokenCookie.setPath(httpServletRequest.getContextPath() + "/");
            httpServletResponse.addCookie(xsrfTokenCookie);
            // 清空SESSION
            Cookie sessionCookie = new Cookie("SESSION", null);
            sessionCookie.setMaxAge(0);
            sessionCookie.setPath(httpServletRequest.getContextPath() + "/");
            httpServletResponse.addCookie(sessionCookie);
            writeJson(
                    httpServletResponse,
                    BeanUtil.getObjToStr(DefaultHttpResultFactory.timeout("登录超时。"))
            );
        };
    }

    /**
     * @return 用户重复登录。
     */
    @Bean
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return event -> writeJson(
                event.getResponse(),
                BeanUtil.getObjToStr(DefaultHttpResultFactory.timeout("用户重复登录。"))
        );
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

    /**
     * 获取忽略验证的Url。
     *
     * @return 忽略验证的Url。
     */
    private String[] getUrlIgnores() {
        List<String> ignoreList = new ArrayList<>();
        ignoreList.add(webSecurityUrlProperties.getSignin());
        if (webSecurityUrlProperties.getIgnoreUrls() != null) {
            ignoreList.addAll(webSecurityUrlProperties.getIgnoreUrls());
        }
        String[] ignores = new String[ignoreList.size()];
        return ignoreList.toArray(ignores);
    }

    /**
     * @return 创建CSRF的Cookie存储对象。
     */
    private CookieCsrfTokenRepository createCookieCsrfTokenRepository() {
        CookieCsrfTokenRepository result = CookieCsrfTokenRepository.withHttpOnlyFalse();
        result.setCookiePath("/");
        return result;
    }

    /**
     * 自动装配定义SpringSecurity所需的Url。
     *
     * @param webSecurityUrlProperties 定义SpringSecurity所需的Url。
     */
    @Autowired
    public void setWebSecurityUrlProperties(WebSecurityUrlProperties webSecurityUrlProperties) {
        this.webSecurityUrlProperties = webSecurityUrlProperties;
    }

    /**
     * 自动装配用户信息详情服务。
     *
     * @param securityUserDetailsService 用户信息详情服务。
     */
    @Autowired
    public void setSecurityUserDetailsService(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }

    /**
     * 自动装配Url访问鉴权器。
     *
     * @param urlAccessDecisionManager Url访问鉴权器。
     */
    @Autowired
    public void setUrlAccessDecisionManager(UrlAccessDecisionManager urlAccessDecisionManager) {
        this.urlAccessDecisionManager = urlAccessDecisionManager;
    }

    /**
     * 自动装配Url路径安全校验器。
     *
     * @param urlFilterInvocationSecurityMetadataSource Url路径安全校验器。
     */
    @Autowired
    public void setUrlFilterInvocationSecurityMetadataSource(UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource) {
        this.urlFilterInvocationSecurityMetadataSource = urlFilterInvocationSecurityMetadataSource;
    }

    /**
     * 自动装配操作日志业务处理接口。
     *
     * @param sysOpLogService 操作日志业务处理接口。
     */
    @Autowired
    public void setSysOpLogService(SysOpLogService sysOpLogService) {
        this.sysOpLogService = sysOpLogService;
    }

    /**
     * 定义SpringSecurity所需的Url。
     */
    private WebSecurityUrlProperties webSecurityUrlProperties;
    /**
     * 用户信息详情服务。
     */
    private SecurityUserDetailsService securityUserDetailsService;
    /**
     * Url路径安全校验器。
     */
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    /**
     * Url访问鉴权器。
     */
    private UrlAccessDecisionManager urlAccessDecisionManager;
    /**
     * 操作日志业务处理接口。
     */
    private SysOpLogService sysOpLogService;

}
