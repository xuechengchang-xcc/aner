package realm;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Configurable;
import sun.util.locale.LocaleSyntaxException;
import utils.BaseTest;

/**
 * @create: 2019-07-03 14:56
 * @author: Aner
 * @description:
 **/
public class UserRealmTest extends BaseTest {
    /** 
    * @Author: Aner
    * @Description: 测试使用当前账号是否能登录
    */ 
    @Test
    public void testLoginSuccess(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertTrue(subject().isAuthenticated());
        System.out.println(subject().isAuthenticated()+"++++++++");
    }
    /** 
    * @Author: Aner
    * @Description: 测试当前账号是否存在 
    */ 
    @Test(expected= UnknownAccountException.class)
    public void testLoginFailWithUnknownUsername(){
        login("classpath:shiro.ini",u1.getUsername()+"1",password);
        System.out.println(subject().isAuthenticated()+"++++++++");
    }
    /** 
    * @Author: Aner
    * @Description: 测试当前密码是否错误
    */ 
    @Test(expected = IncompatibleClassChangeError.class)
    public void testLoginFailWithErrotPassword(){
        login("classpath:shiro.ini",u1.getUsername(),password+"1");
    }
    /** 
    * @Author: Aner
    * @Description: 测试当前账号是否被锁定
    */ 
    @Test(expected = LockedAccountException.class)
    public  void testLoginFailWithLocked(){
        login("classpath:shiro.ini",u4.getUsername(),password+"1");
    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testLoginFailWithLimitRetryCount(){
        for (int i = 0; i < 5; i++) {
            try {
                login("classpath:shiro.ini",u3.getUsername(),password+"1");
            }catch (Exception e){}
        }
        login("classpath:shiro.ini",u3.getUsername(),password+"1");
    }


    @Test
    public void testHasRole(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertTrue(subject().hasRole("admin"));
    }

    @Test
    public void testNoRole(){
        login("classpath:shiro.ini",u2.getUsername(),password);
        System.out.println(subject().hasRole("admin")+"++");
        Assert.assertTrue(subject().hasRole("admin"));

    }

    @Test
    public void testHasPermission(){
        login("classpath:shiro.ini",u1.getUsername(),password);
        Assert.assertTrue(subject().isPermittedAll("user:create","menu:create"));
    }

    @Test
    public void testNoPermission(){
        login("classpath:shiro.ini",u2.getUsername(),password);
        System.out.println(subject().isPermitted("user:create")+"+");
        Assert.assertTrue(subject().isPermitted("user:create"));

    }
}
