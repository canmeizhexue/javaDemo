package com.canmeizhexue.javademo.jvm;

/** // ���������ĳ�ʼ�����֣���ĳ�ʼ������ֻ��ִ��һ�Ρ�
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
                // �������if��䣬���벻ͨ��
                System.out.println(Thread.currentThread()+" init DeadLoopClass");
                while (true){}
            }

        }
    }
}
