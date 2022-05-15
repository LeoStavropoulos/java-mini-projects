package gr.aueb.cf.projects.project9;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Κρυπτογραφεί ένα μήνυμα με βάση τον ακόλουθο αλγόριθμο:
 * ο πρώτος χαρακτήρας αντιστοιχίζεται στον ordinal number του από τον κώδικα ASCII
 * και κάθε επόμενος χαρακτήρας αντιστοιχίζεται στο υπόλοιπο της ακέραιας διαίρεσης
 * που προκύπτει από το άθροισμα του ordinal number του χαρακτήρα με τον κωδικό του
 * προηγούμενού του, διαιρούμενο με το κλειδί κρυπτογράφησης.
 * Το κρυπτογραφημένο μήνυμα αποθηκεύεται σε αρχείο encrypted.txt
 *
 * @author L. Stavropoulos
 */
public class Encryption {

    static final int KEY = 28657;   //Επιλέγεται ένας μεγάλος, κατά προτίμηση πρώτος, αριθμός, ως κλειδί κρυπτογράφησης

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        PrintStream ps = new PrintStream("./src/gr/aueb/cf/projects/project9/encrypted.txt");

        int[] messageEncrypted;
        String message;

        System.out.println("Παρακαλώ δώστε το μήνυμα που θέλετε να κρυπτογραφήσετε:");

        message = getMessage();
        messageEncrypted = encrypt(message);

        for (int item: messageEncrypted) {
            ps.print(Integer.valueOf(item) + " ");
        }
        ps.print(-1);

        ps.close();

        System.out.println("Το μήνυμα κρυπτογραφήθηκε επιτυχώς");
    }

    /**
     * Κρυπτογραφεί το μήνυμα με βάση τον αλγόριθμο που παρουσιάζεται παραπάνω.
     *
     * @param message       Το μήνυμα προς κρυπτογράφηση
     * @return              Πίνακας με τις ακέραιες τιμές που αντιστοιχούν στο κρυπτογραφημένο μήνυμα
     */
    public static int[] encrypt(String message) {
        int[] arr = new int[message.length()];

        arr[0] = message.charAt(0);

        for (int i = 1; i < arr.length; i++) {
            int currentValue = (int) message.charAt(i);
            arr[i] = (arr[i - 1] + currentValue) % KEY;
        }

        return arr;
    }

    /**
     * Διαβάζει από το πληκτρολόγιο ένα μήνυμα μέχρι τον χαρακτήρα "#" και
     * το αποθηκεύει σε μία μεταβλητή τύπου string.
     *
     * @return      String που αντιστοιχεί στο μήνυμα του χρήστη
     */
    public static String getMessage() {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String input;
        int pivot = -1;

        do {
            input = in.nextLine();
            pivot = input.indexOf("#");

            if (pivot == -1) {
                sb.append(input).append("\r\n");
            } else {
                sb.append(input, 0, pivot);
                break;
            }

        } while (true);

        return sb.toString();
    }
}
