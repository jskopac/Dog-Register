//Jennifer Skopac jesk3432
import java.util.Comparator;
public class DogTailNameComparator implements Comparator<Dog> {

    public int compare(Dog dogOne,Dog dogTwo){


        if (dogOne.getTailLength() < dogTwo.getTailLength()){
            return -1;
        }

        else if (dogOne.getTailLength() > dogTwo.getTailLength()){
            return 1;
        }
            
        
        return dogOne.getName().compareTo(dogTwo.getName());


    }

    

}


