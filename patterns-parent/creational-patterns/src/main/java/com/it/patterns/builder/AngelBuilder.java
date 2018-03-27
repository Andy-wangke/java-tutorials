package com.it.patterns.builder;


public class AngelBuilder extends ActorBuilder {

    @Override
    protected void buildType() {
        actor.setType("Angel");

    }

    @Override
    protected void buildSex() {
        actor.setSex("female");

    }

    @Override
    protected void buildFace() {
        actor.setFace("beautiful");

    }

    @Override
    protected void buildConsume() {
        actor.setCostume("long dress");

    }

    @Override
    protected void buildHairStyle() {
        actor.setHairStyle("披肩长发");

    }

}
