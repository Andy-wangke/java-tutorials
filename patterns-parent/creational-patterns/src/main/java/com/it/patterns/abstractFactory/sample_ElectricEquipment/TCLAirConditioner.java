package com.it.patterns.abstractFactory.sample_ElectricEquipment;

public class TCLAirConditioner implements AirConditioner {

    @Override
    public void changeTemperature() {
        System.out.println("TCL air conditioner can change temperature..");
    }

}
