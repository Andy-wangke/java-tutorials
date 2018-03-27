package com.it.patterns.builder;


public class DevilBuilder extends ActorBuilder {

    @Override
    protected void buildType() {
        actor.setType("Devil");

    }

    @Override
    protected void buildSex() {
        actor.setSex("妖");

    }

    @Override
    protected void buildFace() {
        actor.setFace("丑陋");

    }

    @Override
    protected void buildConsume() {
        actor.setCostume("黑衣");

    }

    @Override
    protected void buildHairStyle() {
        actor.setHairStyle("光头");

    }

}
