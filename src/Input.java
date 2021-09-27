
import java.util.ArrayList;
import java.util.Scanner;   // import scanner
import java.io.IOException; //catch exceptions
import java.nio.file.*; // import Paths, Path
import java.util.List;
import java.nio.file.StandardOpenOption; //append to predetermined text file

public class Input {
    private final Scanner scan;


    public Input() {
        this.scan = new Scanner(System.in);

    }

    public String getString(){
        return scan.next();
    }

    public boolean yesNo(){
        System.out.println("Enter yes or y: ");
        String userInput = scan.next().toLowerCase();
        return (userInput.equals("yes") || userInput.equals("y"));
    }

    public int getNumber( int min, int max) throws IllegalArgumentException {
        System.out.println("Enter an option (1, 2, 3, 4 or 5)");
        int num = 0;
        try {
            num = Integer.parseInt(getString());
        } catch (IllegalArgumentException nox) {
            if(num > max || num < min) {
                nox.printStackTrace();
                return getNumber(min, max);
            }
        }
        return num;
    }

    public int getNumber() {
        return getNumber(1, 5);
    }

    public void searchContact(){
        Path pathToList = Paths.get("src", "contacts.txt");
        Input search = new Input();
        System.out.println("Please enter the contact name: ");

        String newSearch = search.getString();
        List<String> list = new ArrayList<>();


        try {
            list = Files.readAllLines(pathToList);
        } catch(IOException iox) {
            iox.printStackTrace();
        }

        for (String i : list) {
                if (i.contains(newSearch)) {
                    System.out.println(i);
                }

        }



    }



    public void displayContacts() {
        Path pathToList = Paths.get("src", "contacts.txt");
        List<String> list = new ArrayList<>();
        try{
            list = Files.readAllLines(pathToList);

        } catch(IOException iox) {
            iox.printStackTrace();
        }
        System.out.println(" |  Name | Phone Number  |");
        System.out.println("--------------------------");
        for(String contact : list) {
            System.out.println(contact);
        }


    }

    public void addContact(){
        Path pathToList = Paths.get("src", "contacts.txt");
        Input name = new Input();
        Input number = new Input();

        List<String> newContact = new ArrayList<>();
        try{
            System.out.println("Enter new contact Name: ");
            String newName = name.getString();

            System.out.println("Enter number for contact: EX(000)000-000 ");
            String newNumber = number.getString();
            String createNum = " | " + newName + " | " + newNumber + " | ";
            newContact.add(createNum);
            Files.write(pathToList, newContact, StandardOpenOption.APPEND);
        } catch (IOException iox) {
            iox.printStackTrace();

        }


    }

    public void deleteContact() {
        Path pathToList = Paths.get("src", "contacts.txt");
        Input delete = new Input();
        System.out.println("Please enter contact you wish to remove: ");
        String input = delete.getString();

        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(pathToList);
        } catch (IOException iox){
            iox.printStackTrace();
        }

        try {
            list.removeIf(contact -> contact.contains(input));
            Files.write(pathToList, list);
        } catch (IOException iox){
            iox.printStackTrace();
        }



    }


    public void mainPrompt() {
         //displayContacts();
//        Path pathToFile = Paths.get("src", "contacts.txt");
//        pathToFile.readFileAndOutput(pathToFile);


        boolean user;
        String userInput;

        do{
            System.out.println("1 - View Contacts.");
            System.out.println("2 - Add a new Contact.");
            System.out.println("3 - Search by contact name.");
            System.out.println("4 - Delete an existing contact.");
            System.out.println("5 - Exit.");

            userInput = String.valueOf(getNumber());


            switch (userInput) {
                case "1" -> displayContacts();
                case "2" -> addContact();
                case "3" -> searchContact();
                case "4" -> deleteContact();
                case "5" -> System.exit(5);
                default -> System.out.println("Input invalid, please try again: ");
            }
            user = yesNo();

        }while(user);


    }


}

