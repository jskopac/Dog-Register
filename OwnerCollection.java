//Jennifer Skopac jesk3432
import java.util.Arrays;
import java.util.ArrayList;
public class OwnerCollection {

    private Owner[] ownersArray = new Owner[0];
    private int count;


    public boolean addOwner(Owner newOwner){
        if (containsOwner(newOwner) && containsOwner(newOwner.getName())){
            return false;
        }
        
        else{
            if (count > ownersArray.length){
                count = ownersArray.length;
            }
            
            growSize();
            ownersArray[count] = newOwner;
            count++;
            return true;
        }
    }

    private void growSize(){
        ownersArray = Arrays.copyOf(ownersArray,ownersArray.length + 1);
    }


    public boolean removeOwner(Owner currentOwner) {
        if (removeOwner(currentOwner.getName())){
            return true;
        }

        return false;
    }

    
    public boolean removeOwner(String currentOwner) {
        currentOwner = currentOwner.toUpperCase();
        if (getOwner(currentOwner) != null && getOwner(currentOwner).getDogs().isEmpty()) {
            Owner[] copy = new Owner[ownersArray.length - 1];
            int copyIndex = 0;
            for (int i = 0; i < ownersArray.length; i++) {
                if (!ownersArray[i].getName().equals(currentOwner)) {
                    copy[copyIndex] = ownersArray[i];
                    copyIndex++;
                }
            }
            ownersArray = copy;
            return true;
        }
        return false;
    }



    public boolean containsOwner(Owner currentOwner){
        for (Owner owner : ownersArray){
            if (owner.getName().equals(currentOwner.getName())){
                return true;
            }
        }

        return false;

    }


    public boolean containsOwner(String currentOwner){
        currentOwner = currentOwner.toUpperCase();
        for (Owner owner : ownersArray){
            if (owner.getName().equals(currentOwner)){
                return true;
            }
        }

        return false;
    }


    public Owner getOwner(String selectedOwner){
        selectedOwner = selectedOwner.toUpperCase();
        for (Owner owner: ownersArray){
            if (owner.getName().equals(selectedOwner)){
                return owner;
            }
        }

        return null;

    }

    public ArrayList<Owner> getOwners(){
        ArrayList <Owner> copy = new ArrayList<>(Arrays.asList(ownersArray));
        copy.sort(Owner::compareTo);
        return copy;

    }

   
}
