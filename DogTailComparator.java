//Jennifer Skopac jesk3432
import java.util.*;
public class DogTailComparator implements Comparator<Dog> {

    public int compare (Dog dogOne, Dog dogTwo){
        if (dogOne.getTailLength() < dogTwo.getTailLength()){
            return -1;
        }
        else if (dogOne.getTailLength() == dogTwo.getTailLength()){
            return 0;
        }

        return 1;
    }
        
}

