package model;

public class Cat {
    private static final int maxLevel = 100;

    private String name;
    private String breed;
    private Sex sex;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;


    //Constructor
    public Cat(String name, String breed, Sex sex) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        happiness = 50;
        hungerLevel = 50;
        energyLevel = 50;
    }

    public void play(Toy toy) {
        happiness += toy.getAddHappiness();
        energyLevel -= toy.getAddEnergyLevel();
    }

    public void feed(Food food) {
        happiness += food.getAddHappiness();
        hungerLevel += food.getAddHunger();
        energyLevel += food.getAddEnergyLevel();

    }

    public void chat() {
        happiness += 5;
        energyLevel -= 5;
    }


    // getters
    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Sex getSex() {
        return sex;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }
}