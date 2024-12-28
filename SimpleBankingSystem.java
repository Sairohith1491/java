import java.util.*;

class Account {
    private static int accountCounter = 1000;
    private final int accountNumber;
    private final String accountHolderName;
    private double balance;
    private final List<String> transactionHistory;

    public Account(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        synchronized (Account.class) {
            this.accountNumber = accountCounter++;
        }
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial deposit: " + initialDeposit);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class SimpleBankingSystem {
    private final Map<Integer, Account> accounts;

    public SimpleBankingSystem() {
        accounts = new HashMap<>();
    }

    public void createAccount(String name, double initialDeposit) {
        Account account = new Account(name, initialDeposit);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account created successfully! Your account number is: " + account.getAccountNumber());
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public static void main(String[] args) {
        SimpleBankingSystem bankingSystem = new SimpleBankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Simple Banking System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Display Account Details");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    bankingSystem.createAccount(name, initialDeposit);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    Account depositAccount = bankingSystem.getAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    Account withdrawAccount = bankingSystem.getAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    int balanceAccountNumber = scanner.nextInt();
                    Account balanceAccount = bankingSystem.getAccount(balanceAccountNumber);
                    if (balanceAccount != null) {
                        System.out.println("Balance: " + balanceAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    int detailsAccountNumber = scanner.nextInt();
                    Account detailsAccount = bankingSystem.getAccount(detailsAccountNumber);
                    if (detailsAccount != null) {
                        detailsAccount.displayAccountDetails();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    int historyAccountNumber = scanner.nextInt();
                    Account historyAccount = bankingSystem.getAccount(historyAccountNumber);
                    if (historyAccount != null) {
                        historyAccount.displayTransactionHistory();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
