package gr.aueb.cf.projects.project8;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Υλοποιεί το παιχνίδι της τρίλιζας. Δέχεται τα ονόματα δύο παικτών και
 * στη συνέχεια τη θέση επιλογής τους, εναλλάξ. Ελέγχει αν η επιλογή
 * αντιστοιχεί σε έγκυρη θέση και την εμφανίζει στην τρίλιζα που έχει
 * εκτυπωθεί στην οθόνη. Ελέγχει (από την 5η κίνηση κι έπειτα) αν έχει
 * γίνει "τρίλιζα" κι εμφανίζει το όνομα του νικητή ή ισοπαλία αν συμπληρωθούν
 * οι θέσεις χωρίς να γίνει "τρίλιζα". Καταγράφει στατιστικά και παρουσιάζει
 * στο τέλος του προγράμματος τον αριθμό των νικών κάθε παίκτη, καθώς και
 * τον αριθμό των παιχνιδιών που έληξαν ισόπαλα.
 *
 * @author L. Stavropoulos
 */
public class TicTacToe {

    public static String[][][] graphics = new String[3][3][2];

    public static void main(String[] args) {
        runGame();

    }

    /**
     * Μέθοδος που "τρέχει" το παιχνίδι. Επικοινωνεί με το χρήστη και διαχειρίζεται
     * τον έλεγχο ροής του προγράμματος.
     *
     */
    public static void runGame(){
        Scanner in = new Scanner(System.in);
        String userA;
        String userB;
        int game = 0;
        int round = 1;
        int moveCount = 1;
        int[][] board = new int[3][3];
        int[] move = {-1, 0, 0};
        int userChoice = -1;
        int checkWinner = 0;
        int[] results = new int[3];


        System.out.println("Καλώς ορίσατε στο παιχνίδι της Τρίλιζας!");
        System.out.println("Παρακαλώ δώστε το όνομα του/της παίκτη/τριας 1:");
        userA = in.next();
        System.out.println("Παρακαλώ δώστε το όνομα του/της παίκτη/τριας 2:");
        userB = in.next();


        do {
            game++;

            for (int[] row: board) {
                Arrays.fill(row, 0);
            }

            do {
                System.out.printf("********* Γύρος %d **********", round);
                System.out.println();
                System.out.printf("Παίζει: %s", (moveCount % 2 == 0) ? userB : userA);
                System.out.println();
                System.out.println("****************************");
                System.out.println();
                showGrid(move);
                move[0] = (moveCount % 2 == 0) ? 2 : 1;

                do {
                    System.out.println("Παρακαλώ δώστε τον αριθμό που αντιστοιχεί στη θέση της επιλογής σας:");


                    try {
                        while ((userChoice = in.nextInt()) < 1 || userChoice > 9) {
                            System.out.println("Μη έγκυρη επιλογή! Η θέση πρέπει να είναι από 0 έως 9");
                        }
                    } catch (InputMismatchException e) {
                        e.printStackTrace();
                        System.out.println("Μη έγκυρη επιλογή! Η θέση είναι ακέραιος αριθμός.");
                        System.out.println("Έξοδος από το πρόγραμμα...");
                        return;
                    }

                    move = playerMove(userChoice, move);

                    if (validMove(move, board)) break;

                    System.out.println("Η θέση είναι κατειλημμένη! Δώστε μία έγκυρη θέση:");
                } while (true);

                board[move[1]][move[2]] = move[0];

                System.out.println();

                if ((moveCount >= 5) && ((checkWinner = checkWinner(board, move)) != 0)) System.out.printf("Νίκη για τον/την %s%n", ((checkWinner % 2) != 0) ? userA : userB);

                if (moveCount == 9) {
                    System.out.println("Ισοπαλία");
                    break;
                }

                moveCount++;
                round += moveCount % 2;

            } while (checkWinner == 0);
            showGrid(move);
            results[checkWinner] ++;

            System.out.println("Θέλετε να παίξετε άλλο παιχνίδι;");
            System.out.println("Δώστε 0 για νέο γύρο ή οποιαδήποτε άλλη επιλογή για τερματισμό");

            round = 1;
            moveCount =1;
            checkWinner = 0;
            move[0] = -1;

        } while (in.nextInt() == 0);

        System.out.println("********** Αποτελέσματα **********");
        System.out.printf("%s: %d\t%s: %d\tΙσοπαλίες: %d", userA, results[1], userB, results[2], results[0]);

    }

