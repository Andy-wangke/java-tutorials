package com.it.patterns.factoryMethod.sample_TV;


public class HisenseTVFactory implements TvFactory {

    @Override
    public TV produceTV() {
        System.out.println("Hisense Factory produce Hisense TV");
        return new HisenseTV();
    }

}
