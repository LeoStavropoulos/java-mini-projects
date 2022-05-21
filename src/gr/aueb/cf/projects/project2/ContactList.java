package gr.aueb.cf.projects.project2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Contact List management system. It can store up to 500 contacts, having Lastname,
 * Firstname and Phone Number fields. Each contact should be unique, meaning that
 * there should not be any other contact having the same name or number.
 * The user is given the following choices: Contact Search, by name or number,
 * New Contact Insertion , provided that the given details do not already exist,
 * Contact Update, Contact Deletion, provided that the given contact exists,
 * Contact List Display, sorted by lastname, either in ascending or descending order.
 *
 * @author L. Stavropoulos
 */
public class ContactList {

    static String[][] contacts = new String[500][3];
    static int lastContact = -1;


    public static void main(String[] args) {

        runApp();
        System.out.println("**************************************************************");
        System.out.println("******************* Exiting programme ******************");

    }

    /**
     * Displays a menu o choices to the user and manages
     * the communication with them. Gets the user's choices
     * and calls the corresponding methods.
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

        System.out.println("********************** Contact List **********************");

        for(;;) {
            System.out.println("**************************************************************");
            System.out.println("Please enter an integer, corresponding to one of the following choices: ");
            System.out.println("1. Search Contacts");
            System.out.println("2. Create New Contact");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Display Contact List");
            System.out.println("6. Exit");
            System.out.println("**************************************************************");
            System.out.println("**************************************************************");
            choice = in.nextInt();
            if ((choice < 1) || (choice > 6)) System.out.println("Invalid choice!");

            switch (choice) {
                case 1:
                    System.out.println("You chose Contact Search.");

                    loop: do {
                        System.out.println("Enter the number of one of the following choices: ");
                        System.out.println("1. Search by name");
                        System.out.println("2. Search by phone number");
                        System.out.println("3. Cancel");

                        searchType = in.nextInt();

                        switch (searchType){
                            case 1:
                                System.out.println("Please give lastname:");
                                lastName = in.next();
                                System.out.println("Please give firstname:");
                                firstName = in.next();

                                position = getIndexByName(lastName, firstName);

                                if (position == -1) {
                                    System.out.println("Contact not found.");
                                }
                                else {
                                    System.out.printf("Contact %s %s found in position %d and correspond to the phone number: %s%n", lastName, firstName, position, contacts[position][2]);
                                }
                                break;
                            case 2:
                                System.out.println("Please give phone number: ");
                                phoneNumber = in.next();


                                position = getIndexByPhoneNumber(phoneNumber);

                                if (position == -1) {
                                    System.out.println("The given number does not correspond to a contact.");
                                }
                                else {
                                    System.out.printf("The phone number: %s corresponds to the contact: %s %s, in position %d%n", contacts[position][2], contacts[position][0], contacts[position][1], position);
                                }
                                break;
                            case 3:
                                System.out.println("Canceling");
                                break loop;
                            default:
                                System.out.println("Invalid choice!");
                                break;
                        }

                    } while ((searchType != 1) && (searchType != 2));

                    break;
                case 2:
                    System.out.println("You chose Create New Contact.");
                    System.out.println("Please give lastname:");
                    lastName = in.next();
                    System.out.println("Please give firstname:");
                    firstName = in.next();

                    position = getIndexByName(lastName, firstName);

                    if (position == -1) {
                        System.out.println("Please given contact's phone number:");
                        phoneNumber = in.next();

                        phonePosition = getIndexByPhoneNumber(phoneNumber);

                        if (phonePosition != -1) {
                            System.out.printf("Phone number already exists, in contact: %s %s", contacts[phonePosition][0], contacts[phonePosition][1]);
                            break;
                        }

                        insertContact(lastName, firstName, phoneNumber);

                        System.out.println("Contact successfully created!");
                    } else {
                        System.out.println("The name you entered already exists!");
                    }

                    break;
                case 3:
                    System.out.println("You chose Contact Update.");
                    System.out.println("Please gie lastname:");
                    lastName = in.next();
                    System.out.println("Please give firstname:");
                    firstName = in.next();

                    position = getIndexByName(lastName, firstName);
                    if (position == -1) {
                        System.out.println("There is not any contact having the given details!");
                        return;
                    }

                    for (;;){
                        do {
                            System.out.println("Please give the corresponding number of one of the following choices:");
                            System.out.println("1. Change lastname.");
                            System.out.println("2. Change firstname.");
                            System.out.println("3. Change phone number.");
                            System.out.println("4. Cancel.");

                            updateField = in.nextInt();

                            if (updateField < 1 || updateField > 4) System.out.println("Invalid choice!");

                        } while (updateField < 1 || updateField > 4);

                        if (updateField == 4) {
                            System.out.println("Canceling.");
                            break;
                        }

                        System.out.print("Please give the new ");

                        switch (updateField) {
                            case 1:
                                System.out.println("lastname:");
                                break;
                            case 2:
                                System.out.println("firstname:");
                                break;
                            case 3:
                                System.out.println("phone number:");
                                break;
                        }

                        updateContact(position,updateField - 1, in.next());

                        System.out.println("Contact updated successfully");
                        System.out.printf("The updated contact is: {%s, %s, %s}%n", contacts[position][0], contacts[position][1], contacts[position][2]);

                        System.out.println("Would you like to make any more changes?");
                        System.out.println("Enter 0 for exit or any other value to continue");
                        if (in.nextInt() == 0) break;
                    }
                    break;
                case 4:
                    System.out.println("You chose Delete Contact.");
                    System.out.println("Please give lastname:");
                    lastName = in.next();
                    System.out.println("Please give firstname:");
                    firstName = in.next();

                    position = getIndexByName(lastName, firstName);
                    if (position == -1) {
                        System.out.println("There is no contact with the given details!");
                        return;
                    }

                    for (int i = 0; i < 3; i++) {
                        deleted = deleted.concat(contacts[position][i]).concat(", ");
                    }

                    deleted = deleted.concat("\b\b}");

                    deleteContact(position);

                    System.out.printf("The contact: %s is successfully deleted.%n", deleted);

                    break;
                case 5:
                    System.out.println("You chose Display Contact List.");

                    do {
                        System.out.println("Enter 0 for ascending or 1 for descending order: ");

                        order = in.nextInt();

                        if ((order != 1) && (order != 0)) System.out.println("Invalid choice!");

                    } while ((order != 1) && (order != 0));

                    System.out.println("***************** {Lastname, Firstname, Phone Number} *****************");

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
     * Searches for a contact with the inputed lastname and firstname.
     *
     * @param lastname      Contact's lastname.
     * @param firstname     Contact's firstname.
     * @return              Contact's position or -1 if it is not found.
     */
    public static int getIndexByName(String lastname, String firstname) {

        for (int i = 0; i <= lastContact; i++) {
            if ((contacts[i][0].equals(lastname)) && (contacts[i][1].equals(firstname))) return i;
        }

        return -1;
    }

