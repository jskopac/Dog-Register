
//Jennifer Skopac jesk3432
import java.util.ArrayList;

public class DogRegister {
    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();
    private InputReader input = new InputReader(System.in);

    private static final String REGISTER_NEW_DOG = "register new dog";
    private static final String REMOVE_DOG = "remove dog";
    private static final String REGISTER_NEW_OWNER = "register new owner";
    private static final String REMOVE_OWNER = "remove owner";
    private static final String LIST_DOGS = "list dogs";
    private static final String LIST_OWNERS = "list owners";
    private static final String INCREASE_AGE = "increase age";
    private static final String GIVE_DOG_TO_OWNER = "give dog to owner";
    private static final String REMOVE_DOG_FROM_OWNER = "remove dog from owner";
    private static final String EXIT = "exit";

    public void start() {
        printCommands();
        runCommandoLoop();
        shutDown();
    }

    private void printCommands() {
        System.out.println("Welcome to the dog register!");
        System.out.println("The following commands are available:");
        System.out.println("* " + REGISTER_NEW_DOG);
        System.out.println("* " + REMOVE_DOG);
        System.out.println("* " + REGISTER_NEW_OWNER);
        System.out.println("* " + REMOVE_OWNER);
        System.out.println("* " + LIST_DOGS);
        System.out.println("* " + LIST_OWNERS);
        System.out.println("* " + INCREASE_AGE);
        System.out.println("* " + GIVE_DOG_TO_OWNER);
        System.out.println("* " + REMOVE_DOG_FROM_OWNER);
        System.out.println("* " + EXIT);
        System.out.println();
        System.out.println();
    }

    private void runCommandoLoop() {
        String command;
        do {
            command = readCommand();
            executeCommand(command);
        }

        while (!command.equalsIgnoreCase(EXIT));
    }

    private String readCommand() {
        return input.readString("Enter command");
    }

    private void executeCommand(String command) {
        switch (command.trim().toLowerCase()) {
            case REGISTER_NEW_DOG:
                registerNewDog();

                break;

            case REMOVE_DOG:
                removeDog();
                break;

            case REGISTER_NEW_OWNER:
                registerNewOwner();
                break;

            case REMOVE_OWNER:
                removeOwner();
                break;

            case LIST_DOGS:
                listDogs();
                break;

            case LIST_OWNERS:
                listOwners();
                break;

            case INCREASE_AGE:
                increaseAge();
                break;

            case GIVE_DOG_TO_OWNER:
                giveDogToOwner();

                break;

            case REMOVE_DOG_FROM_OWNER:
                removeDogFromOwner();
                break;

            case EXIT:
                break;

            default:
                printError(command);
        }

    }

    private boolean isInputBlank(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) {
            System.out.println("Error:A blank string is not allowed, try again");
            return true;
        }

