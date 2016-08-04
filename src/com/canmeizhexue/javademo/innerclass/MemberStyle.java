package com.canmeizhexue.javademo.innerclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import com.canmeizhexue.javademo.innerclass.MemberStyle.FirstInner.SecondInner;

/**
 * 成员内部类,对于成员内部类而言，编译器会对外部类和内部类做一些额外的操作<p>
 * 内部类访问外部类的私有变量或者私有方法时，会在外部类生成相应的静态访问方法。
 * 不管内部类是否会访问外部类的成员，都会给内部类生成一个以外部类为参数的构造函数
 * 
 * @author zengyaping
 *
 */
public class MemberStyle {
	
	
	private int x=100;
	private static int y=200;
	 int notAccessed=300;
	 
	 private int privateVariable=x; //私有字段在内部类中直接访问，会在外部类生成俩个静态方法，用来获取和设置
	 int packageVariable;//包访问权限的字段在内部类中直接访问，不会在外部类中生成静态方法，这种在android中推荐的
	 public int publicVariable;//保护权限的字段在内部类中直接访问，不会在外部类中生成静态方法
	 protected int protectedVariable;//保护权限的字段在内部类中直接访问，不会在外部类中生成静态方法
	 
	 private static int privateStaticVariable=600;//私有字段在内部类中直接访问，会在外部类生成俩个静态方法，用来获取和设置
	 
	 
	 
	 void increase(){
		x++;
		y++;
	}
	private void log(){
		System.out.println("MemberStyle---x="+x+"-----y="+y);
	}
	private void notAccessedMethod(){
		
	}
	//成员内部类是没有static修饰的，类的定义是在外部类的类体中的
	//可以直接方法外部类的成员变量和成员方法，甚至是私有的
	//可以通过反编译工具看到，内部类有一个引用变量指向外部类，是编译器自动加上的
	class FirstInner{
		// 即使不访问外部类的资源，也会由编译器生成外部类的成员变量
		private int firstKey=1;
/*		编译器加上的代码类似于,注意$后面的数字
 * 		
		MemberStyle this$0;如果想要更进一步的了解，可以百度this$0*/
		public FirstInner() {
			System.out.println("FirstInner()");
		}
		public void innerMethod(){
			//上面的this$0是反编译出来的，当然可以理解为MemberStyle MemberStyle.this;
/*			MemberStyle.this.log();
//			increase();
			log();*/
//			privateStaticVariable++;
//			notAccessedMethod();
//			increase();
		}
		class SecondInner{
			/*		编译器加上的代码类似于
			FirstInner this$1;*/
			class ThirdInner{
				/*		编译器加上的代码类似于
				SecondInner this$2;*/
			}
		}
	}
	public static void main(String[]args){
		MemberStyle outerClass = new MemberStyle();
//		printFields(MemberStyle.class.getDeclaredFields());
//		printMethods(MemberStyle.class.getDeclaredMethods());
		FirstInner innerClass = outerClass.new FirstInner();
//		innerClass.innerMethod();
		printConstructors(innerClass.getClass().getDeclaredConstructors());
		Field[] fields=innerClass.getClass().getDeclaredFields();
//		printFields(fields);
		SecondInner secondInner = innerClass.new SecondInner();
		// 打印类里面的字段内容
//		printFields(secondInner.getClass().getDeclaredFields());
	}
	private static void printFields(Field[] fields){
		
		if(fields!=null){
			for(int i=0;i<fields.length;i++){
				Field field = fields[i];
				//反射里面的方法大致分为三类，判断，获取，设置，，，所以一般先判断，然后再获取或者设置
				System.out.println(field.getName()+"--------"+Modifier.toString(field.getModifiers())+"-----"+field.getType()+"-------"+field.getDeclaringClass());
			}
		}
	}
	private static void printMethods(Method[]methods){
		if(methods!=null){
			System.out.println("method count is "+methods.length);
			for(int i=0;i<methods.length;i++){
				Method method = methods[i];
				
				System.out.println(method.getName()+"--"+Modifier.toString(method.getModifiers())+"----"+method.getReturnType()+"----");
				printParameters(method.getParameterTypes());
				System.out.println();
				
			}
		}
	}
	private static void printParameters(Type[] types){
		if(types!=null){
			for(int i=0;i<types.length;i++){
				Type type = types[i];
				System.out.print("---"+type.toString()+"          ");
			}
			System.out.println();
		}
	}
	public static void printConstructors(Constructor[]constructors){
		if(constructors!=null){
			System.out.println("constructors count is "+constructors.length);
			for(int i=0;i<constructors.length;i++){
				Constructor constructor = constructors[i];
				Type[] types = constructor.getParameterTypes();
				printParameters(types);
				System.out.println();
			}
		}
	}
}
