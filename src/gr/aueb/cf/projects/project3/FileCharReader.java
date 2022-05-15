package gr.aueb.cf.projects.project3;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Διαβάζει τους χαρακτήρες ενός αρχείου και τους εισάγει σ έναν πίνακα 256Χ2, όπου στην πρώτη θέση
 * κάθε γραμμής του αντιστοιχίζεται ο κάθε (ξεχωριστός) χαρακτήρας και στη δεύτερη θέση ο αριθμός
 * των φορών που εμφανίζεται. Αν το κείμενο περιέχει περισσότερους από 256 διαφορετικούς χαρακτήρες,
 * η ανάγνωση τερματίζεται και εμφανίζεται το πλήθος των χαρακτήρων (όχι μοναδικών) που αναγνώστηκαν.
 * Στη συνέχεια, εμφανίζονται στατιστικά στοιχεία για τον κάθε χαρακτήρα (πλήθος φορών εμφάνισης και
 * ποσοστό επί του συνόλου των χαρακτήρων του κειμένου), τα οποία ταξινομούνται σε αύξουσα ή φθίνουσα
 * σειρά, με βάση το χαρακτήρα ή το πλήθος εμφανίσεων, σύμφωνα με την επιλογή του χρήστη.
 * Τέλος, τα αποτελέσματα παρουσιάζονται σε ένα γράφημα.
 *
 * @author L. Stavropoulos
 */
public class FileCharReader {

    public static int[][] arr = new int[256][2];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        int n[] = new int[2];

        try {
            n = readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (n[0] != n[1]) System.out.printf("Το αρχείο υπερβαίνει το μέγιστο αριθμό 256 διαφορετικών χαρακτήρων. " +
                "Διαβάστηκαν οι %d πρώτοι χαρακτήρες.%n", n[1]);

        System.out.println(n[1]);
        int count = 0;
        for (int i = 0; i < 256; i++) {
            count += arr[i][1];
        }
        System.out.println(count);

        System.out.println("Η ανάγνωση του αρχείου ολοκληρώθηκε επιτυχώς.");
        System.out.println();
        while (true){
            System.out.println("Δώστε τον αριθμό μίας από τις παρακάτω επιλογές:");
            System.out.println("1. Εμφάνιση κατά αύξουσα σειρά χαρακτήρα");
            System.out.println("2. Εμφάνιση κατά φθίνουσα σειρά χαρακτήρα");
            System.out.println("3. Εμφάνιση κατά αύξουσα σειρά συχνότητας εμφάνισης");
            System.out.println("4. Εμφάνιση κατά φθίνουσα σειρά συχνότητας εμφάνισης");
            System.out.println("5. Έξοδος");

            switch (choice = in.nextInt()) {
                case 1:
                case 2:
                case 3:
                case 4:
                    System.out.println("\t\tΧαρακτήρας\tΑριθμός Εμφανίσεων\tΣυχνότητα εμφάνισης");
                    showStatistics(choice, n[1]);
                    break;
                case 5:
                    System.out.println("Έξοδος από το πρόγραμμα...");
                    return;
                default:
                    System.out.println("Μη έγκυρη επιλογή!");
                    break;
            }
        }
    }

    /**
     * Διαβάζει το αρχείο με χρήση ενός Buffered Reader. Αν το αρχείο περιέχει περισσότερους από 256 διαφορετικούς
     * χαρακτήρες, επιστρέφει τον αριθμό των συνολικών χαρακτήρων που διαβάστηκαν μέχρι να βρεθεί ο 257ος διαφορετικός.
     * Αν οι αριθμοί των δύο θέσεων του πίνακα επιστροφής είναι διαφορετικοί, σημαίνει ότι βρέθηκαν πάνω από
     * 256 διαφορετικοί χαρακτήρες.
     *
     * @return  πίνακας δύο θέσεων. Στην πρώτη υπάρχει το πλήθος τον χαρακτήρων που διαβάστηκαν και στη
     *          δεύτερη το πλήθος εκείνων που τελικά εκχωρήθηκαν στον πίνακα.
     * @throws IOException
     */
    public static int[] readFile() throws IOException {
        int n = 0;
        int bufSize = 8192;
        int lastPosition = 0;
        int[] inArrCount = new int[2];
        char[] buf = new char[bufSize];
        BufferedReader bf = new BufferedReader(new FileReader("./src/gr/aueb/cf/projects/project3/input.txt"));
        //Υπάρχει και το αρχείο ascii.txt που περιέχει περισσότερους από 256 διαφορετικούς χαρακτήρες, για να δοκιμαστεί η συμπεριφορά του προγράμματος σε αυτό το σενάριο.

        while ((n = bf.read(buf, 0, bufSize)) != -1){
            int[] tmp = {0, 0};

            inArrCount[0] += n;
            tmp = createArray(buf, n ,lastPosition);
            inArrCount[1] += tmp[0] + 1;
            lastPosition += tmp[1];
        }

        return inArrCount;
    }

