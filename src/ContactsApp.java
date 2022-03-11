
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
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
        System.out.println("  _____ ____  _   _ _______       _____ _______ _____ \n" +
                " / ____/ __ \\| \\ | |__   __|/\\   / ____|__   __/ ____|\n" +
                "| |   | |  | |  \\| |  | |  /  \\ | |       | | | (___  \n" +
                "| |   | |  | | . ` |  | | / /\\ \\| |       | |  \\___ \\ \n" +
                "| |___| |__| | |\\  |  | |/ ____ \\ |____   | |  ____) |\n" +
                " \\_____\\____/|_| \\_|  |_/_/    \\_\\_____|  |_| |_____/ \n" +
                "                                                      \n" +
                " __  __          _   _          _____ ______ _____  \n" +
                "|  \\/  |   /\\   | \\ | |   /\\   / ____|  ____|  __ \\ \n" +
                "| \\  / |  /  \\  |  \\| |  /  \\ | |  __| |__  | |__) |\n" +
                "| |\\/| | / /\\ \\ | . ` | / /\\ \\| | |_ |  __| |  _  / \n" +
                "| |  | |/ ____ \\| |\\  |/ ____ \\ |__| | |____| | \\ \\ \n" +
                "|_|  |_/_/    \\_\\_| \\_/_/    \\_\\_____|______|_|  \\_\\");
        System.out.println();
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
                String leftAlignFormat = "| %-20s | %-12s | %-20s | %-20s |%n";
                System.out.println();
                System.out.printf(leftAlignFormat, "NAME", "PHONE", "EMAIL", "NOTES");
                System.out.println("--------------------------------------------------------------------------------");
                for (int i = 0; i < allContacts.size(); i += 4) {
                    System.out.printf(leftAlignFormat, allContacts.get(i), allContacts.get(i + 1), allContacts.get(i + 2), allContacts.get(i + 3));
                    System.out.println();
                }
            } else if (userInput == 2) {
                List<String> allContacts = Files.readAllLines(dataFile);
                System.out.print("Enter a name :  ");
                String newName = scan.next();
                if (allContacts.contains(newName)) {
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
                        List<String> updated = new ArrayList<>();
                        for (int i = 0; i < allContacts.size(); i += 4) {
                            if (allContacts.get(i).equals(newName)) {
                                continue;
                            }
                            updated.add(allContacts.get(i));
                            updated.add(allContacts.get(i + 1));
                            updated.add(allContacts.get(i + 2));
                            updated.add(allContacts.get(i + 3));
                        }
                        updated.add(newName);
                        updated.add(newNumber);
                        updated.add(newEmail);
                        updated.add(newNote);
                        Files.write(dataFile, updated);
                        System.out.println();
                        System.out.println("Contact Added");
                        System.out.println();
                    }
                } else {
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

                }
            } else if (userInput == 3) {
                System.out.print("Enter a name to search:  ");
                String userSearch = scan.next();
                contactsList = Files.readAllLines(dataFile);
//                System.out.println(contactsList);
                String leftAlignFormat = "| %-20s | %-12s | %-20s | %-20s |%n";
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
                boolean deleted = contactsList.contains(userDelete);
                List<String> deleteList = new ArrayList<>();
                for (int i = 0; i < contactsList.size(); i++) {
                    if (contactsList.get(i).equalsIgnoreCase(userDelete)) {
                        i += 3;
                        continue;
                    }
                    deleteList.add(contactsList.get(i));
                }
                Files.write(dataFile, deleteList);
                if (deleted) {
                    System.out.println();
                    System.out.println("Contact Deleted");
                } else{
                    System.out.println();
                    System.out.println("Contact does not exist");
                }
                System.out.println();
            } else if (userInput == 5) {
                System.out.println();
                System.out.println(" _                                     _                _             _ \n" +
                        "| |                                   (_)              | |           | |\n" +
                        "| |__   __ ___   _____    __ _   _ __  _  ___ ___    __| | __ _ _   _| |\n" +
                        "| '_ \\ / _` \\ \\ / / _ \\  / _` | | '_ \\| |/ __/ _ \\  / _` |/ _` | | | | |\n" +
                        "| | | | (_| |\\ V /  __/ | (_| | | | | | | (_|  __/ | (_| | (_| | |_| |_|\n" +
                        "|_| |_|\\__,_| \\_/ \\___|  \\__,_| |_| |_|_|\\___\\___|  \\__,_|\\__,_|\\__, (_)\n" +
                        "                                                                 __/ |  \n" +
                        "                                                                |___/   \n");
                System.out.println();
                System.exit(0);
            }
        } while (true);


//        END OF MAIN
    }

//END OF CLASS
}
