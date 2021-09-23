import java.util.Scanner;
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


}

