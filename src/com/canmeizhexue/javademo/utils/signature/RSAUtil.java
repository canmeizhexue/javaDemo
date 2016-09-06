package com.canmeizhexue.javademo.utils.signature;

import com.canmeizhexue.javademo.utils.Base64;
import com.canmeizhexue.javademo.utils.encryption.HexUtils;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**数字签名算法(带有公钥私钥的消息摘要算法)，私钥签名，公钥验证
 * 主要有MD和SHA俩类
 * <p>
 *    算法                    密钥长度                    默认              签名长度                     实现方
 *    MD2WithRSA、          512--65536(64整数倍)           1024             与密钥长度相同                JDK
 *    MD5WithRSA             同上                          1024               同上
 *
 *
 *    SHA256withRSA         同上                             2048             同上                        Bouncy Castle

 *
 *
 * </p>
 * Created by canmeizhexue on 2016/9/6.
 */
public class RSAUtil{
    private static String charset_utf8 = "utf-8";
    public static void main(String[] args){
        jdkRSA("公共",charset_utf8);
    }
    public static void jdkRSA(String source,String charset){
        try {
            //初始化密钥
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(512);
            KeyPair keyPair = keyGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("Public key :" + Base64.encodeBytes(rsaPublicKey.getEncoded()));
            System.out.println("private key : " + Base64.encodeBytes(rsaPrivateKey.getEncoded()));


            //执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(source.getBytes(charset));
            byte[] result = signature.sign();
            System.out.println("jdk rsa sign: "+ HexUtils.encodeHexStr(result));

            //验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(source.getBytes(charset));
            boolean bool=signature.verify(result);
            System.out.println("jdk verify result :"+bool);


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
