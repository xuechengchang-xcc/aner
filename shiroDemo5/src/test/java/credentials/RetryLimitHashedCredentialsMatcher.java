package credentials;


import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import javax.security.auth.login.AccountLockedException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @create: 2019-07-02 15:39
 * @author: Aner
 * @description:
 **/
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Ehcache passwordRetryCahe;

    public RetryLimitHashedCredentialsMatcher(){
        CacheManager cacheManager =CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
        passwordRetryCahe=cacheManager.getCache("passwordRetryCahe");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username=(String)token.getPrincipal();
        //retry count +1
        Element element =passwordRetryCahe.get(username);
        if (element == null) {
            element= new Element(username,new AtomicInteger(0));
            passwordRetryCahe.put(element);
        }
        AtomicInteger retryCount =(AtomicInteger) element.getObjectValue();
        if (retryCount == null) {
            //if retry count >5 throw
            throw  new ExcessiveAttemptsException();
        }
        boolean matches =super.doCredentialsMatch(token,info);
        if (matches) {
            //clear retry count
            passwordRetryCahe.remove(username);
        }
        return matches;
    }
}
