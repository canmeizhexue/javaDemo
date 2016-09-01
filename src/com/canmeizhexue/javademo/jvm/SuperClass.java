package com.canmeizhexue.javademo.jvm;
/**
 * 
 * @author canmeizhexue
 *
 */
public class SuperClass {
	static{
		System.out.println("SuperClass --init");
	}
	public static int value = 123;
	// 常量
	public static final int finalInt=456;
	public static void staticMethod(){
		System.out.println("-------静态方法");
	}
	public static void superStaticMethod(){
		System.out.println("SuperClass --superStaticMethod");
	}
}
