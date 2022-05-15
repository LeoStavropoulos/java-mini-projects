package gr.aueb.cf.projects.project9;

import java.io.*;
import java.util.Scanner;

/**
 * Αποκρυπτογραφεί το μήνυμα που βρίσκεται στο αρχείο encrypted.txt,
 * με τον αντίστροφο τρόπο από τον αλγόριθμο που παρουσιάζεται στην
 * κλάση "Encryption".
 *
 * @author L. Stavropoulos
 */
public class Decryption {

    static final int KEY = 28657;

    public static void main(String[] args) {
        String message = "";

        try {
            message = decrypt();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("Το αποκρυπτογραφημένο μήνυμα είναι:");
        System.out.println(message);
    }

    /**
     * Διαβάζει ένα κρυπτογραφημένο μήνυμα από το αρχείο encrypted.txt, μέχρι
     * να βρει τον αριθμό -1. Το αποκρυπτογραφεί, χαρακτήρα - χαρακτήρα, και
     * δημιουργεί ένα string, με τη βοήθεια του String Builder, το οποίο
     * αντιστοιχεί στο αρχικό μήνυμα.
     *
     * @return
     * @throws FileNotFoundException
     */
    public static String decrypt() throws FileNotFoundException {
        Scanner in = new Scanner(new File("./src/gr/aueb/cf/projects/project9/encrypted.txt"));
        StringBuilder sb = new StringBuilder();
        int cipher = 0;
        int previousCipher = 0;

        previousCipher = in.nextInt();
        sb.append((char) previousCipher);

        while ((cipher = in.nextInt()) != -1) {
            /*
                Αν το μήνυμα είναι αρκετά μεγάλο, ώστε ο κωδικός κάποιου χαρακτήρα να περάσει το κλειδί,
                η αποκρυπτογράφηση δίνει κάποιους "λάθος" χαρακτήρες και συγκεκριμένα αποκρυπτογραφεί
                λάθος το χαρακτήρα του οποίου ο κωδικός είναι μεγαλύτερος από το κλειδί. Το πρόβλημα
                αυτό επιλύεται αν προσθέσουμε στον κωδικό αυτού του χαρακτήρα το κλειδί.
             */
            if(cipher < previousCipher) {
                sb.append((char) (((cipher + KEY - previousCipher)) % KEY));
            } else {
                sb.append((char) (((cipher - previousCipher)) % KEY));
            }
            previousCipher = cipher;
        }
        return sb.toString();
    }
}
