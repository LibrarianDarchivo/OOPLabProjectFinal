import java.util.ArrayList;
import java.util.List;

class BankAccount {
    protected double balance;   // Protected to give access to subclass/es
    protected final List<String> transactionLogs; // Initialize list
                                                  // ↑ Used by ArrayList<>()
    protected int transactionID; // Unique transactions ID
    protected boolean isSavings; // Determine whether the account is a savings account

    private String username;
    private String password;

    // Default constructor
    public BankAccount(double initialBalance, boolean isSavings) {
        this.balance = initialBalance;              // Sets initial balance
        this.transactionLogs = new ArrayList<>();   // Initialize transaction log list
        this.transactionID = 1;                     // Start transaction IDs from 1
        this.isSavings = isSavings;
    }

    // Initialize log in stuff
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Credentials checker --- Basically a long input == username && password checker
    public boolean validateCredentials(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Deposit
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;           // Balance = Balance + Amount
            logTransaction("Deposited $" + amount);
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;          // Balance = Balance - Amount
            logTransaction("Withdrew $" + amount);
            System.out.println("Successfully withdrew: $" + amount);
        } else {
            System.out.println("Invalid or insufficient amount.");
        }
    }

    // Log transactions
    protected void logTransaction(String action) {
        /*

        As long as the user enters new transactions, the transaction log will keep adding entries
        since it's an ArrayList (dynamic)

        */
        transactionLogs.add("#" + transactionID + " - " + action + " | New balance: $" + balance);
        transactionID++;    // Increment transaction ID by 1 after a log is added
                            // ↑ Basically queues a new ID
                            // | Log 1 will have ID 1, so next Log will have ID 2
    }

    // Show all transactions
    public void showTransactionLogs() {
        if (transactionLogs.isEmpty()) {            // If transactionLogs = 0
                                                    // ↑ Throw empty message
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction logs:");
            for (String log : transactionLogs) {    // For each to print out all existing transaction logs
                System.out.println(log);
            }
        }
    }

    // Search transaction by ID
    public void searchTransactionByID(int id) {
        boolean found = false;                      // Start search by flagging as false
        for (String log : transactionLogs) {        // For each to print out all existing transaction logs
            if (log.startsWith("#" + id)) {
                System.out.println("Transaction found: " + log);
                found = true;                       // If transaction log is found, return true
                break;                              // If transaction log is NOT found, returns false
            }                                       // |
        }                                           // |
                                                    // |
        if (!found) {                               // ↓
            System.out.println("Transaction with ID #" + id + " not found.");
        }
    }

    // Display account details at start
    public void showAccountDetails() {
        System.out.println("Is Savings Account: " + isSavings);
    }
}
