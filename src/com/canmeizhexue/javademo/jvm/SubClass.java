package com.canmeizhexue.javademo.jvm;

public class SubClass extends SuperClass {
	static{
		System.out.println("SubClass --init");
	}
	public static int a=0;
	public static void subStaticMethod(){
		System.out.println("SubClass --subStaticMethod");
	}
}
