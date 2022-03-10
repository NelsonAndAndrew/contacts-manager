
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {


    public static void main(String[] args) throws IOException {

//     CODE GOES HERE LOL
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());

        String directory = "./src";
        String filename = "contacts.txt";

        Path dataFile = Paths.get(directory, filename);

        System.out.println("dataFile = " + dataFile);

        List<String> contactsList = new ArrayList<>();
        do {
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            System.out.print("Enter an option (1, 2, 3, 4 or 5):  ");
            int userInput = scan.nextInt();


            //Conditional Start
            if (userInput == 1) {
                System.out.println(Files.readAllLines(dataFile));
            } else if (userInput == 2) {
                System.out.print("Enter a name :  ");
                String newName = scan.next();
                System.out.print("Enter phone number:  ");
                String newNumber = scan.next();
                System.out.print("Enter email address:  ");
                String newEmail = scan.next();
                List<String> newContact = Arrays.asList(newName, newNumber, newEmail);
                Files.write(dataFile, newContact, StandardOpenOption.APPEND);
//            System.out.println(Files.readAllLines(dataFile));
            } else if (userInput == 3) {
                System.out.print("Enter a name to search:  ");
                String userSearch = scan.next();
                contactsList = Files.readAllLines(dataFile);
//                System.out.println(contactsList);
                for (int i = 0; i < contactsList.size(); i++) {
                    if (contactsList.get(i).equals(userSearch)) {
                        System.out.println(contactsList.get(contactsList.indexOf(userSearch)));
                        System.out.println(contactsList.get(contactsList.indexOf(userSearch) + 1));
                        System.out.println(contactsList.get(contactsList.indexOf(userSearch) + 2));
                    }

                }
            } else if (userInput == 4) {
                System.out.print("Who you want to get rid of:  ");
                String userDelete = scan.next();
                contactsList = Files.readAllLines(dataFile);
                List<String> deleteList = new ArrayList<>();
                for (int i = 0; i < contactsList.size(); i++) {
                    if (contactsList.get(i).equals(userDelete)) {
                        i += 2;
                        continue;
                    }
                    deleteList.add(contactsList.get(i));
//                contactsList = deleteList;
                }
                Files.write(dataFile, deleteList);
//                System.out.println(deleteList);
            } else if (userInput == 5) {
                System.exit(0);
            }
        } while (true);


//        END OF MAIN
    }

//    public static Contact addContact(){
//
//    }

//END OF CLASS
}
