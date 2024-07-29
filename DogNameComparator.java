//Jennifer Skopac jesk3432
import java.util.Comparator;
public class DogNameComparator implements Comparator<Dog>{

    public  int compare(Dog dogOne, Dog dogTwo){
        return dogOne.getName().compareTo( dogTwo.getName());
    }
}