        return false;
    }

    private void registerNewDog() {
        String dogName = input.readString("Enter dog name");
        while (isInputBlank(dogName)) {
            dogName = input.readString("Enter dog name");
        }

        if (dogCollection.containsDog(dogName)) {
            System.out.println("Error: The dog " + dogName + " already exists");
        }

        else {
            String dogBreed = input.readString("Enter dog breed");
            while (isInputBlank(dogBreed)) {
                dogBreed = input.readString("Enter dog breed");
            }

            int dogAge = input.readInt("Enter dog age");
            int dogWeight = input.readInt("Enter dog weight");

            doRegisterDog(dogName, dogBreed, dogAge, dogWeight);
        }
    }

    private void doRegisterDog(String dogName, String dogBreed, int dogAge, int dogWeight) {
        Dog newDog = new Dog(dogName, dogBreed, dogAge, dogWeight);
        dogCollection.addDog(newDog);
        System.out.println("The dog " + newDog.getName() + " has been added to the register");
    }

    private void removeDog() {

        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
        }

        else {
            String dogName = input.readString("Enter dog name");
            Dog dogToRemove = findDog(dogName);
            if (dogToRemove != null) {
                doRemoveDog(dogToRemove);
            }
        }

    }

    private void doRemoveDog(Dog dogToRemove) {
        if (dogToRemove.getOwner() != null)
            dogToRemove.getOwner().removeDog(dogToRemove);
        dogCollection.removeDog(dogToRemove);
        System.out.println("The dog " + dogToRemove.getName() + " has been removed");
    }

    private void registerNewOwner() {
        String ownerName = input.readString("Enter owner name");

        while (isInputBlank(ownerName)) {
            ownerName = input.readString("Enter owner name");
        }

        doRegisterOwner(ownerName);
    }

    private void doRegisterOwner(String ownerName) {
        Owner newOwner = new Owner(ownerName);
        if (ownerCollection.addOwner(newOwner)) {
            System.out.println("The owner " + newOwner.getName() + " has been added to the register");
        }

        else {
            System.out.println("Error: The owner " + newOwner.getName() + " already exists");
        }

    }

    private void removeOwner() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
            return;
        }

        String ownerName = input.readString("Enter owner name");
        Owner ownerToRemove = findOwner(ownerName);

        if (ownerToRemove != null) {
            doRemoveOwner(ownerToRemove);
        }
    }

    private void doRemoveOwner(Owner ownerToRemove) {
        if (!ownerToRemove.getDogs().isEmpty()) {
            for (Dog dog : ownerToRemove.getDogs()) {
                ownerToRemove.removeDog(dog);
                dogCollection.removeDog(dog);
            }
        }
        ownerCollection.removeOwner(ownerToRemove);
        System.out.println("The owner " + ownerToRemove + " has been removed");
    }

    private void listDogs() {

        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
        }

        else {
            double tailLength = input.readDouble("Enter minimum tail length");
            System.out.println(dogCollection.sortByTailLength(tailLength));
        }
    }

    private void listOwners() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
        } else {
            for (Owner owner : ownerCollection.getOwners()) {
                System.out.println(owner.getName() + " " + dogNames(owner));
            }
        }
    }

    private ArrayList<String> dogNames(Owner ownerOfDogs) {
        ArrayList<String> dogNames = new ArrayList<>();
        for (Dog dog : ownerOfDogs.getDogs()) {
            dogNames.add(dog.getName());
        }
        return dogNames;
    }

    private void increaseAge() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
        }

        else {
            String selectedDog = input.readString("Enter dog name");
            Dog dog = findDog(selectedDog);

            if (dog != null) {
                dogCollection.getDog(selectedDog).updateAge(1);
                System.out.println("The dog " + selectedDog + " is now one year older");
            }
        }
    }

    private void giveDogToOwner() {

        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
        }

        else if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
        }

        else {
            String dogName = input.readString("Enter dog name");
            Dog dogToGive = findDog(dogName);

            if (dogToGive != null) {
                if (dogToGive.getOwner() != null) {
                    System.out.println("Error: The dog " + dogToGive + " already has an owner");
                }

                else {
                    String ownerName = input.readString("Enter owner name");
                    Owner ownerToReceive = findOwner(ownerName);
                    if (ownerToReceive != null) {
                        ownerToReceive.addDog(dogToGive);
                        System.out.println("The dog " + dogToGive + " has been added to " + ownerToReceive);

                    }
                }

            }

        }
    }

    private void removeDogFromOwner() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
        }

        else if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register");
        }

        else {
            String dogName = input.readString("Enter dog name");
            Dog ownedDog = findDog(dogName);

            if (ownedDog != null) {
                if (ownedDog.getOwner() == null) {
                    System.out.println("The dog " + dogName + " has no owner");
                } else {
                    ArrayList<Dog> ownerDogsCopy = new ArrayList<>(ownedDog.getOwner().getDogs());
                    ownedDog.getOwner().removeDog(ownedDog);

                    for (Dog dog : ownerDogsCopy) {
                        System.out.println("The dog " + dog.getName() + " now has no owner");
                    }
                }
            }
        }
    }

    private Dog findDog(String dogName) {
        Dog dog = dogCollection.getDog(dogName);
        if (dog == null) {
            System.out.println("Error: The dog " + dogName + " doesn't exist");
            return null;
        }
        return dog;
    }

    private Owner findOwner(String ownerName) {
        Owner owner = ownerCollection.getOwner(ownerName);
        if (!ownerCollection.containsOwner(ownerName)) {
            System.out.println("Error: The owner " + ownerName + " doesn't exist");
            return null;
        }
        return owner;
    }

    private void printError(String command) {
        System.out.println("Error: " + command + " is not a recognised command");
        System.out.println();

    }

    private void shutDown() {
        System.out.print("Dog register shut down");
    }

    public static void main(String[] args) {
        new DogRegister().start();
    }
}
