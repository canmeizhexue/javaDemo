package com.canmeizhexue.javademo.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**作为DES的替代者
 * <p>
 *     密钥长度               默认      工作模式                    填充方式                            实现方
 *     128、192、256          128        ECB、CBC、PCBC、CTR、     NoPadding、PKCS5Padding                JDK（256位密钥需要获得无政策限制权限文件）
 *                                      CTS、CFB、CFB8到128、      ISO10126Padding
 *                                      OFB、OFB8到123
 *
 *
 *     同上                   同上         同上                      PKCS7Padding、                    Bouncy Castle
 *                                                                  ZeroBytePadding
 *
 *
 *
 * </p>
 * Created by canmeizhexue on 2016/9/5.
 */
public class AESUtils {
    private static String charset_utf8="utf-8";
    public static void main(String[] args){
        jdkAES("中国", charset_utf8);
    }
    public static void jdkAES(String source,String charset){
        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //根据不同的算法生成默认长度的key
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            System.out.println("key length is "+keyBytes.length);
            //key转换
            Key key = new SecretKeySpec(keyBytes,"AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[]result = cipher.doFinal(source.getBytes(charset));
            System.out.println("jdk aes encrypt: "+HexUtils.encodeHexStr(result));;


            //解密
            cipher.init(Cipher.DECRYPT_MODE,key);
            result=cipher.doFinal(result);
            System.out.println("jdk aes decrypt: "+new String(result,charset));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
