//Jennifer Skopac jesk3432
import java.util.ArrayList;

public class DogCollection {

    private ArrayList<Dog> dogList = new ArrayList<>();

    public boolean addDog(Dog newDog){
        if (containsDog(newDog)){
            return false;
        }

        else{
            dogList.add(newDog);
            return true;
        }
    }

    public boolean removeDog(Dog currentDog){
        if (currentDog != null && currentDog.getOwner() == null){
            return dogList.remove(currentDog);
        }
        return false;
    }

    public boolean removeDog(String currentDog){
        if (getDog(currentDog) != null && getDog(currentDog).getOwner() == null){
            dogList.remove(getDog(currentDog));
            return true;
        }
        
        return false;
    }

    public boolean containsDog(Dog currentDog){
        for (Dog dog : dogList){
            if (dog.getName().equals(currentDog.getName())){
                return true;
            }
        }

        return false;
    
    }

    public boolean containsDog(String currentDog){
        currentDog = currentDog.toUpperCase();
        for (Dog dog : dogList){
            if (dog.getName().equals(currentDog)){
                return true;
            }
        }

        return false;

    }


    public Dog getDog(String selectedDog){
        selectedDog = selectedDog.toUpperCase();
        for (Dog dog : dogList){
            if (dog.getName().equals(selectedDog)){
                return dog;
            }
       }

        return null;

    }
  
    
    public ArrayList<Dog> getDogs(){
        ArrayList<Dog> dogsCopy = new ArrayList<>(dogList);
        DogNameComparator dogNameComparator = new DogNameComparator();
        DogSorter.sortDogs(dogNameComparator, dogsCopy);
        return dogsCopy;
    }

    public ArrayList<Dog> sortByTailLength(double tailLength){
        ArrayList<Dog> dogListCopy = new ArrayList<Dog>();
        for (Dog dog : dogList){
            if (dog.getTailLength() >= tailLength){
                dogListCopy.add(dog);
            }
        }

        DogTailNameComparator dogTailNameComparator = new DogTailNameComparator();
        DogSorter.sortDogs(dogTailNameComparator, dogListCopy);
        return dogListCopy;
    }

}
