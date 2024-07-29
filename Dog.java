//Jennifer Skopac jesk3432
public class Dog {
    
    private static final int MAX_AGE= Integer.MAX_VALUE;
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private static final String[] DACHSHUND_TRANSLATIONS = {"tax", "dachshund","mäyräkoira", "teckel"};

    private final String name;
    private final String breed;
    private int age;
    private final int weight;
    private Owner currentOwner;

    public Dog(String name, String breed, int age, int weight){
        this.name = name.toUpperCase();
        this.breed = breed.toUpperCase();
        this.age = age;
        this.weight = weight;
    }

    public String getName(){
        return name;
    }
    
    public String getBreed(){
        return breed;
    }

    public int getWeight(){
        return weight;
    }

    public int getAge(){
        return age;
    }

    public void updateAge(int newAge){
        if (newAge > 0 && age != MAX_AGE) {
            age += newAge;
        }
    }


    public double getTailLength(){
        for (String element: DACHSHUND_TRANSLATIONS){
            if (element.equalsIgnoreCase(breed))
            return DACHSHUND_TAIL_LENGTH;
        }
            double defaultTailLength = age * weight / (double)10;
            return defaultTailLength;

    }

    public boolean setOwner(Owner newOwner){
        if (currentOwner == null && newOwner != null){
            currentOwner = newOwner;
            newOwner.addDog(this);
            return true;
        }



        else if (currentOwner != null && newOwner == null){
            currentOwner.removeDog(this);
            currentOwner = null;
            return true;

        }
            
        return false;

    }

    public Owner getOwner(){
        return currentOwner;

    }

    public String toString(){
        if (this.currentOwner == null){
            return name + " " + breed + " " + age + " " + weight + " " + "%.1f".formatted(getTailLength());
        }
        else{
            return name + " " + breed + " " + age + " " + weight + " " + "%.1f".formatted(getTailLength()) + " " +  currentOwner.getName();
        }
    }

}
