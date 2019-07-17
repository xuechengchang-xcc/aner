import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * @create: 2019-07-01 09:56
 * @author: Aner
 * @description:
 **/
public abstract class BaseTest {

    @After
    public void tearDown() throws  Exception{
        ThreadContext.unbindSecurityManager();  //退出时解除绑定Subject线程，否则对下次测试造成影响
    }

    protected  void login(String configFile,String username,String password){
        //获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        //得到SecurityManager实例，绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager =factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //得到Subhect以及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
    }

    public Subject subject(){
        return SecurityUtils.getSubject();
    }
}
