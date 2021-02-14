package model;

public class Cat implements Pet {
    private static final int maxLevel = 100;

    private String name;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;
    private boolean canInteract;

    //Constructor
    public Cat(String name) {
        this.name = name;
        happiness = 50;
        hungerLevel = 50;
        energyLevel = 50;
    }

    @Override
    public void play() {
        happiness += 15;
        energyLevel -= 30;
    }

    @Override
    public void feed() {
        happiness += 5;
        hungerLevel += 20;
        energyLevel += 20;
    }

    @Override
    public void chat() {
        happiness += 5;
        energyLevel -= 5;
    }

    @Override
    public void sleep() {
        canInteract = false;
        energyLevel = maxLevel;
        hungerLevel -= 20;
    }
}