package cn.memedai.common.toolkit.classloader;

import java.util.Random;

/**
 * Created by chengtx on 2016/9/7.
 * 输出只有2,没有输出static代码块里面的内容,因为,x变量是final类型的
 */
public class Test {




    public static void main(String[] args) {
        System.out.println(FinalTest.x);
    }
}


class FinalTest{

    public static final int x = 6/3;

    static {
        System.out.println("FinalTest static block");
    }
}