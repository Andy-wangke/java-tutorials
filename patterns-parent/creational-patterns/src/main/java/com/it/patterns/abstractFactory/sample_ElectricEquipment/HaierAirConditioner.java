package com.it.patterns.abstractFactory.sample_ElectricEquipment;


public class HaierAirConditioner implements AirConditioner {

    @Override
    public void changeTemperature() {
        System.out.println("HaierAirConditioner can change temperature...");

    }

}
