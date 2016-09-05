package com.canmeizhexue.javademo.utils.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**对称加密算法，3DES或者DESede，，效率相对较低，催生AES
 * <p>
 *     密钥长度         默认      工作模式                    填充方式                            实现方
 *     112、168          168        ECB、CBC、PCBC、CTR、     NoPadding、PKCS5Padding                JDK
 *                                  CTS、CFB、CFB8到128、      ISO10126Padding
 *                                  OFB、OFB8到123
 *
 *
 *     128、192         168          同上                      PKCS7Padding、ISO10126d2Padding      Bouncy Castle
 *                                                            ZeroBytePadding
 *
 *
 *
 * </p>
 * Created by canmeizhexue on 2016/9/5.
 */
public class TripleDESUtil {
    private static String charset_utf8="utf-8";
    public static void main(String[] args){
    jdk3DES("中国",charset_utf8);
    }

    /**
     * 和DES的代码非常类似，只是改了几个地方而已
     * @param source
     * @param charset
     */
    private static void jdk3DES(String source,String charset){
        try {
            System.out.println("source input is "+source);
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            //指定key的大小
//            keyGenerator.init(168);
            // 或者用这个，俩个都可以的
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[]bytesKey = secretKey.getEncoded();
            //之前指定的是56，，刚好是8个字节
            System.out.println("key length "+bytesKey.length);

            //key转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey= factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(source.getBytes(charset));
            System.out.println("jdk 3des enctypt : "+HexUtils.encodeHexStr(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk 3des encrypt "+HexUtils.encodeHexStr(result));
            System.out.println("jdk 3des encrypt "+new String(result,charset));

        }catch (Exception e){}
    }

    private static void bc3DES(String source,String charset){

    }
}
