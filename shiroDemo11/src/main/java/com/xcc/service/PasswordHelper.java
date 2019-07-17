package com.xcc.service;

import com.xcc.eneity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @create: 2019-07-09 09:04
 * @author: Aner
 * @description:
 **/
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator =new SecureRandomNumberGenerator();

    private String algorithmName="md5";
    private final int hashIterations=2;

    public void encryptPassword(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword =new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }
}
