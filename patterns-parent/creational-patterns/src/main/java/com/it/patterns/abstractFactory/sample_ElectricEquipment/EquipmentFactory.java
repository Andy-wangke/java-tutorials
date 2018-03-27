package com.it.patterns.abstractFactory.sample_ElectricEquipment;


/**
 * same as interface
 * 
 * @since 18.03.26
 */
public abstract class EquipmentFactory {

    abstract TV produceTV();

    abstract AirConditioner produceAirConditioner();
}
