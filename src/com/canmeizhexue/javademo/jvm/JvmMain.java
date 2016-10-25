package com.canmeizhexue.javademo.jvm;

import java.lang.reflect.Method;

/**
 * 虚拟机信息测试类
 * @author canmeizhexue
 *
 */
public class JvmMain {
	public static void main(String[]args) {

		
		// 1.引用静态变量，会触发类加载，也会触发类初始化
//		System.out.println("JvmMain --main "+SuperClass.value);
		
		// 2.调用静态方法，会触发类加载，也会触发类初始化
/*		SuperClass.staticMethod();
		System.out.println("JvmMain --main ");*/
		
		// 3.使用反射包的时候，会触发类加载，也会触发类初始化
/*		Class cls = SuperClass.class;
		try {
			Method method = cls.getDeclaredMethod("staticMethod", null);
			method.setAccessible(true);
			method.invoke(null, null);
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
		
		
		// 4.初始化子类时，发现父类没有初始化，会先去初始化父类
//		int b=SubClass.a;
		




		// ******引用class对象，会导致类加载，不一定会导致初始化
//		Class cls = SuperClass.class;
		// ******引用常量，不会触发类加载
//		System.out.println("JvmMain --main "/*+SuperClass.finalInt*/);
		//******  通过子类引用父类的静态变量，只会导致父类初始化，不会导致子类初始化
//		System.out.println(SubClass.value);

		//******  通过子类引用父类的静态变量，只会导致父类初始化，不会导致子类初始化,但是会导致子类的加载
//		SubClass.superStaticMethod();

		//******  只进行声明是不会加载的，
//		SubClass subModel = null;
//		SubClass[] array = new SubClass[4];

		SubClass subClass = new SubClass();
	}
}
