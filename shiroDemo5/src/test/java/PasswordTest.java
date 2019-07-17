import com.sun.javafx.css.converters.EnumConverter;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

/**
 * @create: 2019-07-02 14:01
 * @author: Aner
 * @description:
 **/
public class PasswordTest extends BaseTest{
    @Test
    public void testPasswordServiceWithMyRealm() {
        login("classpath:shiro-passwordservice.ini", "wu", "123");
        System.out.println(subject().getPrincipal());
    }

    @Test
    public void testPasswordServiceWithJdbcRealm() {
        login("classpath:shiro-jdbc-passwordservice.ini", "zhang", "123");
        System.out.println(subject().getPrincipal());
    }

    @Test
    public void testGeneratePassword(){
        String algorithmName="md5";
        String username="liu";
        String password="123";
        String salt1=username;
        String salt2=new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashInterations=2;

        SimpleHash hash = new SimpleHash(algorithmName,password,salt1+salt2,hashInterations);
        String encodedPassword=hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);
    }

    @Test
    public void testHashedCredentialsMatcherWithMyRealm2(){
        //使用testGeneratePassword生成的散列密码
        login("classpath:shiro-hashedCredentialsMatcher.ini","liu","123");
    }

    @Test
    public void testHashedCredentialsMatcherWithJdbcRealm() {

        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
        //使用testGeneratePassword生成的散列密码
        login("classpath:shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
    }

    private class EnumConverter extends AbstractConverter {
        @Override
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }
        @Override
        protected Object convertToType(final Class type, final Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        @Override
        protected Class getDefaultType() {
            return null;
        }

    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for(int i = 1; i <= 5; i++) {
            try {
                login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
            } catch (Exception e) {/*ignore*/}
        }
        login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
    }
}
