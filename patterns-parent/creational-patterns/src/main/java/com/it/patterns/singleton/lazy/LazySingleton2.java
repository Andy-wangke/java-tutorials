package com.it.patterns.singleton.lazy;

/**
 * <pre>
 * issue:
 * 
 * </pre>
 * 
 * @author ke.wang@hpe.com
 */
public class LazySingleton2 {

    //
    private static volatile LazySingleton2 INSTANCE = null;

    private LazySingleton2() {
    }

    public LazySingleton2 getInstance() {
        // Double-Check Locking
        if (INSTANCE == null) {
            synchronized (LazySingleton2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton2();
                }
            }

        }
        return INSTANCE;
    }
}
