package com.it.patterns.builder;


public class HeroBuilder extends ActorBuilder {

    @Override
    protected void buildType() {
        actor.setType("Hero");

    }

    @Override
    protected void buildSex() {
        actor.setSex("male");

    }

    @Override
    protected void buildFace() {
        actor.setFace("handsome");

    }

    @Override
    protected void buildConsume() {
        actor.setCostume("armour");

    }

    @Override
    protected void buildHairStyle() {
        actor.setHairStyle("飘逸");

    }


}