    /**
     * Εκχωρεί του χαρακτήρες του πίνακα buf και το πλήθος εμφάνισης τους στις αντίστοιχες θέσεις του πίνακα arr.
     * Αν το στοιχείο υπάρχει ήδη, αυξάνει το πλήθος εμφάνισής του κατά ένα, αλλιώς δημιουργεί νέα καταχώριση.
     *
     * @param buf               Πίνακας χαρακτήρων προς εκχώρηση στον arr
     * @param n                 Πλήθος χαρακτήρων στον buf (Μπορεί να είναι μικρότερο από το μήκος του)
     * @param lastPosition      Θέση τελευταίου στοιχείου στον πίνακα arr
     * @return                  Πίνακας δύο θέσεων. Πρώτη θέση: πλήθος αποθηκευμένων χαρακτήρων (όχι διαφορετικών)
     *                          Δεύτερη θέση: θέση τελευταίου στοιχείου του πίνακα arr
     */
    public static int[] createArray (char[] buf, int n, int lastPosition) {
        int position = -1;
        int[] tmp = new int[2];
        tmp[0] = n - 1;
        tmp[1] = lastPosition;

        if ((arr[0][1] == 0)){
            arr[0][0] = buf[0];
        }

        for (int i = 0; i < n; i++) {

            if (lastPosition == 255) {
                tmp[0] = i - 1;
                return tmp; // Αν περάσει τους 256 διαφορετικούς χαρακτήρες
                             // επιστρέφει τη θέση του τελευταίου αποθηκευμένου χαρακτήρα.
            }

            position = existsInArray(lastPosition, buf[i]);
            if (position != -1) {
                arr[position][1] ++;
            } else {
                arr[++lastPosition][0] = buf[i];
                arr[lastPosition][1] = 1;
                tmp[1] = lastPosition;
            }
        }

        return tmp;
    }

    /**
     * Ελέγχει αν ο χαρακτήρας εισόδου υπάρχει ήδη στο πίνακα.
     *
     * @param lastPosition      Θέση τελευταίου στοιχείου του πίνακα
     * @param letter            Χαρακτήρας προς έλεγχο
     * @return                  Θέση χαρακτήρα στον πίνακα ή -1 αν δεν υπάρχει.
     */
    public static int existsInArray(int lastPosition, char letter) {

        for (int i = 0; i <= lastPosition; i++) {
            if (arr[i][0] == letter) return i;
        }

        return -1;
    }

    /**
     * Εμφανίζει στατιστικά στοιχεία για κάθε χαρακτήρα του πίνακα καθώς και το σχετικό διάγραμμα
     * συχνοτήτων εμφάνισης.
     *
     * @param choice        Επιλογή σειράς παρουσίασης (αύξουσα κατά χαρακτήρα, φθίνουσα κατά χαρακτήρα,
     *                      αύξουσα κατά συχνότητα εμφάνισης, φθίνουσα κατά συχνότητα εμφάνισης)
     * @param n             Πλήθος συνολικών αναγνωσμένων χαρακτήρων
     */
    public static void showStatistics(int choice, int n) {
        int[][] copied = new int[256][2];
        int[] plot = new int[256];
        int pivot = 256; // Υποθέτουμε ότι ο πίνακας είναι γεμάτος.
        int maxPlot = 0;

        for (int i = 0; i < 256; i++) {
            if (arr[i][1] == 0) { // Αν ο πίνακας δεν είναι γεμάτος βρίσκουμε τη θέση τελευταίου στοιχείου.
                pivot = i; //Δείχνει την επόμενη θέση από το τελευταίο στοιχείο!
                break;
            }
            copied[i] = Arrays.copyOf(arr[i], 2);
        }

        switch (choice){
            case 1:
                Arrays.sort(copied, 0, pivot, (a1, a2)-> a1[0] - a2[0]);
                break;
            case 2:
                Arrays.sort(copied, 0, pivot, (a1, a2)-> a2[0] - a1[0]);
                break;
            case 3:
                Arrays.sort(copied, 0, pivot, (a1, a2)-> a1[1] - a2[1]);
                break;
            case 4:
                Arrays.sort(copied, 0, pivot, (a1, a2)-> a2[1] - a1[1]);
                break;
        }

        for (int i = 0; i < pivot; i++) {
            double percentage = (((double) copied[i][1]) / n) * 100;

            if ((copied[i][0] != 10) && (copied[i][0] != 32)) {
                System.out.printf("%d\t\t%-10c\t\t%-5d\t\t\t\t%02.2f%% %n", i + 1, (char) copied[i][0], copied[i][1], percentage);
            } else if(copied[i][0] == 10) {
                System.out.printf("%d\t\t(new line)\t\t%-5d\t\t\t\t%02.2f%% %n", i + 1, copied[i][1], percentage);
            } else
                System.out.printf("%d\t\t(space)   \t\t%-5d\t\t\t\t%02.2f%% %n", i + 1, copied[i][1], percentage);
            plot[i] = (int) Math.round (percentage);
        }

        System.out.println();
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();

        maxPlot = maxElement(plot); //Βρίσκουμε τη μεγαλύτερη τιμή που παίρνουν τα στοιχεία του plot, για να
                                    // κανονικοποιήσουμε το γράφημα.

        for (int i = maxPlot; i > 0; i--) {
            for (int j = 0; j < pivot; j++) {
                if (plot[j] >= i) {
                    System.out.print("||\t");
                } else {
                    System.out.print(" \t");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < pivot; i++) {
            if ((copied[i][0] != 10) && (copied[i][0] != 32)) {
                System.out.print((char) copied[i][0] + "\t");
            } else if (copied[i][0] == 10){
                System.out.print("\\n\t");
            } else {
                System.out.print("\\s\t");
            }
        }

        System.out.println();
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Βρίσκει το μέγιστο στοιχείο του πίνακα εισόδου. (Όχι τη θέση του!)
     *
     * @param arr       Πίνακας ακεραίων προς έλεγχο
     * @return          Μέγιστο στοιχείο του arr
     */
    public static int maxElement(int[] arr) {
        int max = arr[0];

        for (int item: arr) {
            if (item > max) max = item;
        }

        return max;
    }
}
