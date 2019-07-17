package com.xcc.realm;

import com.xcc.service.UserService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @create: 2019-07-11 10:21
 * @author: Aner
 * @description: CasRealm 根据 CAS 服务器端返回的用户身份获取相应的角色/权限信息。
  **/
public class MyCasRealm extends CasRealm {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username =(String)principals.getPrimaryPrincipal();
        //获取身份信息
        SimpleAuthorizationInfo authorizationInfo =new SimpleAuthorizationInfo();
        //根据姓名查找角色
        authorizationInfo.setRoles(userService.findRoles(username));
        //根据姓名查找操作权限
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }
}
