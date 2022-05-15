package gr.aueb.cf.projects.project10;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Διαχειρίζεται το σύστημα κρατήσεων ενός θεάτρου. Δίνει στο χρήστη τη δυνατότητα κράτησης
 * θέσης ή ακύρωσης μιας υπάρχουσας κράτησης. Εμφανίζει ένα πλάνο θέσεων που περιέχει
 * τις διαθέσιμες και τις κρατημένες θέσεις.
 *
 * @author L. Stavropoulos
 */
public class BookingSystem {

    public static boolean[][] theater = new boolean[30][12];
    public static String[][] plan = new String[30][12];

    public static void main(String[] args) {
        runApp();
    }

    /**
     * Διαχειρίζεται την επικοινωνία με το χρήστη και τους ελέγχους ροής του προγράμματος
     *
     */
    public static void runApp(){
        Scanner in = new Scanner(System.in);
        char column = ' ';
        int row = -1;
        int choice = 0;



        for (int i = 0; i < 30; i++) {
            Arrays.fill(theater[i], false);
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 12; j++) {
                plan[i][j] = String.valueOf ((char) (65 + j)) + String.format("%02d", i + 1);
            }
        }

        do {
            do {
                showPlan();
                System.out.println("Δώστε τον αριθμό της επιλογής σας:");
                System.out.println("1. Κράτηση");
                System.out.println("2. Ακύρωση Κράτησης");
                System.out.println("3. Έξοδος");

                choice = in.nextInt();

                if (choice >= 1 && choice <= 3) break;

                System.out.println("Μη έγκυρη επιλογή!");

            } while (true);

            if (choice == 3) {
                System.out.println("Έξοδος από το πρόγραμμα...");
                return;
            }

            System.out.println("Δώστε το χαρακτήρα που αντιστοιχεί στη στήλη της επιλογής σας: ");

            do {
                column = in.next().charAt(0);

                if (column >= 'A' && column <= 'L') break;

                System.out.println("Μη έγκυρη τιμή! Παρακαλώ δώστε ένα χαρακτήρα από A έως L:");

            } while (true);

            System.out.println("Δώστε τον αριθμό που αντιστοιχεί στη σειρά:");

            do {
                row = in.nextInt();

                if (row >= 1 && row <= 30) break;

                System.out.println("Μη έγκυρη τιμή! Παρακαλώ δώστε έναν ακέραιο αριθμό από 1 έως 30:");

            } while (true);

            switch (choice) {
                case 1:
                    if (!isBooked(column, row)) {
                        book(column, row);
                        System.out.printf("Επιτυχής κράτηση θέσης %c%d%n", column, row);
                    } else {
                        System.out.println("Η θέση είναι ήδη κρατημένη!");
                    }
                    break;
                case 2:
                    if (isBooked(column, row)) {
                        cancel(column, row);
                        System.out.printf("Επιτυχής ακύρωση κράτησης θέσης %c%d%n", column, row);
                    } else {
                        System.out.println("Δεν υπάρχει κράτηση για τη θέση!");
                    }
                    break;
            }
            System.out.println("################################################################");
        } while (true);
    }

    /**
     * Εμφανίζει το πλάνο θέσεων του θεάτρου.
     *
     */
    public static void showPlan () {
        for (String[] row: plan) {
            System.out.print("\t\t");
            for (String column: row) {
                System.out.printf("\t%s", column);
            }
            System.out.println();
        }
    }

    /**
     * Πραγματοποιεί την κράτηση μιας θέσης.
     *
     * @param column        Χαρακτήρας που αντιστοιχεί στη στήλη της θέσης
     * @param row           Ακέραιος αριθμός που αντιστοιχεί στη σειρά της θέσης
     */
    public static void book(char column, int row) {
        int colNum = ((int) column) - 65;

        theater[row - 1][colNum] = true;
        plan[row - 1][colNum] = "XXX";
    }

    /**
     * Ακυρώνει την κράτηση μιας θέσης.
     *
     * @param column        Χαρακτήρας που αντιστοιχεί στη στήλη της θέσης
     * @param row           Ακέραιος αριθμός που αντιστοιχεί στη σειρά της θέσης
     */
    public static void cancel(char column, int row) {
        int colNum = ((int) column) - 65;

        theater[row - 1][colNum] = false;
        plan[row - 1][colNum] = String.valueOf (column) + String.valueOf(row);

    }

    /**
     * Ελέγχει αν η επιλεγμένη θέση είναι ήδη κρατημένη.
     *
     * @param column        Χαρακτήρας που αντιστοιχεί στη στήλη της θέσης
     * @param row           Ακέραιος αριθμός που αντιστοιχεί στη σειρά της θέσης
     * @return              true αν η θέση είναι ήδη κρατημένη
     */
    public static boolean isBooked(char column, int row) {
        int colNum = ((int) column) - 65;

        return theater[row - 1][colNum];
    }
}
