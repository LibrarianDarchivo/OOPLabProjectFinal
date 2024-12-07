import java.util.Scanner;

public class Main extends BankAccount {
    public Main() {
        super(1000.00, true); // Initialize base class with default values
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main account = new Main();

        account.showAccountDetails();
        // Fancy greeting message
        System.out.println("\n\n============================\nWelcome to the Money System!\n============================");

        // USER LOGIN
        System.out.println("\n\n============================\n        USER LOGIN:      \n============================\n");
        account.setCredentials("SirJustineJudePura", "SanaPumasaSaOOP2024");

        // Loop until the user enters valid credentials
        while (true) {
            try {
                System.out.println("Enter your username:");
                String inputUsername = sc.nextLine();

                System.out.println("Enter your password:");
                String inputPassword = sc.nextLine();

                if (account.validateCredentials(inputUsername, inputPassword)) {
                    System.out.println("\n============================\n     Login successful!\n============================\n");

                    // MAIN MENU
                    while (true) {
                        try {
                            System.out.println("\n1. Check Balance");
                            System.out.println("2. Transaction (Create Transactions/Transaction Logs)");
                            System.out.println("3. Exit");
                            System.out.print("\nChoose an option: ");
                            int choice = sc.nextInt();

                            if (choice == 1) { // Balance
                                System.out.println("Your current balance is: $" + account.getBalance());
                            } else if (choice == 2) { // Transactions
                                transactionMenu(account, sc);
                            } else if (choice == 3) { // Exit
                                System.out.println("Exiting system...");
                                sc.close();
                                System.exit(0);
                            } else {
                                System.out.println("Invalid option. Please select 1, 2, or 3.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.nextLine(); // Clear scanner buffer
                        }
                    }
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                sc.nextLine(); // Clear scanner buffer
            }
        }
    }

    // Transaction Menu
    public static void transactionMenu(Main account, Scanner sc) {
        try {
            System.out.println("1. Add Transactions");
            System.out.println("2. Transaction Logs");
            System.out.print("Choose an option: ");
            int transact = sc.nextInt();

            if (transact == 1) {        // Add a transaction
                try {
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.print("Choose an option: ");
                    int transact_add = sc.nextInt();

                    if (transact_add == 1) {        // Deposit
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);
                    } else if (transact_add == 2) { // Withdraw
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = sc.nextDouble();
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid option. Returning to main menu.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    sc.nextLine(); // Clear scanner buffer
                }
            } else if (transact == 2) {     // Show transactions
                try {
                    System.out.println("1. Show All Transactions");
                    System.out.println("2. Search Transaction by ID");
                    System.out.print("Choose an option: ");
                    int transactchoice = sc.nextInt();

                    if (transactchoice == 1) {          // Show all transactions
                        account.showTransactionLogs();
                    } else if (transactchoice == 2) {   // Search transaction by ID
                        System.out.print("Enter transaction ID to search: ");
                        int idToSearch = sc.nextInt();
                        account.searchTransactionByID(idToSearch);
                    } else {
                        System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); // Clear scanner buffer
                }
            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            sc.nextLine(); // Clear scanner buffer
        }
    }
}
