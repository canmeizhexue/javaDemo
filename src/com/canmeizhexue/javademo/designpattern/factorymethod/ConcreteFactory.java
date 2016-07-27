package com.canmeizhexue.javademo.designpattern.factorymethod;
/**
 * 具体的工厂类，负责生产某个具体的产品
 * @author canmeizhexue
 *
 */
public class ConcreteFactory extends IFactory {

	public IProduct createProduct() {
		return new ConcreteProduct();
	}

}
