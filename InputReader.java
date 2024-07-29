//Jennifer Skopac jesk3432
import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStream;
public class InputReader {
    
    private static ArrayList <InputStream> instanceOfInputStream = new ArrayList<>();
    private Scanner scanner;

    public InputReader(){
        this(System.in);
    }

    public InputReader(InputStream input){
        if(instanceOfInputStream!=null && instanceOfInputStream.contains(input)){
            throw new IllegalStateException();
        }

        else{
            this.scanner = new Scanner(input);
            instanceOfInputStream.add(input);
        }

    }

    public int readInt(String leadText){
        System.out.print(leadText + "?> ");
        int userInput = this.scanner.nextInt();
        clearBuffer();
        return userInput;

    }

    public double readDouble(String leadText){
        System.out.print(leadText + "?>");
        double userInput = this.scanner.nextDouble();
        clearBuffer();
        return userInput;
        

    }

    public String readString(String leadText){
        System.out.print(leadText  + "?>");
        return this.scanner.nextLine();


    }

    private void clearBuffer(){
        scanner.nextLine();

    }

}
