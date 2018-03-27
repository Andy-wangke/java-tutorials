package com.it.patterns.builder;


public abstract class ActorBuilder {

    protected Actor actor = new Actor();

    protected abstract void buildType();

    protected abstract void buildSex();

    protected abstract void buildFace();

    protected abstract void buildConsume();

    protected abstract void buildHairStyle();

    protected Actor createActor() {
        return actor;
    }
}
