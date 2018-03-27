package com.it.patterns.singleton;

/**
 * <pre>
 *  饿汉式单例不能实现延迟加载，始终需要占用内存； 
 *  懒汉式单例线程安全控制繁琐，而且性能受影响(响应速度不及饿汉式,由于需要用到互斥锁和volatile)
 * </pre>
 * 
 * @since 18.03.27
 */
// Initialization on Demand Holder
public class IoDHSingleton {

    private IoDHSingleton() {
    }

    private static class Holder {

        private static IoDHSingleton instance = new IoDHSingleton();
    }

    public static IoDHSingleton getInstance() {
        return Holder.instance;
    }

    // test
    public static void main(String[] args) {
        IoDHSingleton s1, s2;
        s1 = IoDHSingleton.getInstance();
        s2 = IoDHSingleton.getInstance();
        System.out.println(s1 == s2);
    }
}
