package com.canmeizhexue.javademo.utils.encryption;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.Security;

/**对称加密算法，不推荐使用了，已被破解，只供学习
 * <p>
 *     密钥长度         默认      工作模式                    填充方式                            实现方
 *     56               56          ECB、CBC、PCBC、CTR、     NoPadding、PKCS5Padding                JDK
 *                                  CTS、CFB、CFB8到128、      ISO10126Padding
 *                                  OFB、OFB8到123
 *
 *
 *     64               64          同上                      PKCS7Padding、ISO10126d2Padding      Bouncy Castle
 *                                                            ZeroBytePadding
 *
 *
 *
 * </p>
 * Created by canmeizhexue on 2016/9/5.
 */
public class DESUtil {
    private static String charset_utf8="utf-8";
    public static void main(String[]args){
        try {
//            jdkDES("中华人民共和国",charset_utf8);
            bcDES("中华人民共和国",charset_utf8);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 只有几行代码和之前的不一样
     * @param source
     * @param charset
     */
    public static void bcDES(String source,String charset){
        try {
            // 不同代码行##########动态添加provider的方式，来使用BC提供的算法
            Security.addProvider(new BouncyCastleProvider());



            System.out.println("source input is "+source);
            //不同代码行########生成key的时候提供了Provider
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
            //如果不指定provider的话，那么系统会按照一定的规则到jdk1.8.0_65\jre\lib\security\java.security里面找
            //KeyGenerator keyGenerator = KeyGenerator.getInstance("DES"/*,"BC"*/);
            System.out.println(keyGenerator.getProvider());
            //指定key的大小
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[]bytesKey = secretKey.getEncoded();
            //之前指定的是56，，刚好是8个字节
            System.out.println("key length "+bytesKey.length);

            //key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey= factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(source.getBytes(charset));
            System.out.println("bc des enctypt : "+HexUtils.encodeHexStr(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("bc des encrypt "+HexUtils.encodeHexStr(result));
            System.out.println("bc des encrypt "+new String(result,charset));

        }catch (Exception e){}
    }
    /**
     * 对字节数组进行加密，得到一个新的字节数组
     * @param source
     * @param charset
     */
    public static void jdkDES(String source,String charset){
        try {
            System.out.println("source input is "+source);
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            //指定key的大小
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[]bytesKey = secretKey.getEncoded();
            //之前指定的是56，，刚好是8个字节
            System.out.println("key length "+bytesKey.length);

            //key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey= factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(source.getBytes(charset));
            System.out.println("jdk des enctypt : "+HexUtils.encodeHexStr(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des encrypt "+HexUtils.encodeHexStr(result));
            System.out.println("jdk des encrypt "+new String(result,charset));

        }catch (Exception e){}
    }
}
