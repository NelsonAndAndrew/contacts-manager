package contactsManagerApp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactsApp {


    public static void main(String[] args) {

//     CODE GOES HERE LOL

        String directory = "./src";
        String filename = "contacts.txt";

        Path dataFile = Paths.get(directory, filename);

        System.out.println("dataFile = " + dataFile);


//        END OF MAIN
    }

//END OF CLASS
}
