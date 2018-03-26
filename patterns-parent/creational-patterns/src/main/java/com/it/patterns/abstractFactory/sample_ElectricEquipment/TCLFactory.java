package com.it.patterns.abstractFactory.sample_ElectricEquipment;


public class TCLFactory extends EquipmentFactory {

    @Override
    TV produceTV() {
        System.out.println("TCL produce TV...");
        return new TCLTV();
    }

    @Override
    AirConditioner produceAirConditioner() {
        System.out.println("TCL produce air conditioner...");
        return new TCLAirConditioner();
    }

}
