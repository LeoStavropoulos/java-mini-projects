package gr.aueb.cf.projects.project5;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Βρίσκει την ελάχιστη και τη μέγιστη θέση εμφάνισης ενός στοιχείου σ έναν ταξινομημένο πίνακα.
 * (Αφού πρώτα ελεγχθεί ότι υπάρχει το στοιχείο στον πίνακα)
 *
 * @author L. Stavropoulos
 */
public class LowHighIndex {

    public static void main(String[] args) {
        int[] arr = {0, 1, 4, 4, 4, 6, 7, 8, 8, 8, 8, 9};
        int key = 0;
        int[] index = new int[2];

        System.out.println("Παρακαλώ δώστε την τιμή που αναζητάτε:");
        try {
            key = userChoice();
        } catch (InputMismatchException e) {
            System.out.println("Μη έγκυρη τιμή! Το κλειδί αναζήτησης πρέπει να είναι ακέραιος αριθμός.");
            e.printStackTrace();
            return;
        }

        index = getLowAndHighIndexOf(arr, key);

        if (index[0] == -1) {
            System.out.printf("Το στοιχείο %d δεν υπάρχει στον πίνακα.", key);
        } else {
            System.out.printf("Το στοιχείο %d εμφανίζεται από τη θέση %d έως και τη θέση %d του πίνακα.", key, index[0], index[1]);
        }
    }


    /**
     * Βρίσκει την ελάχιστη και τη μέγιστη θέση ενός στοιχείου στον πίνακα εισόδου. Αν δεν υπάρχει επιστρέφει
     * την τιμή -1 και για την ελάχιστη και για τη μεγίστη θέση.
     *
     * @param arr       Πίνακας προς έλεγχο
     * @param key       Στοιχείο προς έλεγχο
     * @return          Πίνακας δύο θέσεων που αποτελείται από τις τιμές ελάχιστης και μέγιστης θέσης του στοιχείου
     */
    public static int[] getLowAndHighIndexOf (int[] arr, int key) {
        int[] LowHigh = {-1, -1};
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                LowHigh[0] = i;
                found = true;
                break;
            }
        }

        if (!found) return LowHigh;

        for (int i = LowHigh[0] + 1; i < arr.length; i++) {
            if (arr[i] != key) {
                LowHigh[1] = i - 1;
                found = false;
                break;
            }
        }

        if (found) LowHigh[1] = arr.length - 1;

        return LowHigh;
    }

    /**
     * Διαβάζει την επιλογή του χρήστη από το πληκτρολόγιο.
     *
     * @return      Τον ακέραιο επιλογής του χρήστη.
     * @throws InputMismatchException
     */
    public static int userChoice () throws InputMismatchException {
        int choice = 0;
        Scanner in = new Scanner(System.in);

        choice = in.nextInt();
        in.close();

        return choice;
    }
}


