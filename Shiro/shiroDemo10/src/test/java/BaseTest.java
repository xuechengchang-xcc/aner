import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

/**
 * @create: 2019-07-05 09:34
 * @author: Aner
 * @description:
 **/
public abstract class BaseTest {
    @Before
    public void setUp(){}


    @After
    public void tearDown() throws Exception{
        ThreadContext.unbindSubject();  //退出时请解除绑定Subject到线程。否则对下次测试造成影响
    }

    protected  void login(String configFile,String username,String pasaword){
        //获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        //得到SecurityManager实例，并绑定到SecurityUtils
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //得到SecurityManager实例，并绑定给SecurityUtils
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token =new UsernamePasswordToken(username,pasaword);
        token.setHost("10.83.1.1");
        subject.login(token);
    }

    public Subject subject(){
        return SecurityUtils.getSubject();
    }
}
