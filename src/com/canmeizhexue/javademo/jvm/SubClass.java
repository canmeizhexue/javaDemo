package com.canmeizhexue.javademo.jvm;

public class SubClass extends SuperClass {
	static{
		System.out.println("SubClass --init");
	}
	public static int a=0;

	public static void subStaticMethod(){
		System.out.println("SubClass --subStaticMethod");
	}

	public SubClass() {
		System.out.println("SubClass---------SubClass---------SubClass");
	}
	// 成员变量赋值操作会优先于构造函数,会先看到LogClass里面的日志，再看到这个类的日志信息
	private LogClass logClass = new LogClass();
}
