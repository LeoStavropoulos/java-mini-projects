package gr.aueb.cf.projects.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Διαβάζει από 6 έως 49 ακέραιους αριθμούς από ένα αρχείο ("input.txt"),
 * που έχουν τιμές από 1 έως και 49. Τους εισάγει σε πίνακα,
 * τον ταξινομεί και παράγει όλες τις δυνατές εξάδες.
 * Φιλτράρει τις εξάδες, με βάση τα κριτήρια που περιγράφονται
 * στις ακόλουθες μεθόδους και αποθηκεύει τα αποτελέσματα
 * σε αρχείο "output.txt".
 *
 * @author L. Stavropoulos
 */
public class CombinationSixFiltered {

    public static int[] numbers = new int[49];
    public static PrintStream ps;

    static {
        try {
            ps = new PrintStream(new File("./src/gr/aueb/cf/projects/project1/output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        int count = 0;


        count = getNumbersAndCount();

        if (count == -1) return; // Τερματίζεται το πρόγραμμα.

        if (count < 6) {
            System.out.println("Μη επαρκές πλήθος αριθμών στο αρχείο! Το πρόγραμμα θα τερματιστεί.");
            return; // Αν στο αρχείο δεν υπάρχουν τουλάχιστον 6 αριθμοί το πρόγραμμα τερματίζεται.
        }

        Arrays.sort(numbers, 0, count); // Ο πίνακας numbers έχει 49 θέσεις, επομένως μπορεί κάποιες
                                                 // να είναι "κενές". Ταξινομούνται οι θέσεις από την αρχή μέχρι
                                                 // τη θέση που αντιστοιχεί στο πλήθος των αριθμών που διαβάστηκαν.

        combinations(0, 1, 2, 3, 4, 5, count);

    }

    /**
     * Διαβάζει ακέραιους αριθμούς από ένα αρχείο, τους αποθηκεύει
     * σε στατικό πίνακα και επιστρέφει το πλήθος τους. Αν το αρχείο
     * περιέχει περισσότερους από 49 αριθμούς, υπολογίζει μόνο τους
     * 49 πρώτους. Αν βρεθεί μη έγκυρος αριθμός στο αρχείο,
     * το πρόγραμμα τερματίζεται.
     *
     * @return πλήθος αριθμών που διαβάστηκαν
     * @throws FileNotFoundException
     */
    public static int getNumbersAndCount () throws FileNotFoundException {
        Scanner in = new Scanner(new File("./src/gr/aueb/cf/projects/project1/input.txt"));
        int count = 0;

        for (int i = 0; in.hasNextInt(); i++) {
            if (i == 49) {
                System.out.println("Το αρχείο περιέχει περισσότερους από 49 αριθμούς. " +
                        "Μόνο οι πρώτοι 49 θα υπολογιστούν.");
                break;
            }

            numbers[i] = in.nextInt();

            if (numbers[i] == -1) break;

            if (numbers[i] < 0 || numbers[i] > 49) {
                System.out.printf("Ο αριθμός στη θέση %d είναι μη έγκυρος. " +
                        "Παρακαλώ αντικαταστήστε τον με ένα αριθμό από 0 έως 49. " +
                        "Το πρόγραμμα θα τερματιστεί.", count);
                return -1;
            }

            count++;
        }

        in.close();
        return count;
    }


    /**
     * Δημιουργεί εξάδες αριθμών, από τα στοιχεία του πίνακα
     * numbers, αναδρομικό τρόπο. Εφαρμόζει τα φίλτρα και
     * αποθηκεύει τα αποτελέσματα σε αρχείο "output.txt"
     *
     *
     * @param i     1ος αριθμός συνδυασμού
     * @param j     2ος αριθμός συνδυασμού
     * @param k     3ος αριθμός συνδυασμού
     * @param l     4ος αριθμός συνδυασμού
     * @param m     5ος αριθμός συνδυασμού
     * @param n     6ος αριθμός συνδυασμού
     * @param count     πλήθος στοιχείων του numbers
     */
    public static void combinations (int i, int j, int k, int l, int m, int n, int count) {         //  Μπορεί να γίνει και με static int count

        if (n < count) {
            int[] arr = {numbers[i], numbers[j], numbers[k], numbers[l], numbers[m], numbers[n]};
            if (hasMaxFourEven(arr) && hasMaxFourOdd(arr) && hasMaxTwoInARow(arr) && hasMaxThreeSameEnding(arr) && hasMaxThreeSameTen(arr)) ps.printf("%d\t%d\t%d\t%d\t%d\t%d%n", numbers[i], numbers[j], numbers[k], numbers[l], numbers[m], numbers[n]);
            combinations(i, j, k, l , m,n + 1, count);
        } else {
            n = m + 1;
            if (m < count - 1) {
                combinations(i, j, k, l , m + 1,n + 1, count);
            } else {
                m = l + 1;
                n = m + 1;
                if (l < count - 2) {
                    combinations(i, j, k, l + 1 , m + 1,n + 1, count);
                } else {
                    l = k + 1;
                    m = l + 1;
                    n = m + 1;
                    if (k < count - 3) {
                        combinations(i, j, k + 1, l + 1, m + 1,n + 1, count);
                    }
                    else {
                        k = j + 1;
                        l = k + 1;
                        m = l + 1;
                        n = m + 1;
                        if (j < count - 4) {
                            combinations(i, j + 1, k + 1, l + 1, m + 1,n + 1, count);
                        } else {
                            j = i + 1;
                            k = l + 1;
                            l = k + 1;
                            m = l + 1;
                            n = m + 1;
                            if (i < count - 5) {
                                combinations(i + 1, j, k + 1, l + 1, m + 1,n + 1, count);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Φιλτράρει τους συνδυασμούς ώστε να έχουν το πολύ 4 άρτιους
     *
     * @param arr       πίνακας συνδυασμού
     * @return      true αν ο συνδυασμός ανταποκρίνεται στο φίλτρο
     */
    public static boolean hasMaxFourEven (int[] arr) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (arr[i] % 2 == 0) count++;
        }
        return (count <= 4);
    }

    /**
     * Φιλτράρει τους συνδυασμούς ώστε να έχουν το πολύ 4 περιττούς
     *
     * @param arr       πίνακας συνδυασμού
     * @return      true αν ο συνδυασμός ανταποκρίνεται στο φίλτρο
     */
    public static boolean hasMaxFourOdd(int[] arr) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (arr[i] % 2 == 1) count++;
        }
        return (count <= 4);
    }

    /**
     * Φιλτράρει τους συνδυασμούς ώστε να έχουν το πολύ δύο συνεχόμενους αριθμούς
     *
     * @param arr       πίνακας συνδυασμού
     * @return      true αν ο συνδυασμός ανταποκρίνεται στο φίλτρο
     */
    public static boolean hasMaxTwoInARow(int[] arr) {
        for (int i = 0; i < 3; i++) {
            if (arr[i] == (arr[i + 2] - 2)) return false;
        }
        return true;
    }

    /**
     * Φιλτράρει τους συνδυασμούς ώστε να έχουν το πολύ τρεις ίδιους λήγοντες
     *
     * @param arr       πίνακας συνδυασμού
     * @return      true αν ο συνδυασμός ανταποκρίνεται στο φίλτρο
     */
    public static boolean hasMaxThreeSameEnding(int[] arr) {
        for (int i = 0; i < 3; i++) {
            int count = 0;

            for (int j = i + 1; j < 6; j++) {
                if ((arr[i] % 10) == (arr[j] % 10)) count++;
            }

            if (count == 4) return false;
        }
        return true;
    }

    /**
     * Φιλτράρει τους συνδυασμούς ώστε να έχουν το πολύ τρεις αριθμούς στην ίδια δεκάδα
     *
     * @param arr       πίνακας συνδυασμού
     * @return      true αν ο συνδυασμός ανταποκρίνεται στο φίλτρο
     */
    public static boolean hasMaxThreeSameTen(int[] arr) {

        for (int i = 0; i < 3; i++) {
            if ((arr[i] / 10) == (arr[i + 3] / 10)) return false;
        }
        return true;
    }
}
