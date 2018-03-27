package com.it.patterns.builder;


public class Client {

    public static void main(String[] args) {
        ActorController actorController = new ActorController();

        // ActorBuilder actorBuilder = new AngelBuilder();// specific
        ActorBuilder actorBuilder = new HeroBuilder();

        Actor actor = actorController.constructActor(actorBuilder);

        System.out.println(actor);
    }
}
