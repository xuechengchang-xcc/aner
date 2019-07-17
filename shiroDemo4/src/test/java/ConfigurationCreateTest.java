import com.mysql.jdbc.Security;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @create: 2019-07-01 13:29
 * @author: Aner
 * @description:
 **/
public class ConfigurationCreateTest {
    /** 
    * @Author: Aner
    * @Description: 使用加载配置文件ini来进行数据库交互
    */ 
    @Test
    public void test(){
        Factory<org.apache.shiro.mgt.SecurityManager> factory =new
                IniSecurityManagerFactory("classpath:shiro-config.ini");
        org.apache.shiro.mgt.SecurityManager securityManager =factory.getInstance();
        //将SecutityManager设置到SecurityUtils 方便全局使用
        SecurityUtils.setSecurityManager(securityManager);
         Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new
                UsernamePasswordToken("zhang","123");
        subject.login(token);
        System.out.println(subject.isAuthenticated()+"++");
        //判断当前内容是否为true
        Assert.assertTrue(subject.isAuthenticated());
    }
}
