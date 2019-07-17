import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @create: 2019-07-01 14:07
 * @author: Aner
 * @description:
 **/
public class MyAuthenticator extends ModularRealmAuthenticator {

    public void setBytes(byte[] bytes){
        System.out.println(".+++++++++++++++++++++setBytes");
        System.out.println(new String(bytes));
    }

    public void setArray(int[] ints){
        System.out.println(".+++++++++++++++++++++setArray");System.out.println(Arrays.toString(ints));
    }

    public void setSet(Set<Realm> realms){
        System.out.println(".+++++++++++++++++++++setSet");System.out.println(realms);
    }

    public void setMap(Map<Object,Object> maps){
        System.out.println(".+++++++++++++++++++++setMap");
        Set<Object> keys=maps.keySet();
        for (Object o:keys) {
            System.out.println(maps.get("key"));
        }
        System.out.println(maps.size());
        System.out.println(maps.get("1"));
        System.out.println(maps.get("key"));
    }

}
