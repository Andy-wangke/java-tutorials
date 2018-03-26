package com.it.patterns.factoryMethod.sample_TV;


public class HaierTVFactory implements TvFactory {

    @Override
    public TV produceTV() {
        System.out.println("Haiver factory produce Haiver TV");
        return new HaierTV();
    }

}
