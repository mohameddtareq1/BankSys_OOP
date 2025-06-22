package banksystem.account;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends Account {
    private int clientAccountID;
    private boolean accountStateIsActive;
    public float balance;
    public boolean isSavingsAccount;

    public Client() {
        super();
    }

    public Client(String fName, String lName, long pNumber, String address,
                  String username, String password, int clientAccountID, boolean accountState,
                  float balance, boolean isSavingsAccount) {
        super(fName, lName, pNumber, address, username, password);
        this.clientAccountID = clientAccountID;
        this.accountStateIsActive = accountState;
        this.balance = balance;
        this.isSavingsAccount = isSavingsAccount;
    }

    public int getClientAccountID() {
        return clientAccountID;
    }

    public boolean getAccountStateIsActive() {
        return accountStateIsActive;
    }

    public void setAccountState(boolean accountStateIsActive) {
        this.accountStateIsActive = accountStateIsActive;
    }

    public void editPersonalInfo(Client client) {
        char redo = 'Y';
        do {
            System.out.print("\n1.Firstname" +
                             "\n2.Lastname" +
                             "\n3.Phone number" +
                             "\n4.Address" +
                             "\nSelect: ");

            Scanner input = new Scanner(System.in);
            int selection;
            do {
                try {
                    selection = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("\nError: Invalid input!\n Enter a number: ");
                    input.nextLine();
                }
            } while (true);

            if (selection == 1) {
                System.out.print("Enter your new firstname: ");
                input.nextLine();
                client.firstName = input.nextLine();
            } else if (selection == 2) {
                System.out.print("Enter your new lastname: ");
                input.nextLine();
                client.lastName = input.nextLine();
            } else if (selection == 3) {
                System.out.print("Enter your new phone number: ");
                do {
                    try {
                        client.phoneNumber = input.nextLong();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.print("\nError: Invalid input!\n Enter a number: ");
                        input.nextLine();
                    }
                } while (true);
            } else if (selection == 4) {
                System.out.print("Enter your new address: ");
                input.nextLine();
                client.address = input.nextLine();
            } else {
                System.out.println("Error: Invalid selection!");
                continue;
            }
            System.out.println("Do you wish to edit another attribute? (y/n)");
            redo = input.next().charAt(0);
        } while (redo == 'y' || redo == 'Y');
    }

    public void displayAccountDetails() {
        System.out.println("\nName: " + firstName + " " + lastName +
                           "\nPhone number: " + phoneNumber +
                           "\nAddress: " + address + "\n" +
                           "\nUsername: " + getUsername() +
                           "\nPassword: " + getPassword() +
                           "\nID: " + getClientAccountID() +
                           "\nAccount state: " + getAccountStateIsActive() +
                           "\nBalance: " + balance +
                           "\nSavings Account: " + isSavingsAccount + "\n");
    }

    public void displayAccountIdentification() {
        System.out.println("\nUsername: " + getUsername() +
                           "\nPassword: " + getPassword() +
                           "\nID: " + getClientAccountID() +
                           "\nAccount state: " + getAccountStateIsActive() +
                           "\nSavings Account: " + isSavingsAccount + "\n");
    }

    public void depositTransaction(Client client, ArrayList<PreviousTransaction> transactionHistory) {
        System.out.print("\nDeposit amount: ");
        Scanner input = new Scanner(System.in);
        float DepositAmount;
        do {
            try {
                DepositAmount = input.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\nError: Invalid input!\nEnter a number: ");
                input.nextLine();
            }
        } while (true);
        client.balance += DepositAmount;

        System.out.println("Deposited: " + DepositAmount + "\nNew balance: " + client.balance);
        transactionHistory.add(new PreviousTransaction(clientAccountID, client.balance,
                client.balance - DepositAmount, "Deposit"));
    }

    public void withdrawalTransaction(Client client, ArrayList<PreviousTransaction> transactionHistory) {
        System.out.print("\nWithdrawal amount: ");
        Scanner input = new Scanner(System.in);
        float withdrawalAmount;
        do {
            try {
                withdrawalAmount = input.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\nError: Invalid input!\nEnter a number: ");
                input.nextLine();
            }
        } while (true);

        if (client.balance >= withdrawalAmount) {
            client.balance -= withdrawalAmount;
            System.out.println("Withdrew: " + withdrawalAmount + "\nNew balance: " + client.balance);
            transactionHistory.add(new PreviousTransaction(clientAccountID, client.balance,
                    client.balance + withdrawalAmount, "Withdrawal"));
        } else
            System.out.println("Error: Insufficient funds!");
    }

    public void transferTransaction(Client client, ArrayList<Client> clients, ArrayList<PreviousTransaction> transactionHistory) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nTransferal amount: ");
        float transferAmount;
        do {
            try {
                transferAmount = input.nextFloat();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\nError: Invalid input!\nEnter a number: ");
                input.nextLine();
            }
        } while (true);

        if (client.balance >= transferAmount) {
            Employee tempEmp = new Employee();
            Client transferToClient = tempEmp.searchClientAccount(clients);

            if (transferToClient != null) {
                client.balance -= transferAmount;
                transferToClient.balance += transferAmount;
                System.out.println("Transferred: " + transferAmount + "\nNew balance: " + client.balance);
                transactionHistory.add(new PreviousTransaction(clientAccountID, client.balance,
                        client.balance + transferAmount, "transferWithdrawal"));
                transactionHistory.add(new PreviousTransaction(transferToClient.clientAccountID, transferToClient.balance,
                        transferToClient.balance - transferAmount, "transferDeposit"));
            } else
                System.out.println("Error: Client not found!");
        } else
            System.out.println("Insufficient funds!");
    }
}