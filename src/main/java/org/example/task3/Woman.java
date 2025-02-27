package org.example.task3;

public class Woman extends Person {
    private boolean hatesMan;

    public Woman(String name) {
        super(name);
    }

    public void hate(Man man) {
        this.hatesMan = true;
        System.out.println(getName() + " ненавидит " + man.getName());
    }

    public boolean isHatesMan() {
        return hatesMan;
    }
}
