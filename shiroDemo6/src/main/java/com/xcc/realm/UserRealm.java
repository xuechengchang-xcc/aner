package com.xcc.realm;

import com.xcc.entity.User;
import com.xcc.service.UserService;
import com.xcc.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @create: 2019-07-03 12:17
 * @author: Aner
 * @description:
 **/
public class UserRealm extends AuthorizingRealm {
    private UserService userService =new UserServiceImpl();


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String username=(String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo =new SimpleAuthorizationInfo();
        //根据用户名查找角色
        authorizationInfo.setRoles(userService.findRoles(username));
        //根据用户名查找权限
        authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username=(String) token.getPrincipal();
        //根据用户名查找用户
        User user =userService.findByUsername(username);
        System.out.println("++"+user);
        //判断是否查找到用户
        if (user == null) {
            throw  new UnknownAccountException(); //没找到账号
        }
        //判断当前账号是否为锁定状态
        if (Boolean.TRUE.equals(user.getLocked())){
            throw  new LockedAccountException(); //账号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配。可以自定义
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName() //realm name
        );
        return authenticationInfo;
    }
}
