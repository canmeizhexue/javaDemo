package com.canmeizhexue.javademo.designpattern.strategy;
/**
 * 策略模式的接口，用来定义规范。当具体策略子类有共同代码逻辑的时候，可以考虑将这个类改成抽象基类。<br>
 * 然后将共有的代码提取到这个抽象基类当中。
 * @author canmeizhexue
 *
 */
public interface IStrategy {
	void doSomething();
}
