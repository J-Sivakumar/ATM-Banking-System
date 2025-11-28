import java.util.Scanner;

public class ATM {
    private BankService bankService;

    public ATM(BankService bankService) {
        this.bankService = bankService;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Welcome to Mini ATM =====");
        System.out.print("Enter account number: ");
        String accNum = sc.nextLine().trim();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine().trim();

        BankAccount account = bankService.login(accNum, pin);
        if (account == null) {
            System.out.println("Invalid account number or PIN.");
            sc.close();
            return;
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount;
                    try {
                        depositAmount = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                        break;
                    }
                    if (depositAmount <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }
                    account.setBalance(account.getBalance() + depositAmount);
                    bankService.updateBalance(account);
                    bankService.addTransaction(account, "Deposit", depositAmount);
                    System.out.println("Deposited: " + depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ");
                    double withdrawAmount;
                    try {
                        withdrawAmount = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount!");
                        break;
                    }
                    if (withdrawAmount <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }
                    if (withdrawAmount <= account.getBalance()) {
                        account.setBalance(account.getBalance() - withdrawAmount);
                        bankService.updateBalance(account);
                        bankService.addTransaction(account, "Withdraw", withdrawAmount);
                        System.out.println("Withdrawn: " + withdrawAmount);
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;
                case 4:
                    bankService.showTransactions(account);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        BankService bankService = new BankService();
        ATM atm = new ATM(bankService);
        atm.start();
    }
}
