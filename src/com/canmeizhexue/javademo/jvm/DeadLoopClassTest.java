package com.canmeizhexue.javademo.jvm;

/** // 互斥访问类的初始化部分，类的初始化部分只会执行一次。
 * Created by zengyaping on 2016-9-1.
 */
public class DeadLoopClassTest {

    public static void main(String[] args){
        System.out.println(Thread.currentThread()+" main function");
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+" start");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread()+" end");
            }
        };
        new Thread(script).start();
        new Thread(script).start();
    }
    public static class DeadLoopClass{
        static {
            if(true){
                // 不加这个if语句，编译不通过
                System.out.println(Thread.currentThread()+" init DeadLoopClass");
                while (true){}
            }

        }
    }
}
