package com.cehome.cloud.user.util;

import com.cehome.cloud.user.model.po.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码加密
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static String algorithmName = "md5";
    public static int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public static void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setEncryptPassword(newPassword);
    }

    public static Boolean getEncryptPassword(String password, User user) {
        String credentialsSalt = user.getCredentialsSalt();
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(credentialsSalt),
                hashIterations).toHex();
        return newPassword.equalsIgnoreCase(user.getEncryptPassword());
    }

}
