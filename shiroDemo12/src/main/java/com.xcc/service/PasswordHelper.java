package com.xcc.service;

import com.xcc.eneity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @create: 2019-07-09 09:04
 * @author: Aner
 * @description:
 **/
@Service
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator =new SecureRandomNumberGenerator();
    private String algorithmName="md5";
    private  int hashIterations=2;

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int getHashIterations() {
        return hashIterations;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

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
