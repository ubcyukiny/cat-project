# Virtual Cat

## Application that lets you interact with a cat!

I played *Neopets* and *Pet Society* when I was young, and I wanted to recreate a virtual model.pet game for this project.

What this application can do:
- Login using your own username (**changed to auto save and auto load**)
- Create a cat, and associate with the user account 
- Buy consumables from shop
- Feed, play, with your cat using consumables!


## User Stories P1
- As a user, I want to be able to create my cat and add it to user 

- As a user, I want to be able to buy food from shop (adding multiple X to Y)

- As a user, I want to be able to feed my cat using food bought from shop

- As a user, I want to be able to check my Inventory

- As a user, I want to be able to view my cat's status(happiness, hunger level, energy level)

## User Stories P2
- As a user, I want to be able to save my user data to file 
    (saved automatically when quitting game)

- As a user, I want to be able to load my user data from file
    (loaded automatically when start up game)
    
- As a user, I want to see more realism by adding time and stat decay in Cat...

## User Stories P3
- Can Feed, Purchase Food, and View Food in Gui

- When auto save, or load, pop up message will indicate saving to/from "./data/user.json" or not found
    status change and persistance cant be found in status panel
    
- When cat happiness reaches >= 70 or >= 90, new cat icons will appear and replace old

## User Stories P4
- Task 2: Make appropriate use of a bi-directional association somewhere in your code. 

    I have added a bi-directional association between Class Cat and User, when user addCat to his field, cat will also
    add user to owner. The method is addCat(Cat cat) in model.User and addOwner(User user) in model.Cat. Check is 
    implemented to guard against potential infinite loop, user.addCat(cat) will have a check if myPet.equals(cat), same for
    cat.addOwner(user). Intelij generated override equals and hashcode for both classes.  
    
    

- Task 3: 
I would separate PetApp (Game logic) and the GUI, and make a new class called GUI, because of the Single Responsibility
Principle. 

    Currently, in PetApp, it's runs the GUI, do game actions, and save load using the JsonReader and JsonWriter class. 

    I would extract GUI related fields and methods, like JFrame, JLabel Jbutton. The PetApp will have a GUI field and 
    the constructor would then initialize GUI inside PetApp like this:

    void PetApp(){
        gui = new GUI(this); 
    }

    And in GUI class, we would make a PetApp field, and the constructor of GUI includes a PetApp parameter, so it can 
    uses PetApp's field and method.

    When GUI "listens" player's actionEvent, it will call PetApp's method, using the PetApp field. It looks like this:

    public class GUI {
        private PetApp game;
        
        private void actionPerformedResponse (ActionEvent e) {
            game.doResponse();
        } 
    }

- MINOR REFACTORINGS 


  1. Currently, in shop class, theres a list of Food and 3 Food objects in the field, the constructor would then add
    the 3 objects in the List<Food>.


    Inside Shop class, in constructor method, add a method called initialize catalogue, inside the method, it will use 
    list.add(new Food (...)); 
    so we can modify the shop catalogue in the initalize catalogue method, not in the fields of Shop





  2. In the User class, there is a createRandomCat method that randomly chooses a name from a list of breed names, and 
    create a cat object based on the breed name. 
    
    
    I would extract a randomizeBreed method from it, remove the createRandomCat method in User class, and add 
    randomizeBreed method in Cat class constructor.






  3. In PetApp class, there are getShopCatalogue, displayBalance, and displayLastLogin methods to return formatted 
    Strings to put inside JLabel for the Gui. 
    
    
    These methods should be moved to their own class, getShopCatalogue to Shop class, displayBalance and 
    displayLastLogin to User class. The proposed new GUI class would call these methods from their own classes. 

 

