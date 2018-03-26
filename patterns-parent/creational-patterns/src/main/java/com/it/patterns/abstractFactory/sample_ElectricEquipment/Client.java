package com.it.patterns.abstractFactory.sample_ElectricEquipment;



public class Client {

    static EquipmentFactory factory = null;

    public static void main(String[] args) {
        factory = new HaierFactory();
        // TODO: configured in XML
        AirConditioner airConditioner = factory.produceAirConditioner();

        airConditioner.changeTemperature();
        TV tv = factory.produceTV();
        tv.play();

    }
}
