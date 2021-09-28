package cc.xuepeng.ray.framework.bms.config.security;

import cc.xuepeng.ray.framework.core.web.security.url.WebSecurityUrlProperties;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Url路径安全校验器。
 *
 * @author xuepeng
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 判断请求的Url是否有访问权限。
     *
     * @param o 请求对象。
     * @return 是否有权限访问该Url。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        // 获取请求地址
        String url = ((FilterInvocation) o).getRequestUrl();
        // 判断请求的Url是否在忽略列表中
        if (CollectionUtils.isNotEmpty(webSecurityUrlProperties.getIgnoreUrls())) {
            for (String ignoreUrl : webSecurityUrlProperties.getIgnoreUrls()) {
                if (antPathMatcher.match(ignoreUrl, url)) {
                    return SecurityConfig.createList("ROLE_ANYONE");
                }
            }
        }
        // 查询全部API，与当前请求路径匹配
        List<String> apis = sysRoleService.findAllRoleApi();
        for (String api : apis) {
            if (antPathMatcher.match(api, url)) {
                // 匹配到API后，查询API对应的角色编号
                List<String> roleCodes = sysRoleService.findRoleCodeByRoleApi(api);
                String[] values = roleCodes.toArray(new String[0]);
                return SecurityConfig.createList(values);
            }
        }
        // 若没有匹配则返回无权限
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    /**
     * 自动装配角色管理的业务处理接口。
     *
     * @param sysRoleService 角色管理的业务处理接口。
     */
    @Autowired
    public void setSysRoleService(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
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
     * 角色管理的业务处理接口。
     */
    private SysRoleService sysRoleService;
    /**
     * API比较工具。
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 定义SpringSecurity所需的Url。
     */
    private WebSecurityUrlProperties webSecurityUrlProperties;

}
