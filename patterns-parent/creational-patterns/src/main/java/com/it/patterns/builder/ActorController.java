package com.it.patterns.builder;


public class ActorController {

    // move into abstract builder
    protected Actor constructActor(ActorBuilder actorBuilder) {
        actorBuilder.buildFace();
        actorBuilder.buildSex();
        actorBuilder.buildType();
        actorBuilder.buildConsume();
        actorBuilder.buildHairStyle();
        return actorBuilder.createActor();

    }
}
