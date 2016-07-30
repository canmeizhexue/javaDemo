package com.canmeizhexue.javademo.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <p>hash散列算法的一种，或者说摘要算法的一种</p>
 *<p>将字符串的utf-8字节数组进行摘要，然后将得到的字节数组转换成16进制的字符串,md5串中不会大小写混合</p>
 * @author canmeizhexue<br/>
 * @version 1.0 (16/4/21 下午9:53)<br/>
 */
public class MD5Util {

    /**
     *  生成摘要串
     * @param str 内容
     * @return
     */
    public static String md5(String str) {
        String encodeStr = null;
        try {
            //声明消息摘要对象
            MessageDigest md = null;
            //创建消息摘要
            md = MessageDigest.getInstance("MD5");
            //将口令的数据传给消息摘要对象
            md.update(str.getBytes("UTF-8"));
            //获得消息摘要的字节数组
            byte[] digest = md.digest();

            //将字节数组格式加密后的口令转化为16进制字符串格式的口令
            encodeStr = HexUtils.encodeHexStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return encodeStr;
    }
    public static void main(String[]args){
    	String originalString = "儿科换个可更换给我喝口hop诶好？1245￥%#%……&&%";
    	String md5 = md5(originalString);
    	System.out.println(md5);
    	String str="6f70a4e314fd2a47f86136c7bf8f5262";
    	System.out.println((str.equalsIgnoreCase(md5)));
    	
    }

}
