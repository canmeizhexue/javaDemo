package com.canmeizhexue.javademo.jvm;
/**
 * 虚拟机class文件格式用winhex十六进制编辑器查看
 * @author canmeizhexue
 *
 */
public class TestClass extends TestBaseClass implements MyInterface1 , MyInterface2{
	private int m;
	private static String staticString="helloworld";
	private static final String finalString="finalString";
	private static int staticInt;
	static{
		staticInt = 9;
	}
	
	public TestClass(int m) {
		super();
		this.m = m;
	}

	public int inc(){
		return m+1;
	}
	public static String staticMethod(){
		return staticString;
	}

	@Override
	public void myInterface2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void myInterface1() {
		// TODO Auto-generated method stub
		
	}
}
