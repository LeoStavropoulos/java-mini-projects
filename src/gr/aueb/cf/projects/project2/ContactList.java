package gr.aueb.cf.projects.project2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Πρόγραμμα διαχείρισης λίστας επαφών. Μπορεί να δεχθεί έως 500 επαφές,
 * η καθεμία από τις οποίες περιέχει Επώνυμο, Όνομα και Αριθμό Τηλεφώνου.
 * Υποθέτουμε ότι κάθε επαφή είναι μοναδική, δηλαδή δεν υπάρχει άλλη επαφή
 * με το ίδιο ονοματεπώνυμο ή αριθμό τηλεφώνου.
 * Ο χρήστης έχει τις εξής επιλογές: αναζήτηση επαφής με βάση το ονοματεπώνυμο ή
 * τον αριθμό τηλεφώνου, δημιουργία νέας επαφής (εφόσον τα στοιχεία που καταχωρεί
 * δεν υπάρχουν ήδη), ενημέρωση των στοιχείων της επαφής, διαγραφή επαφής, καθώς και
 * εμφάνιση του καταλόγου επαφών με αύξουσα ή φθίνουσα αλφαβητική σειρά (με βάση το επώνυμο).
 *
 * @author L. Stavropoulos
 */
public class ContactList {

    static String[][] contacts = new String[500][3];
    static int lastContact = -1;


    public static void main(String[] args) {

        runApp();
        System.out.println("**************************************************************");
        System.out.println("******************* Έξοδος από το πρόγραμμα ******************");

    }

