package com.xcc.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @create: 2019-07-03 18:02
 * @author: Aner
 * @description:
 **/
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "a"; //realm name 为"a"
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo(
                "zhang",  //身份 字符串类型
                "123",     //凭证
                getName()  //Realm Name
        );
    }
}
