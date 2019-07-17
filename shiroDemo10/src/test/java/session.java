import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @create: 2019-07-05 09:43
 * @author: Aner
 * @description:
 **/
public class session extends  BaseTest{
    @Test
    public void testGetSession()throws Exception{
        login("classpath:shiro.ini","zhang","123");
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(); //获取会话

        System.out.println(session.getId()+"会话标识");  //会话
        System.out.println(session.getHost()+"登录主机地址"); //登录主机地址
        System.out.println(session.getTimeout()+"超时时间"); //超时时间
        System.out.println(session.getStartTimestamp()+"获取会话创建时间");  //获取会话创建时间
        System.out.println(session.getLastAccessTime()+"最后访问时间");  //最后访问时间
        Thread.sleep(1000L);
        session.touch(); //更新会话最后访问时间
        System.out.println(session.getLastAccessTime()+"更新会话最后访问时间");

        //会话属性操作
        session.setAttribute("key","123");
        System.out.println("session中存在的会话："+session.getAttribute("key"));
        Assert.assertEquals("123",session.getAttribute("key"));
        session.removeAttribute("key");
    }
}
