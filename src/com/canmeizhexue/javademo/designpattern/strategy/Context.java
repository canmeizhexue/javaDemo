package com.canmeizhexue.javademo.designpattern.strategy;
/**
 * 对策略类进行二次封装，避免高层模块对策略的直接调用
 * @author canmeizhexue
 *
 */
public class Context {
	private IStrategy strategy;

	public Context(IStrategy strategy) {
		super();
		this.strategy = strategy;
	}
	public void execute(){
		strategy.doSomething();
	}

}
