package com.canmeizhexue.javademo.threads;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakMapTest {
	/**
	 * 当键不再被引用而被回收后，这个map中会将对应的条目删除
	 * @param args
	 */
	public static void main(String[]args){
		Map map = new WeakHashMap();
		//因为很快MyKey对象没被其他地方引用
		map.put(new MyKey("helloworld"), "12345");
		//字符串类型的key却不会被回收
		map.put("wekmap", "map test");
		map.put(new MyKey("3f3khgf"), "3evfe3");
		/**
		 * 同样是字符串，可以看见这俩个表现出来的行为是不一样的
		 * stringa会被回收，stringb对应的项不会从map中删除，尽管将stringb赋值为null
		 */
		String stringa = new String("aaaaa");
		map.put(stringa, "valueaaaaaaaaaaaaaaaaaaaa");
		stringa=null;
		
		String stringb = "bbbbbbbbbbbb";
		map.put(stringb, "valuebbbbbbbbbbb");
		stringb=null;
		
		printMap(map);
		for(int i=0;i<20/*000*/;i++){
			System.gc();
/*			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			if(map.isEmpty()){
				System.out.println("map is empty  "+i);	
				System.exit(-1);
			}
			System.out.println("current loop is  "+i);
			printMap(map);
		}
		System.out.println("test end ");
	}
	public static void printMap(Map map){
		Set set=map.keySet();
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			Object key = iterator.next();
			System.out.println("key is "+key+"   value is "+map.get(key));
		}
	}
	public static class MyKey{
		String key;
		
		public MyKey(String key) {
			super();
			this.key = key;
		}

		public String toString() {
			 
			return key;
		}
	}
}
