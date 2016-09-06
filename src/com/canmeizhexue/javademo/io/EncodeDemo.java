package com.canmeizhexue.javademo.io;

/**
 * 编码问题
 * Created by canmeizhexue on 2016/9/6.
 */
public class EncodeDemo {
 /*   编码问题，中文系统桌面建立文本文件的时候存的是gbk编码字节序列，
    用记事本打开的时候有可能根据存的内容自动按照新的编码来解析，
    ，所以看到  联  联想  联通  会有不同的效果。
    但是在IDE建立文本文件的时候会使用项目的默认编码来存文本*/
    public static void main(String[] args) throws Exception {
        String s = "联";
        byte[] bytes0 = s.getBytes("gbk");
        for (byte b : bytes0) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println(new String(bytes0, "gbk"));
        System.out.println();
        byte[] bytes1 = s.getBytes();//项目默认编码
        for (byte b : bytes1) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
        System.out.println();
        byte[] bytes2 = s.getBytes("utf-8");//
        // utf-8编码中文占三个字节，英文占一个字节
        for (byte b : bytes2) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();
        byte[] bytes3 = s.getBytes("gbk");
        // gbk编码中文占俩个字节，英文占一个字节
        for (byte b : bytes3) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();
        //java双字节编码
        byte[] bytes4 = s.getBytes("utf-16be");
        // utf-16be编码中文占俩个字节，英文占俩个字节
        for (byte b : bytes4) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }
    }
}
