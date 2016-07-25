package com.canmeizhexue.javademo.designpattern.templatemethod;

public class ConcreteSort extends AbstractSort {

	protected void sort(int[] array) {
		 for(int i=0;i<array.length-1;i++){
			 selectSort(array,i);
		 }
	}
	private void selectSort(int[]array,int index){
		int minValue = Integer.MAX_VALUE;
		int indexMin = 0;
		int temp;
		/**
		 * 从特定的位置开始，每次找到最小的一个，记录下这个数据
		 */
		for(int i=index;i<array.length;i++){
			if(array[i]<minValue){
				minValue = array[i];
				indexMin=i;
			}
		}
		temp = array[index];
		array[index]= array[indexMin];
		array[indexMin]=temp;
		
	}

}