    /**
     * Εμφανίζει το γραφικό περιβάλλον του παιχνιδιού
     *
     * @param move      Ακέραιος που αντιστοιχεί στην κίνηση του παίκτη
     */
    public static void showGrid(int[] move){
        final String GRID_HORIZONTAL = "_____";
        final String GRID_VERTICAL = "|";
        final String SPACE_HORIZONTAL = "  ";
        final String X_TOP = " \\ / ";
        final String X_BOTTOM = "_/_\\_";
        final String O_TOP = " / \\ ";
        final String O_BOTTOM = "_\\_/_";

        if (move[0] == 2) {
            if (move[2] == 2){
                graphics[move[1]][move[2]][0] = O_TOP;
                graphics[move[1]][move[2]][1] = O_BOTTOM;
            } else {
                graphics[move[1]][move[2]][0] = O_TOP.concat(GRID_VERTICAL);
                graphics[move[1]][move[2]][1] = O_BOTTOM.concat(GRID_VERTICAL);
            }
        } else if (move[0] == 1){
            if (move[2] == 2){
                graphics[move[1]][move[2]][0] = X_TOP;
                graphics[move[1]][move[2]][1] = X_BOTTOM;
            } else {
                graphics[move[1]][move[2]][0] = X_TOP.concat(GRID_VERTICAL);
                graphics[move[1]][move[2]][1] = X_BOTTOM.concat(GRID_VERTICAL);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    graphics[i][j][0] = SPACE_HORIZONTAL.concat(String.valueOf((i * 3 + 1) + j)).concat(SPACE_HORIZONTAL).concat(GRID_VERTICAL);
                }
                graphics[i][2][0] = SPACE_HORIZONTAL.concat(String.valueOf(i * 3 + 3)).concat(SPACE_HORIZONTAL);
                graphics[2][i][1] = SPACE_HORIZONTAL.concat(" ").concat(SPACE_HORIZONTAL).concat(GRID_VERTICAL);
            }

            graphics[2][2][1] += ("\b");

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    graphics[i][j][1] = GRID_HORIZONTAL.concat(GRID_VERTICAL);
                }
                graphics[i][2][1] = GRID_HORIZONTAL;
            }
        }

        for (int i = 0; i < 3; i++) {
            graphics[2][i][1] = graphics[2][i][1].replace("_", " ");
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(graphics[i][k][j]);
                }
                System.out.println();
            }
        }
    }

    /**
     * Μετατρέπει τον ακέραιο επιλογής του παίκτη σε συντεταγμένες του δισδιάστατου πίνακα
     * της τρίλιζας.
     *
     * @param userChoice        Ακέραιος επιλογής χρήστη
     * @param move              Πίνακας κίνησης παίκτη
     * @return                  Πίνακας που αντιστοιχεί στις "συντεταγμένες" επιλογής του χρήστη
     */
    public static int[] playerMove(int userChoice, int[] move) {

        if (userChoice % 3 == 0) {
            move[2] = 2;
        } else if (userChoice % 3 == 2) {
            move[2] = 1;
        } else {
            move[2] = 0;
        }

        move[1] = (userChoice - move[2] - 1) / 3;

        return move;
    }

    /**
     * Ελέγχει αν ο κίνηση του χρήστη είναι έγκυρη.
     *
     * @param move      Κίνηση παίκτη
     * @param board     Πίνακας τρίλιζας
     * @return          true αν η κίνηση είναι έγκυρη
     */
    public static boolean validMove(int[] move, int[][] board) {

        if (board[move[1]][move[2]] != 0) {
            return false;
        }

        return true;
    }

    /**
     * Ελέγχει αν μετά την τελευταία κίνηση έχει γίνει "τρίλιζα" κι επιστρέφει τον αριθμό του νικητή.
     * (Ελέγχει μόνο τις πιθανές για τρίλιζα θέσεις σύμφωνα με την τελευταία κίνηση κι όχι
     * ολόκληρο τον πίνακα.)
     *
     * @param board     Πίνακας τρίλιζας
     * @param move      Κίνηση παίκτη
     * @return          -1 αν δεν υπάρχει τρίλιζα αλλιώς τον αριθμό του νικητή
     */
    public static int checkWinner(int[][] board, int[] move) {
        boolean checkH = true;
        boolean checkV = true;
        boolean checkD = (move[1] == move[2]);
        boolean checkS = (2 - move[1] == move[2]);

        for (int i = 0; i < 3; i++) {
            if (checkH) checkH = (board[move[1]][i] == move[0]);
            if (checkV) checkV = (board[i][move[2]] == move[0]);

            if (checkD) checkD = (board[i][i] == move[0]);
            if (checkS) checkS = (board[2-i][i] == move[0]);
        }

        System.out.println(checkH);
        System.out.println(checkV);
        System.out.println(checkD);
        System.out.println(checkS);
        return (checkH || checkV || checkD || checkS) ? move[0] : 0;
    }

}
