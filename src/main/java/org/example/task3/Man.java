package org.example.task3;

public class Man extends Person {
    public Man(String name) {
        super(name);
    }

    public void laugh() {
        System.out.println(getName() + " громко смеется");
    }
}
