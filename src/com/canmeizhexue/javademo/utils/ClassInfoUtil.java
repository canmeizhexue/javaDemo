package com.canmeizhexue.javademo.utils;

/**打印类的相关信息
 * Created by zengyaping on 2016-10-8.
 */
public class ClassInfoUtil {
    /**
     * 打印父类和接口信息
     * @param cls
     */
    public static void printSuperClass(Class cls){
        if(cls!=null){
            System.out.println("##############"+cls.getName());
            Class[] interfaces=cls.getInterfaces();
            if(interfaces!=null){
                for(int i=0;i<interfaces.length;i++){
                    printSuperClass(interfaces[i]);
                }
            }
            Class superClass=cls.getSuperclass();
            printSuperClass(superClass);

        }
    }
}
