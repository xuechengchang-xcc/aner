import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @create: 2019-07-01 11:15
 * @author: Aner
 * @description:
 **/
public class NonConfigurationCreateTest {
    /** 
    * @Author: Aner
    * @Description:  没有使用ini配置文件。纯粹使用java代码实现数据库的交互 
    */ 
    @Test
    public void test(){
        DefaultSecurityManager securityManager =new DefaultSecurityManager();
        //设置authenticator
        ModularRealmAuthenticator  authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);

        //设置authorizer
        ModularRealmAuthorizer authorizer =new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);


        //设置Realm
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("root");
        JdbcRealm jdbcRealm =new JdbcRealm();
        jdbcRealm.setDataSource(ds);
        jdbcRealm.setPermissionsLookupEnabled(true);
        securityManager.setRealms(Arrays.asList(jdbcRealm));

        //将SecurityManager设置到SecurityUtils方便全局使用
        SecurityUtils.setSecurityManager(securityManager);
         Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);
        System.out.println(subject.isAuthenticated()+"+");
        Assert.assertTrue(subject.isAuthenticated());
    }

}
