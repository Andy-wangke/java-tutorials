package com.it.patterns.abstractFactory.sample_ElectricEquipment;


public class HaierFactory extends EquipmentFactory {

    @Override
    TV produceTV() {
        System.out.println("Haier produces Tv...");
        return new HaierTV();
    }

    @Override
    AirConditioner produceAirConditioner() {
        System.out.println("Haier produces Air conditioner...");
        return new HaierAirConditioner();
    }

}
