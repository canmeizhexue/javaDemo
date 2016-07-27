package com.canmeizhexue.javademo.designpattern.abstractfactory;
/**
 * 特点：
 * 1.具有多种类型并且有关联关系的产品，也就是说工厂接口有多个方法来产生不同类型的产品。
 * 2.会存在多个不同类型的具体的工厂，不同类型的产品的属性进行自由组合得到不同的产品。
 * 3.对比工厂方法模式，工厂接口多了若干个接口方法用来生产其他类型的产品。工厂方法模式与简单工厂方法相比是多了一个工厂接口类。
 * @author canmeizhexue
 *
 */
public class Client {
	public static void main(String[]args){
		IFactory factory = new Factory();
		factory.createProduct1().show();
		factory.createProduct2().show();
	}
}
