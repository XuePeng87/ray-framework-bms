package cc.xuepeng.ray.framework.bms.config.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Url访问鉴权器。
 *
 * @author xuepeng
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判断用户的角色是否有权限访问。
     *
     * @param authentication 认证信息。
     * @param o              请求对象。
     * @param collection     角色集合。
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        for (ConfigAttribute ca : collection) {
            // 当前请求需要的权限
            String needRole = ca.getAttribute();
            // 判断是否不需要鉴权即可通过
            if (StringUtils.equals("ROLE_ANYONE", needRole)) {
                return;
            }
            // 当前用户所具有的权限，判断是否允许调用该API
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足。");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
