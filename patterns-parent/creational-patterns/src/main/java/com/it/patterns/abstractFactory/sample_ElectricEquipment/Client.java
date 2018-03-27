package com.it.patterns.abstractFactory.sample_ElectricEquipment;

import java.util.Collection;
import java.util.Map;
import java.util.Set;



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

    private static class test implements Map<String, Object> {

        /*
         * (non-Javadoc)
         * @see java.util.Map#size()
         */
        @Override
        public int size() {
            // TODO Auto-generated method stub
            return 0;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#isEmpty()
         */
        @Override
        public boolean isEmpty() {
            // TODO Auto-generated method stub
            return false;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#containsKey(java.lang.Object)
         */
        @Override
        public boolean containsKey(Object key) {
            // TODO Auto-generated method stub
            return false;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#containsValue(java.lang.Object)
         */
        @Override
        public boolean containsValue(Object value) {
            // TODO Auto-generated method stub
            return false;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#get(java.lang.Object)
         */
        @Override
        public Object get(Object key) {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#put(java.lang.Object, java.lang.Object)
         */
        @Override
        public Object put(String key, Object value) {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#remove(java.lang.Object)
         */
        @Override
        public Object remove(Object key) {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#putAll(java.util.Map)
         */
        @Override
        public void putAll(Map<? extends String, ? extends Object> m) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#clear()
         */
        @Override
        public void clear() {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#keySet()
         */
        @Override
        public Set<String> keySet() {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#values()
         */
        @Override
        public Collection<Object> values() {
            // TODO Auto-generated method stub
            return null;
        }

        /*
         * (non-Javadoc)
         * @see java.util.Map#entrySet()
         */
        @Override
        public Set<java.util.Map.Entry<String, Object>> entrySet() {
            // TODO Auto-generated method stub
            return null;
        }

    }
}
