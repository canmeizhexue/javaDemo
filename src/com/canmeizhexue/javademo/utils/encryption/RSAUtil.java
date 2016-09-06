package com.canmeizhexue.javademo.utils.encryption;


import com.canmeizhexue.javademo.utils.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密算法，可以数据加密和数字签名
 * 可以公钥加密，私钥解密。也可以私钥加密，公钥解密。
 * <p>
 * 密钥长度                     默认      工作模式                    填充方式                            实现方
 * 512--65536(64整数倍)          1024        ECB                       NoPadding、PKCS1Padding、         JDK
 *                                                                      OAEPWITHMD5AndMGF1Padding、
 * <p>
 * 同上                           2048       NONE                      NoPadding、PKCS1Padding、                    Bouncy Castle
 *                                                                      OAEPWITHMD5AndMGF1Padding、
 * <p>
 * <p>
 * <p>
 * </p>
 * Created by canmeizhexue on 2016/9/6.
 */
public class RSAUtil {
    private static String charset_utf8 = "utf-8";

    public static void main(String[] args) {
        jdkRSA("中国", charset_utf8);
    }

    private static void jdkRSA(String source, String charset) {
        try {
            //A和B要用RSA进行通信，A将公钥发给B，然后A用私钥加密数据传给B，B用公钥解密。
            //B向A发回数据时，用公钥加密数据，然后传给A，A用私钥解密数据。

            // 公钥对应的是x509----私钥对应的是pkcs8


            //初始化密钥
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(512);
            KeyPair keyPair = keyGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public key :" + Base64.encodeBytes(rsaPublicKey.getEncoded()));
            System.out.println("private key : " + Base64.encodeBytes(rsaPrivateKey.getEncoded()));

            //私钥加密、公钥解密---加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(source.getBytes(charset));
            System.out.println("--私钥加密、公钥解密--加密-" + Base64.encodeBytes(result));

            //私钥加密、公钥解密---解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("--私钥加密、公钥解密--解密-" + new String(result, charset));











            //公钥加密，私钥解密---加密
            x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(source.getBytes(charset));
            System.out.println("公钥加密，私钥解密---加密--" + Base64.encodeBytes(result));
            ;

            //公钥加密，私钥解密---解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密，私钥解密---解密---" + new String(result, charset));



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
