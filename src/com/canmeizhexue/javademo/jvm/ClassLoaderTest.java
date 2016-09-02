package com.canmeizhexue.javademo.jvm;


import java.io.InputStream;

/**类加载测试
 * 类加载器和class对象来唯一确定一个类，也就是说一个类在不同的加载器得到的Class对象是不一样的。
 * Created by canmeizhexue on 2016-9-2.
 */
public class ClassLoaderTest {
    static {
        System.out.println("ClassLoaderTest-------init");
    }
    public static void main(String[] args)throws Exception{
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is==null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch (Exception e){
                    throw  new ClassNotFoundException(name);
                }
            }
        };
        Class cls2 = myLoader.loadClass("com.canmeizhexue.javademo.jvm.ClassLoaderTest");

        Object object = cls2.newInstance();
        System.out.println(cls2==ClassLoaderTest.class);
        System.out.println(object.getClass());
        System.out.println(object instanceof ClassLoaderTest);
    }
}
