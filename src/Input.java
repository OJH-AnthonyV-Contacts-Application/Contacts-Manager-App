import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException; //catch exceptions
import java.nio.file.*; // import Paths, Path
import java.util.List;
import java.nio.file.StandardOpenOption; //append to predetermined text file

public class Input {
    private Scanner scan;

    public Input() {
        this.scan = new Scanner(System.in);

    }

    public String getString(String userInput){
        System.out.println(userInput);
        return this.scan.nextLine();
    }

    public int getNumber(String userInput, int min, int max) throws NumberFormatException {
        System.out.println(userInput);
        int num;
        String select = this.scan.nextLine();
        try {
            num = Integer.parseInt(getString(userInput));
            if(num > max || num < min) {
                return getNumber("Input invalid, please try again", min, max);
            }
            return num;
        } catch (NumberFormatException nox) {
            nox.printStackTrace();
        }
        return getNumber("Uh Oh! Please try again", min , max);

    }

    public void displayContacts() {
        Path pathToList = Paths.get("src", "contacts.txt");
        List<String> list = new ArrayList<>();
        try{
            list = Files.readAllLines(pathToList);
        } catch(IOException iox) {
            iox.printStackTrace();
        }

        System.out.println("Name | Phone Number");
        System.out.println("-------------------");
        for(String contact : list) {
            System.out.println(contact);
        }
    }

    public void mainPrompt() {
        Path pathToList = Paths.get("src", "contacts.txt");
        Input main = new Input();

        System.out.println("1 - View Contacts.");
        System.out.println("2 - Add a new Contact.");
        System.out.println("3 - Search by contact name.");
        System.out.println("4 - Delete an existing contact.");
        System.out.println("5 - Exit.");
        System.out.println("Enter an option (1, 2, 3, 4 or 5)");
        String userInput = scan.next();
        //System.out.println(main.getString(userInput));


        switch (userInput) {
            case "1" -> displayContacts();
            case "2" -> System.out.println("The day will only get better, stay positive!");
            case "3" -> System.out.println("Stay the course!");
            case "4" -> System.out.println("Perhaps the day will get better.");
            case "5" -> System.out.println("What can we do to make it better?");
            default -> System.out.println("We don't have that");
        }

    }

    public static void main(String[] args) {

    }


}

