package com.it.patterns.singleton.eager;


public class EagerSingleton1 {

    private static final EagerSingleton1 ENGER_EAGER_SINGLETON1 = new EagerSingleton1();

    private EagerSingleton1() {
    }

    public EagerSingleton1 getInstance() {
        return ENGER_EAGER_SINGLETON1;
    }
}
