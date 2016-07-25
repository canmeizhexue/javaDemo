package com.canmeizhexue.javademo.designpattern.templatemethod;
/**
 * 定义一个操作中算法的框架，将其中的某些步骤延迟到子类，使得子类可以不改变算法的结构即可重定义该算法中的某些特定步骤<br>
 * 属于行为类<br>
 * 结构<br>
 * 模版方法模式由一个抽象类和一组实现类通过继承结构组成，抽象类中的方法分为三种:
 * 		抽象方法：父类只声明不实现，由子类去实现<br>
 * 		模板方法：由抽象类声明并加以实现。一般来说，模板方法调用抽象方法来完成主要的逻辑功能，模板方法定义为final，不能被子类重写。<br>
 * 		钩子方法:由抽象类声明并加以实现。子类可以通过扩展钩子方法来影响模板方法的逻辑。<br>
 * 	抽象类的任务是搭建逻辑的框架，实现类用来实现细节。<br>
 * 优点:<br>
 * 抽象类中的模板方法是不易发生改变的部分，而抽象方法是容易发生变化的部分。<br>
 * 因为有钩子方法，子类的实现也可以影响父类中主逻辑的运行。<br>
 * 适用场景:<br>
 * 当多个子类拥有相同的方法，并且这些方法逻辑相同时，可以考虑使用模板方法模式。在程序的主框架相同，细节不同的场合下，也比较适合使用这种模式。
 * 
 * 
 * @author canmeizhexue
 *
 */
public abstract class AbstractSort {
	/**
	 * 具体的排序逻辑由子类来实现
	 * @param array
	 */
	protected abstract void sort(int[]array);
	/**
	 * 先排序，然后打印排序后的结果，这个总体的算法框架不变<br>
	 * 排序可以由不同的算法实现，所以这些可以推迟到子类实现。
	 * 
	 * @param array
	 */
	public void showSortResult(int[]array){
		sort(array);
		System.out.print("排序结果:");
		for(int i=0;i<array.length;i++){
			System.out.print("  "+array[i]);
		}
	}
}
