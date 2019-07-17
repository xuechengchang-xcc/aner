package realm;

import com.xcc.entity.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import utils.BaseTest;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @create: 2019-07-03 17:58
 * @author: Aner
 * @description:
 **/
public class PrincialCollectionTest extends BaseTest {
    @Test
    public void test(){
        //因为Realm里没有进行验证，所以箱单与每个Realm都身份验证成功了
        login("classpath:shiro-multirealm.ini","zhang","123");
        Subject subject =subject();
        //获取Primary Princepal(即第一个)
        Object primaryPrincipal1=subject.getPrincipal();
        PrincipalCollection principalCollection =subject.getPrincipals();
        Object primaryPrincipal2 =principalCollection.getPrimaryPrincipal();

        //但是因为多个Realm                                    都返回了Princepal，所以此处倒是那个是不确定的
        Assert.assertEquals(primaryPrincipal1,primaryPrincipal2);
        System.out.println("+"+primaryPrincipal1+"+"+primaryPrincipal2+"+");

        //返回a  b c
        Set<String> realmNames =principalCollection.getRealmNames();
        System.out.println(realmNames);

        //因为Myrealm1和Myrealm2返回的凭据都市zhang，所以排重了
        Set<Object> principalsSet =principalCollection.asSet();  //asList和asSet 的结果一样
        List<Object> principalsList =principalCollection.asList();
        System.out.println(principalsSet);
        System.out.println(principalsList);

        //根据Realm名字获取
        Collection<User> users =principalCollection.fromRealm("c");
        System.out.println(users);
    }

}
