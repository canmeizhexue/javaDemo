package com.canmeizhexue.javademo.designpattern.strategy;
/**
 * 模拟具体的策略算法
 * @author canmeizhexue
 *
 */
public class ConcreteStrategy1 implements IStrategy {

	public void doSomething() {
		System.out.println("具体策略1---doSomething");
	}

}
