package com.canmeizhexue.javademo.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MapTest {
	static Map<String,Map<String,MyKey>> staticMap;
	static String name="silence";
	static String newValue="new value";
	static int loop = /*Integer.MAX_VALUE*/1000000/*Integer.MAX_VALUE/2/2/2/2/400000*/;
	static boolean changed = false;
	static CountDownLatch countDownLatch = new CountDownLatch(1);
	static CountDownLatch countDownLatch2 = new CountDownLatch(20);
	public static void main(String[]args){
		for(int i=0;i<20;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					test(Thread.currentThread()+"   : ",loop);
					
				}
			}).start();
		}
		//很奇怪，为什么让子线程先获取MyKey对象后，再让主线程更新，再唤醒子线程，这个时候子线程居然还能获取到正确的值，，
		//按照之前的理解，应该是子线程拿到的还是之前的缓存，而不是最新值，难道是因为缓存和主存还有其他的刷新机制？
		try {
			countDownLatch2.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateValue(name,newValue);

//		test("main thread : ",loop);
		countDownLatch.countDown();
		
	}
	public static void test(String logtag,int loop){
		MyKey myKey = getMyKey(name);
		countDownLatch2.countDown();
		try {
			System.out.println(logtag+"-----------await");
			countDownLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean tempFlag=false;
		boolean loged = false;
		boolean logedhaha=false;
		for(int i=0;i<loop;i++){
/*			synchronized (MapTest.class) {
				tempFlag = changed;
			}*/
			if(!loged && tempFlag){
				System.out.println(logtag+"-----------"+tempFlag);
			}
			loged=true;
			//去掉下面这个同步代码块，有些能取到正确的值，有些取不到
//			synchronized (MapTest.class) {
				
//				System.out.println(logtag+myKey);
				if(myKey!=null){
					if(!newValue.equals(myKey.key)){
						System.out.println(logtag+"  find not equals----"+System.currentTimeMillis()+"-----------"+tempFlag);
						
						if(tempFlag){
							System.out.println(logtag+"  find not equals-------exit"+System.currentTimeMillis());
							System.exit(-1);
						}

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						
					}else{
						if(!logedhaha){
							System.out.println(logtag+" equals 哈哈哈哈");
						}
						logedhaha=true;
//						
					}
//					System.out.println(logtag+" current loop is "+i+"  "+myKey.key);
				}else{
					System.out.println("find null , exit");
					System.exit(-1);
				}
//			}


		}
		System.out.println(logtag+"end !!");
	}
	public static void updateValue(String name,String newValue){
		MyKey myKey = getMyKey(name);
		System.out.println("我要改变值了，你们要跟我的一样"+System.currentTimeMillis());
		myKey.key=newValue;
		synchronized (MapTest.class) {
			changed = true;

		}
	}
	public static MyKey getMyKey(String name){
		synchronized (MapTest.class) {
			if(staticMap==null){
				staticMap = new HashMap<>();
			}
			String keyname="canmeizhexue";
			Map<String ,MyKey> map = staticMap.get(keyname);
			if(map==null){
				map = new HashMap<>();
				staticMap.put(keyname, map);
			}
			MyKey myKey = map.get(name);
			if(myKey==null){
				myKey = new MyKey("helloworld");
				map.put(name, myKey);
			}
			return myKey;
		}
	}
	public static class MyKey{
		String key;
		
		public MyKey(String key) {
			super();
			this.key = key;
		}

/*		public String toString() {
			 
			return key;
		}*/
	}
}
