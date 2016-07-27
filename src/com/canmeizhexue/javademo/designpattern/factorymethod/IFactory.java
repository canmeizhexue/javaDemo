package com.canmeizhexue.javademo.designpattern.factorymethod;
/**
 * 工厂类的基类，一般只有一个生产产品的方法
 * @author canmeizhexue
 *
 */
public abstract class IFactory {
	public abstract IProduct createProduct();
}
