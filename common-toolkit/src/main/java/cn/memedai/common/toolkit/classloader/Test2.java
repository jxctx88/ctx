package cn.memedai.common.toolkit.classloader;

/**
 * Created by chengtx on 2016/9/8.
 * 查看类加载器的父子关系
 */
public class Test2 {

    public static void main(String[] args) {
        ClassLoader classLoader =  ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (null != classLoader){
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }



}
