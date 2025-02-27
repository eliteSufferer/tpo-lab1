package org.example.task3;

public class EvaporationEvent {
    public void triggerEvaporation(Person person) {
        if (person.getState() == EvaporationState.ALIVE) {
            person.evaporate();
            System.out.println("[ИСПАРЕНИЕ] " + person.getName() + " превращается в облачко газов!");
        }
    }

    public void logTimePassing(int seconds) {
        System.out.println("[ВРЕМЯ] Прошло " + seconds + " секунд");
    }
}
