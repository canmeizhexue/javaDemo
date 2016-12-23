package com.canmeizhexue.javademo.dynamicproxy;

import com.canmeizhexue.javademo.utils.ClassInfoUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试<br><br/>
 * 调用动态代理类的方法才会有效果，可以考虑通过反射等手段用动态代理类对象替换掉被代理的对象，比如通过这种方式更改android sdk里面的执行行为，但是sdk里面有强转的话就可能报错<br><br/>
 *
 * jdk的动态代理机制要求被代理类必须实现了接口，因为jdk的动态代理是基于接口的,并且动态代理类是java.lang.Proxy的子类 <br><br/>
 *
 * 有其他的工具可以动态生产类，甚至动态操作字节码文件，比如CGLIB,Javassist,ASM
 *
 * http://www.ibm.com/developerworks/cn/java/j-lo-proxy1/
 * http://blog.csdn.net/luanlouis/article/details/24589193
 * Created by zengyaping on 2016-10-8.
 */
public class DynamicProxyTest {


    public static void main(String[] args) {

        // 被代理类的实例
        AbstractSubject realSubject = new RealSubject();

        // 获得被代理类的类加载器，使得JVM能够加载并找到被代理类的内部结构，以及已实现的interface
        ClassLoader loader = realSubject.getClass().getClassLoader();

        // 获得被代理类已实现的所有接口interface,使得动态代理类的实例
        Class<?>[] interfaces = realSubject.getClass().getInterfaces();

        // 用被代理类的实例创建动态代理类的实例，用于真正调用处理程序
        InvocationHandler handler = new DynamicProxy(realSubject);

        /*
         * loader : 被代理类的类加载器
         * interfaces ：被代理类已实现的所有接口，而这些是动态代理类要实现的接口列表
         * handler ： 用被代理类的实例创建动态代理类的实例，用于真正调用处理程序
         *
         * return ：返回实现了被代理类所实现的所有接口的Object对象，即动态代理，需要强制转型
         */
        //获得代理的实例
        AbstractSubject proxy = (AbstractSubject) Proxy.newProxyInstance(
                loader, interfaces, handler);
        InvocationHandler invocationHandler=Proxy.getInvocationHandler(proxy);
        System.out.println(handler==invocationHandler);
        ClassInfoUtil.printSuperClass(proxy.getClass());


        //注意这个地方调用动态代理对象，才会起作用，如果可以将动态代理对象通过反射等机制将被代理对象替换，那么就根本上进行了代理，不过这种方式有风险，框架里面一旦进行强制转换就会出错的
        proxy.request();
        // 注意这个地方调用被代理对象的方法是不会有效果的
//        realSubject.request();
        //打印出该代理实例的名称



        System.out.println(proxy.getClass().getName());
    }
}

//抽象主题类,这里不能用abstract抽象类，一定要是interface
interface AbstractSubject {
    public abstract void request();
}

// 真实主题类，即被代理类
class RealSubject implements AbstractSubject {
    public void request() {
        System.out.println("RealSubject's request() ...");
    }
}

// 动态代理类，实现InvocationHandler接口
class DynamicProxy implements InvocationHandler {

    // 被代理类的实例
    Object obj = null;

    // 将被代理者的实例传进动态代理类的构造函数中
    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    /**
     * 覆盖InvocationHandler接口中的invoke()方法
     * <p>
     * 更重要的是，动态代理模式可以使得我们在不改变原来已有的代码结构
     * 的情况下，对原来的“真实方法”进行扩展、增强其功能，并且可以达到
     * 控制被代理对象的行为，下面的before、after就是我们可以进行特殊
     * 代码切入的扩展点了。
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        /*
         * before ：doSomething();
         */
        System.out.println("DynamicProxy's prerequest ..."+proxy.getClass().getName());
        Thread.sleep(1000);
        // 如果这个地方传入proxy，那么会进入死循环,查看InvocationHandler的文档，它写的是proxy,可见文档也不一定对
//        Object result = method.invoke(proxy, args);
        Object result = method.invoke(this.obj, args);
        /*
         * after : doSomething();
         */
        System.out.println("DynamicProxy's afterrequest ...");
        return result;
    }
}
