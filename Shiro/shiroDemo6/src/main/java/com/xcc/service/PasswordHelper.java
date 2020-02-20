package com.xcc.service;/**
 * ClassName:PasswordHelper
 * package:com.xcc
 * Description: TODO
 *
 * @date ：2019/7/3 9:03
 * @Author : 安儿
 */

import com.xcc.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @create: 2019-07-03 09:03
 * @author: Aner
 * @description: 加密方法
 **/
public class PasswordHelper {
        private RandomNumberGenerator randomNumberGenerator =new SecureRandomNumberGenerator();

        //加密方式
        private String algorithmName="md5";
        //加盐次数
        private final int hashInterations=2;
        //加密方法
        public  void encyptPassword(User user){

            user.setSalt(randomNumberGenerator.nextBytes().toHex());

            String newPassword=new SimpleHash(
                    algorithmName,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getCredentialsSalt()),
                    hashInterations).toHex();

            user.setPassword(newPassword);
        }
}