    /**
     * Searches for a contact with the inputed phone number.
     *
     * @param phoneNumber       Contact's phone number
     * @return                  Contact's position or -1 if it is not found.
     */
    public static int getIndexByPhoneNumber(String phoneNumber) {

        for (int i = 0; i <= lastContact; i++) {
            if (contacts[i][2].equals(phoneNumber)) return i;
        }

        return -1;
    }

    /**
     * Creates new contact and increases the lastContact variable's value by 1.
     *
     * @param lastName      New contact's lastname.
     * @param firstName     New contact's firstname.
     * @param phoneNumber   New contact's phone number.
     */
    public static void insertContact(String lastName, String firstName, String phoneNumber) {
        contacts[++lastContact] [0] = lastName;
        contacts[lastContact] [1] = firstName;
        contacts[lastContact] [2] = phoneNumber;
    }

    /**
     * Updates a chosen contact's field.
     *
     * @param position      Contact's position.
     * @param updateField   Field to update.
     * @param newValue      Field's new value.
     */
    public static void updateContact (int position, int updateField, String newValue) {
        contacts[position][updateField] = newValue;
    }

    /**
     *
     * Deletes a chosen contact. It achieves that by moving each following contact by one position
     * upwards and decreasing the lastContact's value by 1. This way, the deleted contact is
     * moved at the bottom of the list, outside the bounds given by lastContact pointer.
     *
     * @param position      To be deleted contact's position.
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
     * Copies all the contacts to a temporary array, which is being sorted by lastname.
     * The elements of the sorted array are being displayed.
     *  @param order             Choice of ascending or descending order.
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