    /**
     * Εμφανίζει το μενού επιλογών προς το χρήστη
     * και διαχειρίζεται την επικοινωνία με αυτόν.
     * Διαβάζει από το πληκτρολόγιο τις επιλογές του
     * χρήστη και καλεί τις ανάλογες μεθόδους.
     */
    public static void runApp() {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        int position = -1;
        int searchType = 0;
        int phonePosition = -1;
        int updateField = -1;
        int order = -1;
        String lastName = "";
        String firstName = "";
        String phoneNumber = "";
        String deleted = "{";


        //TEST DATA ------------------------------------------------------

        contacts[0] = new String[]{"Stark", "Tony", "0000000000"};
        contacts[1] = new String[]{"Banner", "Bruce", "1111111111"};
        contacts[2] = new String[]{"Strange", "Stephen", "2222222222"};
        contacts[3] = new String[]{"Parker", "Peter", "3333333333"};
        contacts[4] = new String[]{"Romanoff", "Natasha", "4444444444"};
        contacts[5] = new String[]{"Maximoff", "Wanda", "5555555555"};
        contacts[6] = new String[]{"Rogers", "Steve", "6666666666"};
        contacts[7] = new String[]{"Carter", "Peggy", "7777777777"};
        contacts[8] = new String[]{"Odinsson", "Thor", "8888888888"};
        contacts[9] = new String[]{"Danvers", "Carol", "9999999999"};

        lastContact = 9;

        //----------------------------------------------------------------

        System.out.println("********************** Κατάλογος Επαφών **********************");

        for(;;) {
            System.out.println("**************************************************************");
            System.out.println("Παρακαλώ δώστε έναν ακέραιο σύμφωνα με τις παρακάτω επιλογές: ");
            System.out.println("1. Αναζήτηση Επαφής");
            System.out.println("2. Δημιουργία Νέας Επαφής");
            System.out.println("3. Ενημέρωση Επαφής");
            System.out.println("4. Διαγραφή Επαφής");
            System.out.println("5. Εμφάνιση Επαφών");
            System.out.println("6. Έξοδος");
            System.out.println("**************************************************************");
            System.out.println("**************************************************************");
            choice = in.nextInt();
            if ((choice < 1) || (choice > 6)) System.out.println("Μη έγκυρη επιλογή!");

            switch (choice) {
                case 1:
                    System.out.println("Επιλέξατε Αναζήτηση.");

                    loop: do {
                        System.out.println("Δώστε τον αριθμό που αντιστοιχεί σε μία από τις παρακάτω επιλογές: ");
                        System.out.println("1. Αναζήτηση με βάση το ονοματεπώνυμο");
                        System.out.println("2. Αναζήτηση με βάση τον αριθμό τηλεφώνου");
                        System.out.println("3. Ακύρωση");

                        searchType = in.nextInt();

                        switch (searchType){
                            case 1:
                                System.out.println("Παρακαλώ δώστε επώνυμο:");
                                lastName = in.next();
                                System.out.println("Παρακαλώ δώστε όνομα:");
                                firstName = in.next();

                                position = getIndexByName(lastName, firstName);

                                if (position == -1) {
                                    System.out.println("Η επαφή δε βρέθηκε.");
                                }
                                else {
                                    System.out.printf("Η επαφή %s %s βρέθηκε στη θέση %d και αντιστοιχεί στον αριθμό τηλεφώνου: %s%n", lastName, firstName, position, contacts[position][2]);
                                }
                                break;
                            case 2:
                                System.out.println("Παρακαλώ δώστε αριθμό τηλεφώνου:");
                                phoneNumber = in.next();


                                position = getIndexByPhoneNumber(phoneNumber);

                                if (position == -1) {
                                    System.out.println("Ο αριθμός δεν αντιστοιχεί σε επαφή.");
                                }
                                else {
                                    System.out.printf("Ο αριθμός τηλεφώνου: %s αντιστοιχεί στην επαφή: %s %s, στη θέση %d%n", contacts[position][2], contacts[position][0], contacts[position][1], position);
                                }
                                break;
                            case 3:
                                System.out.println("Ακύρωση ενέργειας");
                                break loop;
                            default:
                                System.out.println("Μη έγκυρη επιλογή!");
                                break;
                        }

                    } while ((searchType != 1) && (searchType != 2));

                    break;
                case 2:
                    System.out.println("Επιλέξατε Εισαγωγή Επαφής.");
                    System.out.println("Παρακαλώ δώστε επώνυμο:");
                    lastName = in.next();
                    System.out.println("Παρακαλώ δώστε όνομα:");
                    firstName = in.next();

                    position = getIndexByName(lastName, firstName);

                    if (position == -1) {
                        System.out.println("Παρακαλώ δώστε τον αριθμό τηλεφώνου της επαφής:");
                        phoneNumber = in.next();

                        phonePosition = getIndexByPhoneNumber(phoneNumber);

                        if (phonePosition != -1) {
                            System.out.printf("Ο αριθμός τηλεφώνου υπάρχει ήδη, στην επαφή: %s %s", contacts[phonePosition][0], contacts[phonePosition][1]);
                            break;
                        }

                        insertContact(lastName, firstName, phoneNumber);

                        System.out.println("Η επαφή δημιουργήθηκε επιτυχώς!");
                    } else {
                        System.out.println("Το ονοματεπώνυμο που δώσατε υπάρχει ήδη!");
                    }

                    break;
                case 3:
                    System.out.println("Επιλέξατε Ενημέρωση Επαφής.");
                    System.out.println("Παρακαλώ δώστε επώνυμο:");
                    lastName = in.next();
                    System.out.println("Παρακαλώ δώστε όνομα:");
                    firstName = in.next();

                    position = getIndexByName(lastName, firstName);
                    if (position == -1) {
                        System.out.println("Δεν υπάρχει υπάρχει επαφή με τα στοιχεία που δώσατε!");
                        return;
                    }

                    for (;;){
                        do {
                            System.out.println("Δώστε τον αριθμό που αντιστοιχεί σε μία από τις παρακάτω επιλογές:");
                            System.out.println("1. Αλλαγή επωνύμου");
                            System.out.println("2. Αλλαγή ονόματος");
                            System.out.println("3. Αλλαγή τηλεφώνου");
                            System.out.println("4. Ακύρωση");

                            updateField = in.nextInt();

                            if (updateField < 1 || updateField > 4) System.out.println("Μη έγκυρη επιλογή!");

                        } while (updateField < 1 || updateField > 4);

                        if (updateField == 4) {
                            System.out.println("Ακύρωση ενέργειας.");
                            break;
                        }

                        System.out.print("Παρακαλώ δώστε νέο ");

                        switch (updateField) {
                            case 1:
                                System.out.println("επώνυμο:");
                                break;
                            case 2:
                                System.out.println("όνομα:");
                                break;
                            case 3:
                                System.out.println("αριθμό τηλεφώνου:");
                                break;
                        }

                        updateContact(position,updateField - 1, in.next());

                        System.out.println("Επιτυχής ενημέρωση επαφής");
                        System.out.printf("Η ενημερωμένη επαφή είναι: {%s, %s, %s}%n", contacts[position][0], contacts[position][1], contacts[position][2]);

                        System.out.println("Θέλετε να κάνετε άλλη αλλαγή;");
                        System.out.println("Δώστε 0 για έξοδο ή οποιαδήποτε άλλη τιμή για να συνεχίσετε");
                        if (in.nextInt() == 0) break;
                    }
                    break;
                case 4:
                    System.out.println("Επιλέξατε Διαγραφή Επαφής.");
                    System.out.println("Παρακαλώ δώστε επώνυμο:");
                    lastName = in.next();
                    System.out.println("Παρακαλώ δώστε όνομα:");
                    firstName = in.next();

                    position = getIndexByName(lastName, firstName);
                    if (position == -1) {
                        System.out.println("Δεν υπάρχει υπάρχει επαφή με τα στοιχεία που δώσατε!");
                        return;
                    }

                    for (int i = 0; i < 3; i++) {
                        deleted = deleted.concat(contacts[position][i]).concat(", ");
                    }

                    deleted = deleted.concat("\b\b}");

                    deleteContact(position);

                    System.out.printf("Η επαφή: %s διαγράφηκε επιτυχώς.%n", deleted);

                    break;
                case 5:
                    System.out.println("Επιλέξατε Εμφάνιση επαφών.");

                    do {
                        System.out.println("Δώστε 0 για αύξουσα ή 1 για φθίνουσα σειρά: ");

                        order = in.nextInt();

                        if ((order != 1) && (order != 0)) System.out.println("Μη έγκυρη επιλογή!");

                    } while ((order != 1) && (order != 0));

                    System.out.println("***************** {Επώνυμο, Όνομα, Τηλέφωνο} *****************");

                    sortAndDisplayContacts(order);

                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }

    /**
     * Αναζητά αν υπάρχει επαφή με το ονοματεπώνυμο που δέχεται ως είσοδο.
     *
     * @param lastname      Επώνυμο επαφής
     * @param firstname     Όνομα επαφής
     * @return              Θέση επαφής ή -1 αν δε βρεθεί
     */
    public static int getIndexByName(String lastname, String firstname) {

        for (int i = 0; i <= lastContact; i++) {
            if ((contacts[i][0].equals(lastname)) && (contacts[i][1].equals(firstname))) return i;
        }

        return -1;
    }

    /**
     * Αναζητά αν υπάρχει επαφή με τον αριθμό τηλεφώνου που δέχεται ως είσοδο.
     *
     * @param phoneNumber       Αριθμός τηλεφώνου
     * @return                  Θέση επαφής ή -1 αν δε βρεθεί
     */
    public static int getIndexByPhoneNumber(String phoneNumber) {

        for (int i = 0; i <= lastContact; i++) {
            if (contacts[i][2].equals(phoneNumber)) return i;
        }

        return -1;
    }

    /**
     * Δημιουργεί νέα επαφή κι αυξάνει το δείκτη της τελευταίας θέσης κατά ένα
     *
     * @param lastName      Επώνυμο νέας επαφής
     * @param firstName     Όνομα νέας επαφής
     * @param phoneNumber   Αριθμός τηλεφώνου νέας επαφής
     */
    public static void insertContact(String lastName, String firstName, String phoneNumber) {
        contacts[++lastContact] [0] = lastName;
        contacts[lastContact] [1] = firstName;
        contacts[lastContact] [2] = phoneNumber;
    }

    /**
     * Ενημερώνει ένα πεδίο της επιλεγμένης επαφής
     *
     * @param position      Θέση επαφής
     * @param updateField   Πεδίο προς ενημέρωση
     * @param newValue      Νέα τιμή
     */
    public static void updateContact (int position, int updateField, String newValue) {
        contacts[position][updateField] = newValue;
    }

    /**
     * Διαγράφει την επιλεγμένη επαφή. Στην πραγματικότητα μετατοπίζει όλα τα στοιχεία που βρίσκονται
     * κάτω από αυτήν κατά μία θέση πάνω και μειώνει το δείκτη τελευταίας θέσης κατά ένα.
     * Τα στοιχεία της επαφής ουσιαστικά δε διαγράφονται, αλλά μετατοπίζονται στη θέση lastContact + 1!
     *
     * @param position      Θέση επαφής προς διαγραφή
     */
    public static void deleteContact(int position) {

        lastContact--; // Η τελευταία επαφή θα υπάρχει και στη θέση lastContact και στη θέση lastContact + 1.

        for (int i = position; i < lastContact; i++) {
            for (int j = 0; j < 3; j++) {
                contacts[i][j] = contacts[i + 1][j];
            }
        }

    }

    /**
     * Εκχωρεί τις επαφές σ ένα προσωρινό πίνακα, τον οποίο ταξινομεί με βάση το επώνυμο
     * και εκτυπώνει τα ταξινομημένα, πλέον, στοιχεία.
     *  @param order             Επιλογή αύξουσας ή φθίνουσας ταξινόμησης
     *
     */
    public static void sortAndDisplayContacts(int order) {
        String[][] tmp = new String[500][3];
        int pivot = lastContact + 1;

        tmp = Arrays.copyOf(contacts, pivot);

        if (order == 0) {
            Arrays.sort(tmp, 0, pivot, (a1, a2) -> a1[0].compareTo(a2[0]) );
        } else {
            Arrays.sort(tmp, 0, pivot, (a1, a2) -> a2[0].compareTo(a1[0]));
        }

        for (int i = 0; i < pivot; i++) {
            System.out.print("\t\t\t\t{");
            for (int j = 0; j < 3; j++) {
                System.out.print(tmp[i][j] + ", ");
            }
            System.out.println("\b\b}");
        }

        System.out.println("**************************************************************");

    }
}
