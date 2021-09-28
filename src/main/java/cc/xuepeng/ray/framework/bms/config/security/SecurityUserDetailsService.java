package cc.xuepeng.ray.framework.bms.config.security;

import cc.xuepeng.ray.framework.bms.bean.request.RoleRequestBean;
import cc.xuepeng.ray.framework.bms.bean.response.UserResponseBean;
import cc.xuepeng.ray.framework.core.util.bean.BeanUtil;
import cc.xuepeng.ray.framework.sdk.mgt.entity.SysUser;
import cc.xuepeng.ray.framework.sdk.mgt.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户信息详情服务。
 *
 * @author fanjiubin, xuepeng
 */
@Service("userDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

    /**
     * 根据账号加载用户信息。
     *
     * @param userAccount 账号。
     * @return 用户信息。
     * @throws UsernameNotFoundException 用户不存在异常。
     */
    @Override
    public UserDetails loadUserByUsername(String userAccount) {
        // 根据帐号查询用户
        final SysUser user = sysUserService.findByAccount(userAccount);
        if (user == null) {
            throw new BadCredentialsException("登录失败，用户[" + userAccount + "]不存在");
        }
        final UserResponseBean userResponseBean = BeanUtil.objToObj(user, UserResponseBean.class);
        // 设置角色，角色都以ROLE_开头
        final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (RoleRequestBean role : userResponseBean.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleCode());
            grantedAuthorities.add(grantedAuthority);
        }
        // 用户名密码构造User
        return new User(userAccount, user.getUserSecret(), grantedAuthorities);
    }

    /**
     * 自动装配用户消费层访问入口。
     *
     * @param sysUserService 用户消费层访问入口。
     */
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 用户消费层访问入口。
     */
    private SysUserService sysUserService;

}
