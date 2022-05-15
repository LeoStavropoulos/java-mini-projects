package gr.aueb.cf.projects.project6;

import java.util.Arrays;

/**
 * Δέχεται έναν πίνακα ακεραίων και βρίσκει τον υποπίνακά του με το μέγιστο άθροισμα.
 * Διατρέχει τον πίνακα μία φορά και εντοπίζει τα τοπικά μέγιστα αθροίσματα. Για κάθε
 * τοπικό μέγιστο αποθηκεύουμε τις θέσεις έναρξης και λήξης του υποπίνακα. Όταν
 * το τοπικό μέγιστο γίνει μεγαλύτερο από το ολικό, τότε εκχωρούμε στη μεταβλητή
 * globalMax την τιμή του και αποθηκεύουμε τις θέσης έναρξης και λήξης του
 * υποπίνακα στις μεταβλητές maxPositionInitial και maxPositionFinal, αντίστοιχα.
 *
 * Η πολυπλοκότητά του είναι γραμμική, αφού διατρέχουμε τον πίνακα μόνο μία φορά.
 *
 * @author L. Stavropoulos
 */
public class MaxSumContiguousSubarray {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] tmp = new int[arr.length];
        int sum = 0;

        tmp = maxContiguous(arr);

        System.out.print("Ο συνεχόμενος υποπίνακας του {");

        for (int item: arr) {
            System.out.print(item + ", ");
        }

        System.out.print("\b\b} ");

        System.out.println("με το μέγιστο άθροισμα είναι ο: ");

        System.out.println();
        System.out.print("\t\t\t\t\t\t\t {");
        for (int item: tmp) {
            System.out.print(item + ", ");
            sum += item;
        }
        System.out.print("\b\b}");

        System.out.printf(" με μέγιστο άθροισμα: %d", sum);
    }

    /**
     * Εντοπίζει και επιστρέφει το συνεχόμενο υποπίνακα με το μέγιστο άθροισμα
     *
     * @param arr       Πίνακας προς εξέταση
     * @return          Υποπίνακας με μέγιστο άθροισμα
     */
    public static int[] maxContiguous (int[] arr) {
        int localMax = arr[0];
        int globalMax = localMax;
        int maxPositionInitial = 0;
        int maxPositionFinal = 0;
        int currentPositionInitial = 0;
        int currentPositionFinal = 0;

        for (int i = 1; i < arr.length; i++) {
            localMax += arr[i];
            currentPositionFinal = i;

            if (localMax < arr[i]) {
                localMax = arr[i];
                currentPositionInitial = i;
            }
            if (localMax > globalMax) {
                globalMax = localMax;
                maxPositionInitial = currentPositionInitial;
                maxPositionFinal = currentPositionFinal;
            }

        }

        return Arrays.copyOfRange(arr, maxPositionInitial, maxPositionFinal + 1);
    }
}
