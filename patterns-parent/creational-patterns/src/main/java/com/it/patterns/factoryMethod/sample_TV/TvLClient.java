package com.it.patterns.factoryMethod.sample_TV;


public class TvLClient {

    static TvFactory tvFactory = null;

    public static void main(String[] args) {
        TV tv = null;
        /**
         * <pre>
         *  problems:
         *      1.
         * </pre>
         */
        tvFactory = new HaierTVFactory();
        tv = tvFactory.produceTV();
        tv.play();
    }
}
