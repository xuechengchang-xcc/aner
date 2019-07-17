import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @create: 2019-07-01 13:57
 * @author: Aner
 * @description:
 **/
public class IniMainTest {
    @Test
    public void test(){
        Factory<org.apache.shiro.mgt.SecurityManager> factory =new
                IniSecurityManagerFactory("classpath:shiro-config-main.ini");
        org.apache.shiro.mgt.SecurityManager securityManager =factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token =new UsernamePasswordToken("zhang","123");

        subject.login(token);
        System.out.println(subject.isAuthenticated()+"+");
        Assert.assertTrue(subject.isAuthenticated());
    }
}
