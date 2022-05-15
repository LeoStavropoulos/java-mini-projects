package gr.aueb.cf.projects.project7;

import java.util.Arrays;

/**
 * Δημιουργεί δύο αντίγραφα ενό πίνακα· ένα shallow κι ένα deep. Στη συνέχεια,
 * εμφανίζει τις πιθανές μεταβολές που υφίσταται ο αρχικός πίνακας αν αλλάξουμε
 * κάποιο στοιχείο των αντιγράφων, καθώς και τις πιθανές μεταβολές που υφίστανται
 * τα αντίγραφα, αν αλλάξουμε ένα στοιχείο του αρχικού πίνακα.
 *
 * @author L. Stavropoulos
 */
public class DeepShallowCopy {

    public static void main(String[] args) {
        int[][] arr = new int[10][2];
        int[][] shallow;
        int[][] deep;

        arr[0][0] = 1;
        arr[1][0] = 1;
        arr[0][1] = 1;
        arr[1][1] = 1;

        for (int i = 2; i < 10; i++) {
            arr[i][0] = arr[i - 1][0] + arr[i-2][0];
            arr[i][1] = i * arr[i - 1][1];
        }

        shallow = shallowCopy(arr);
        deep = deepCopy(arr);

        System.out.println("\t\t\t\tarr\tshallow\tdeep");
        System.out.printf("Initial Values: %d\t  %d\t%d%n", arr[6][1], shallow[6][1], deep[6][1]);

        shallow[6][1] /= 2;

        System.out.printf("shallow div 2:  %d\t  %d\t%d%n", arr[6][1], shallow[6][1], deep[6][1]);

        arr[6][1] *= 2;

        System.out.printf("arr * 2: \t\t%d\t  %d\t%d%n", arr[6][1], shallow[6][1], deep[6][1]);

        deep[6][1] /= 4;
        System.out.printf("deep div 4: \t%d\t  %d\t%d%n", arr[6][1], shallow[6][1], deep[6][1]);

    }

    /**
     * Πραγματοποιεί shallow copy του πίνακα εισόδου
     *
     * @param arr       Πίνακας προς αντιγραφή
     * @return          Αντίγραφο πίνακα εισόδου
     */
    public static int[][] shallowCopy(int[][] arr) {
        return arr;
    }

    /**
     * Πραγματοποιεί deep copy του πίνακα εισόδου
     *
     *@param arr       Πίνακας προς αντιγραφή
     *@return          Αντίγραφο πίνακα εισόδου
     */
    public static int[][] deepCopy(int[][] arr) {
        int[][] copied = new int[arr.length][arr[0].length]; // Θεωρούμε ότι όλες οι σειρές του πίνακα έχουν ίδιο μήκος,
                                                            // διαφορετικά θα έπρεπε να κάνουμε new κάθε σειρά ξεχωριστά.
        for (int i = 0; i < arr.length; i++) {
            copied[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        return copied;
    }
}
