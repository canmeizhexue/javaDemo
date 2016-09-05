package com.canmeizhexue.javademo.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**对称加密算法，构建口令，公布口令
 *
 * PBE算法结合了消息摘要算法和对称加密算法的优点
 *
 * 对也有算法的包装
 *
 * <p>
 *     算法                   密钥长度        默认      工作模式        填充方式            实现
 *     PBEWithMD5AndDES         64              64      CBC             PKCS5Padding、        Bouncy Castle
 *     PBEWithMD5AndRC2         112             128     同上            PKCS7Padding、
 *     PBEWithSHA1AndDES        64              64       同上            ISO10126Padding、
 *     PBEWithSHA1AndRC2        128             128      同上             ZeroBytePadding
 *
 *
 *
 *     PBEWithMD5AndDES         56              56      CBC             PKCS5Padding            JDK
 *     PBEWithMD5AndTripleDES   112、168         168     CBC             同上
 *     PBEWithSHA1AndDESede     112、168         168     CBC             同上
 *
 *
 *
 * </p>
 * Created by canmeizhexue on 2016/9/5.
 */
public class PBEUtil {
    private static String charset_utf8="utf-8";
    public static void main(String[] args){
        jdkPBE("中国", charset_utf8);
    }
    public static void jdkPBE(String source,String charset){
        try {
            // 初始化盐---加密的随机串
            SecureRandom secureRandom = new SecureRandom();
            //8*8=64
            byte[] salt = secureRandom.generateSeed(8);

            //口令和密钥
            String password="canmeizhexue";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = secretKeyFactory.generateSecret(pbeKeySpec);

            //加密，100是迭代的次数
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
            byte[] result = cipher.doFinal(source.getBytes(charset));
            System.out.println("jdk pbe encrypt: "+HexUtils.encodeHexStr(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt: "+new String(result,charset));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
