
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

//        System.out.println("dataFile = " + dataFile);

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
                List<String> allContacts = Files.readAllLines(dataFile);
                String leftAlignFormat = "| %-15s | %-12s | %-20s | %-20s |%n";
                System.out.println();
                System.out.printf(leftAlignFormat, "NAME", "PHONE", "EMAIL", "NOTES");
                System.out.println("--------------------------------------------------------------------------------");
                for (int i = 0; i < allContacts.size(); i += 5) {
                    System.out.printf(leftAlignFormat, allContacts.get(i), allContacts.get(i + 1), allContacts.get(i + 2), allContacts.get(i + 3));
                    System.out.println();
                }
            } else if (userInput == 2) {
                List<String> allContacts = Files.readAllLines(dataFile);
                System.out.print("Enter a name :  ");
                String newName = scan.next();
                for (int i = 0; i < allContacts.size(); i++) {
                    if (allContacts.get(i).equalsIgnoreCase(newName)) {
                        System.out.println("There's already a contact named " + newName + ".");
                        System.out.print("Do you want to continue / overwrite? [Y/N]:  ");
                        String yesNo = scan.next();
                        if (yesNo.equalsIgnoreCase("y")) {
                            System.out.print("Enter phone number:  ");
                            String newNumber = scan.next();
                            newNumber = newNumber.replaceAll("[^\\d]", "");
                            if (newNumber.length() == 7) {
                                newNumber = newNumber.substring(0, 3) + "-" + newNumber.substring(3);
                            } else if (newNumber.length() == 10) {
                                newNumber = newNumber.substring(0, 3) + "-" + newNumber.substring(3, 6) + "-" + newNumber.substring(6);
                            }
                            System.out.print("Enter email address:  ");
                            String newEmail = scan.next();
                            System.out.print("Enter a note (Max 20 Characters):  ");
                            String newNote = scan.next();
                            if (newNote.length() > 20) {
                                newNote = newNote.substring(0, 20);
                            }
                            List<String> newContact = Arrays.asList(newName, newNumber, newEmail, newNote);
                            Files.write(dataFile, newContact, StandardOpenOption.APPEND);
                            System.out.println("Contact Added");
                            System.out.println();
                        } else {
                            break;
                        }
                    }
                }
                System.out.print("Enter phone number:  ");
                String newNumber = scan.next();

                newNumber = newNumber.replaceAll("[^\\d]", "");
                if (newNumber.length() == 7) {
                    newNumber = newNumber.substring(0, 3) + "-" + newNumber.substring(3);
                } else if (newNumber.length() == 10) {
                    newNumber = newNumber.substring(0, 3) + "-" + newNumber.substring(3, 6) + "-" + newNumber.substring(6);
                }

                System.out.print("Enter email address:  ");
                String newEmail = scan.next();
                System.out.print("Enter a note (Max 20 Characters):  ");
                String newNote = scan.next();
                if (newNote.length() > 20) {
                    newNote = newNote.substring(0, 20);
                }
                List<String> newContact = Arrays.asList(newName, newNumber, newEmail, newNote);
                Files.write(dataFile, newContact, StandardOpenOption.APPEND);
                System.out.println("Contact Added");
                System.out.println();


            } else if (userInput == 3) {
                System.out.print("Enter a name to search:  ");
                String userSearch = scan.next();
                contactsList = Files.readAllLines(dataFile);
//                System.out.println(contactsList);
                String leftAlignFormat = "| %-15s | %-12s | %-20s | %-20s |%n";
                System.out.println();
                System.out.printf(leftAlignFormat, "NAME", "PHONE", "EMAIL", "NOTES");
                System.out.println("--------------------------------------------------------------------------------");
                for (int i = 0; i < contactsList.size(); i++) {
                    if (contactsList.get(i).equalsIgnoreCase(userSearch)) {
                        System.out.printf(leftAlignFormat, contactsList.get(contactsList.indexOf(userSearch)), contactsList.get(contactsList.indexOf(userSearch) + 1), contactsList.get(contactsList.indexOf(userSearch) + 2), contactsList.get(contactsList.indexOf(userSearch) + 3));
                        System.out.println();
                    }
                }
            } else if (userInput == 4) {
                System.out.print("Who you want to get rid of:  ");
                String userDelete = scan.next();
                contactsList = Files.readAllLines(dataFile);
                List<String> deleteList = new ArrayList<>();
                for (int i = 0; i < contactsList.size(); i++) {
                    if (contactsList.get(i).equalsIgnoreCase(userDelete)) {
                        i += 2;
                        continue;
                    }
                    deleteList.add(contactsList.get(i));
//                contactsList = deleteList;
                }
                Files.write(dataFile, deleteList);
                System.out.println("Contact Deleted");
                System.out.println();
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
