import org.junit.Assert;
import org.junit.Test;

/**
 * @create: 2019-07-01 10:57
 * @author: Aner
 * @description:
 **/
public class AuthorizerTest extends BaseTest{

    @Test
    public void testIsPermitted(){
        login("classpath:shiro-jdbc-authorizer.ini","zhang","123");
        //判断拥有权限:user:create
        Assert.assertTrue(subject().isPermitted("user1:update"));
        Assert.assertTrue(subject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限    为用户添加权限
        Assert.assertTrue(subject().isPermitted("+user1+2"));//添加权限
        Assert.assertTrue(subject().isPermitted("+user1+8"));//查询权限
        Assert.assertTrue(subject().isPermitted("+user2+10"));//新增和查看权限

        Assert.assertFalse(subject().isPermitted("+user1+4")); //没有删除权限

        Assert.assertTrue(subject().isPermitted("menu:view")); //通过MyROlePermissionResolber解析的到的权限

    }
}
