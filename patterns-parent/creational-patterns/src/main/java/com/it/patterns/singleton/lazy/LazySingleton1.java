package com.it.patterns.singleton.lazy;

/**
 * <pre>
 * issue:
 * 
 * </pre>
 * 
 * @author ke.wang@hpe.com
 */
public class LazySingleton1 {

    private static LazySingleton1 INSTANCE = null;

    private LazySingleton1() {
    }

    // FIXME still exist concurrent issue
    synchronized public LazySingleton1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton1();
        }
        return INSTANCE;
    }
}
