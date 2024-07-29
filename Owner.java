
//Jennifer Skopac jesk3432
import java.util.ArrayList;

public class Owner implements Comparable<Owner> {

    private ArrayList<Dog> dogsOwned = new ArrayList<>();
    private String name;

    public Owner(String name) {
        this.name = name.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public boolean addDog(Dog newDog) {

        if (newDog == null) {
            return false;
        }

        else if (newDog.getOwner() == this) {
            if (dogsOwned.contains(newDog)) {
                return false;
            }
            dogsOwned.add(newDog);
            return true;
        }

        else if (newDog.getOwner() == null) {
            newDog.setOwner(this);
            return true;
        }

        return false;

    }

    public boolean removeDog(Dog dogToRemove) {
        if (dogToRemove == null) {
            return false;
        }

        for (Dog dog : dogsOwned) {
            if (dog == dogToRemove) {
                dogsOwned.remove(dogToRemove);
                dogToRemove.setOwner(null);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Dog> getDogs() {
        DogNameComparator dogNameComparator = new DogNameComparator();
        ArrayList<Dog> copy = new ArrayList<>(dogsOwned);
        DogSorter.sortDogs(dogNameComparator, copy);

        return copy;
    }

    public int compareTo(Owner other) {
        return name.compareTo(other.name);
    }

    public String toString() {
        return name + dogsOwned;
    }

}
