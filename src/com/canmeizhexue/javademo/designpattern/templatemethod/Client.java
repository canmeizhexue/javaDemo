package com.canmeizhexue.javademo.designpattern.templatemethod;

public class Client {
	public static int[]a = {10,32,1,9,5,7,12,0,4,3};
	public static void main(String[]args){
		AbstractSort s = new ConcreteSort();
		s.showSortResult(a);
	}
}
