package org.example.task3;

public class Person {
    private String name;
    private EvaporationState state = EvaporationState.ALIVE;

    public Person(String name) {
        this.name = name;
    }

    public void evaporate() {
        this.state = EvaporationState.EVAPORATED;
    }

    public EvaporationState getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}