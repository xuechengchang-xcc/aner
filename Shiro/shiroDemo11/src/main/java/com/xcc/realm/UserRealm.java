package com.xcc.realm;

import com.xcc.eneity.User;
import com.xcc.service.UserService;
import com.xcc.service.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @create: 2019-07-09 11:16
 * @author: Aner
 * @description:
 **/
public class UserRealm extends AuthorizingRealm {
    private UserService userService =new UserServiceImpl();
    /** 
    * @Author: Aner
    * @Description: 获取对应的角色和权限
    */ 
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username =(String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo =new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username =(String)token.getPrincipal();
        User user =userService.findByUsername(username);
        if (user==null){
            throw new UnknownAccountException(); //没有找到账号
        }

        if (Boolean.TRUE.equals(user.getLocked())){
            throw new LockedAccountException();  //账号锁定
        }

        //叫给AuthenticatingRealm使用CredenetialsMatcher进行密码匹配.
        SimpleAuthenticationInfo authenticationInfo =new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()
        );
        return authenticationInfo;
    }

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo(){
        getAuthorizationCache().clear();
    }
    public void clearAllCacheAuthrnticationInfo(){
        getAuthenticationCache().clear();
    }
    public void clearAllCache(){
        clearAllCacheAuthrnticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}

