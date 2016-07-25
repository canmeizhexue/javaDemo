package com.canmeizhexue.javademo.designpattern.strategy;
/**行为类模式<br>
 * 策略模式，把不同的算法封装到不同的类中，并且这些类实现相同的接口，这些算法可以相互替换<br>
 * 模版方法模式也是对算法的封装，但是调用算法的主体是在抽象的父类中。策略模式调用算法的主体是在另外一个类中。
 * @author canmeizhexue
 *
 */
public class Client {
	/***
	 * 优点：<br>
	 * 		策略类之间可以自由替换<br>
	 * 		易于扩展<br>
	 * 		避免使用多重条件<br>
	 * 缺点：<br>
	 * 		策略类数量不宜过多<br>
	 * 		必须对客户端(使用者)暴露所有的策略类，因为使用哪种策略是由客户端来决定的。客户端还要了解策略类之间的差别。比如排序算法。这一点有点违背迪米特法则<br>
	 * 适用场景：<br>
	 * 		几个类的主要逻辑相同，只在部分逻辑的算法和行为上稍有区别的情况<br>
	 * 		有几种相似的行为，或者说算法，客户端需要动态的决定使用哪一种<br>
	 * 
	 * 注意：<br>
	 * 策略模式一般不会单独使用，跟模版方法，工厂模式等混合使用的情况比较多
	 * @param args
	 */
	public static void main(String[]args){
		Context context;
		System.out.println("------执行策略1----");
		context = new Context(new ConcreteStrategy1());
		context.execute();
		
		System.out.println("------策略模式2-------");
		context = new Context(new ConcreteStrategy2());
		context.execute();
	}

}
