package com.xcc.realm;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.xcc.eneity.User;
import com.xcc.utils.BaseTest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.junit.Test;

/**
 * @create: 2019-07-09 12:20
 * @author: Aner
 * @description:
 **/
public class UserRealmTest extends BaseTest {
    @Override
    public void tearDown() throws Exception {
        userService.changePassword(u1.getId(),password);
        RealmSecurityManager securityManager =(RealmSecurityManager)SecurityUtils.getSecurityManager();
        UserRealm userRealm =(UserRealm)securityManager.getRealms().iterator().next();
       userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());

       super.tearDown();
    }

    @Test
    public void testClearCacheAuthenticationInfo(){
        login(u1.getUsername(),password);
        userService.changePassword(u1.getId(),password+"1");

        RealmSecurityManager securityManager =(RealmSecurityManager)SecurityUtils.getSecurityManager();
        UserRealm userRealm =(UserRealm)securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());

        login(u1.getUsername(),password+"1");
        System.out.println("++"+subject().getPrincipal());
    }
    @Test
    public void testClearCacheAuthorizationInfo(){
        login(u1.getUsername(),password);
        subject().checkRole(r1.getRole());
        userService.correlationRoles(u1.getId(),r2.getId());

        RealmSecurityManager securityManager =(RealmSecurityManager)SecurityUtils.getSecurityManager();
        UserRealm userRealm =(UserRealm)securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthorizationInfo(subject().getPrincipals());

        subject().checkRole(r2.getRole());
        System.out.println("+++++"+r2.getRole()+":"+r2.getId()+":"+subject().getPrincipal());
    }

    @Test
    public void testClearCache(){
        login(u1.getUsername(),password);
        subject().checkRole(r1.getRole());

        userService.changePassword(u1.getId(),password+"1");
        userService.correlationRoles(u1.getId(),r2.getId());

        RealmSecurityManager securityManager =(RealmSecurityManager)SecurityUtils.getSecurityManager();
        UserRealm userRealm =(UserRealm) securityManager.getRealms().iterator().next();
        userRealm.clearCache(subject().getPrincipals());

        login(u1.getUsername(),password+"1");
        subject().checkRole(r2.getRole());

        System.out.println(subject().getPrincipals()+"+");
    }
}
