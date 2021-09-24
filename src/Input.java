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

    public String getString(){
       String userInput = scan.next();
        return userInput;
    }

    public boolean yesNo(){
        System.out.println("Enter yes or y: ");
        String userInput = scan.next().toLowerCase();
        return (userInput.equals("yes") || userInput.equals("y"));
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

    public void addContact(){
        Path pathToList = Paths.get("src", "contacts.txt");
        Scanner name = new Scanner(System.in);
        Scanner number = new Scanner(System.in);

        List<String> newContact = new ArrayList<>();
        try{
            System.out.println("Enter new contact Name: ");
            String newName = name.next();
            System.out.println("Enter number for contact: EX(000)000-000 ");
            String newNumber = number.next();
            String createNum = newName + " | " + newNumber;
            newContact.add(createNum);
            Files.write(pathToList, newContact, StandardOpenOption.APPEND);
        } catch (IOException iox) {
            iox.printStackTrace();
        }


    }

    public void mainPrompt() {
        Path pathToList = Paths.get("src", "contacts.txt");
        //Input main = new Input();
        boolean user;

        do{
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
                case "2" -> addContact();
                case "3" -> System.out.println("Stay the course!");
                case "4" -> System.out.println("Perhaps the day will get better.");
                case "5" -> System.out.println("What can we do to make it better?");
                default -> System.out.println("We don't have that");
            }
            System.out.println("Continue?");
            user = yesNo();

        }while(user);


    }

    public static void main(String[] args) {

    }


}

