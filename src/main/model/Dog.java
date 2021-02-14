package model;

public class Dog implements Pet {
    private String name;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;
    private boolean canInteract;


    //Constructor
    public Dog(String name) {
        this.name = name;
        happiness = 75;
        hungerLevel = 20;
        energyLevel = 70;
        canInteract = true;
    }


    @Override
    public void play() {
        if (canInteract) {
            happiness += 15;
            energyLevel -= 30;
        } else {
            happiness -= 5;
            energyLevel -= 20;
        }

    }

    @Override
    public void feed() {
        if (canInteract) {
            happiness += 5;
            hungerLevel += 20;
            energyLevel += 20;
        } else {
            happiness -= 5;
            energyLevel -= 20;
        }
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
