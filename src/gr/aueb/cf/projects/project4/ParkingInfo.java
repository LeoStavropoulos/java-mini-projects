package gr.aueb.cf.projects.project4;

import java.util.Arrays;

/**
 * Δέχεται έναν πίνακα με τις ώρες άφιξης κι αναχώρησης κάθε αυτοκινήτου ενός πάρκινγκ
 * και εμφανίζει το μέγιστο πλήθος αυτοκινήτων που βρέθηκαν σε αυτό ταυτόχρονα.
 *
 * @author L. Stavropoulos
 */
public class ParkingInfo {

    public static void main(String[] args) {
        int[][] arr = {{1012, 1136}, {1317, 1417}, {1015, 1020}};


        System.out.printf("Ο μέγιστος αριθμός αυτοκινήτων που βρέθηκαν ταυτόχρονα στο πάρκινγκ είναι: %d", maxCars(arr));


    }

    /**
     * Δημιουργεί ενά νέο πίνακα στον οποίο εκχωρεί την ώρα άφιξης ή αναχώρησης κάθε αυτοκινήτου και τον
     * αριθμό 1 για άφιξη ή -1 για αναχώρηση. Αθροίζοντας τους τελευταίους, αφού πρώτα ταξινομηθεί ο πίνακας
     * σε αύξουσα σειρά, βρίσκει το μέγιστο αριθμό αυτοκινήτων που ήταν ταυτόχρονα σταθμευμένα.
     *
     * @param arr       Πίνακας ωρών άφιξης κι αναχώρησης κάθε αυτοκινήτου.
     * @return          Μέγιστος αριθμών αυτοκινήτων που βρέθηκαν ταυτόχρονα στο πάρκινγκ.
     */
    public static int maxCars(int[][] arr) {
        int[][] intervals = new int[arr.length * 2][2];
        int count = 0;
        int maxCount = 0;

        for (int i = 0, j = 0; i < arr.length; i++) {
            intervals[j][0] = arr[i][0];
            intervals[j][1] = 1;
            intervals[++j][0] = arr[i][1];
            intervals[j++][1] = -1;
        }

        Arrays.sort(intervals, (a1, a2) -> a1[0] - a2[0]);

        for (int i = 0; i < intervals.length; i++) {
            count += intervals[i][1];
            if (count > maxCount) maxCount = count;
        }

        return maxCount;
    }
}
