package model.Pets;

public class Cat implements Pet{
    private String name;
    private int happiness;
    private int hungerLevel;
    private int energyLevel;



    @Override
    public void greet() {
        System.out.println("MEOW~~~~~");
    }

    @Override
    public void expressHungry() {

    }

    @Override
    public void expressFull() {

    }

    @Override
    public void expressBored() {

    }

    @Override
    public void expressTired() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void printPet() {

    }
}
