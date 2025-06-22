package banksystem.account;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PreviousTransaction implements Serializable {
    int clientID;
    LocalDateTime dateAndTime;
    float afterTransaction, beforeTransaction;
    String transactionType;

    public PreviousTransaction(int clientID, float afterTransaction, float beforeTransaction, String transactionType) {
        this.clientID = clientID;
        this.dateAndTime = LocalDateTime.now();
        this.afterTransaction = afterTransaction;
        this.beforeTransaction = beforeTransaction;
        this.transactionType = transactionType;
    }

    public void selectTransactionHistory(Client client, ArrayList<PreviousTransaction> previousTransactions) {
        boolean performAnotherOperation = true;
        do {
            System.out.print("\nType of transaction to display:" +
                             "\n       1.Deposit" +
                             "\n       2.Withdrawal" +
                             "\n       3.Transfer (deposit)" +
                             "\n       4.Transfer (withdrawal)" +
                             "\n       5.All" +
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

            if (selection == 1)
                this.showTransactionHistory(client.getClientAccountID(), previousTransactions, "Deposit");
            else if (selection == 2)
                this.showTransactionHistory(client.getClientAccountID(), previousTransactions, "Withdrawal");
            else if (selection == 3)
                this.showTransactionHistory(client.getClientAccountID(), previousTransactions, "transferDeposit");
            else if (selection == 4)
                this.showTransactionHistory(client.getClientAccountID(), previousTransactions, "transferWithdrawal");
            else if (selection == 5)
                this.showTransactionHistory(client.getClientAccountID(), previousTransactions, "All");
            else {
                System.out.println("Error: Invalid selection.");
                continue;
            }

            do {
                System.out.print("Do you wish to perform another operation in \"Display Transaction History\"? (y/n): ");
                char answer = input.next().charAt(0);
                if (answer == 'y' || answer == 'Y') {
                    break;
                } else if (answer == 'n' || answer == 'N') {
                    performAnotherOperation = false;
                    break;
                } else
                    System.out.println("Invalid input: Enter (y/n) only!");
            } while (true);

        } while (performAnotherOperation);
    }

    public void showTransactionHistory(int ID, ArrayList<PreviousTransaction> previousTransactions, String transactionType) {
        boolean found = false;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");

        for (PreviousTransaction previousTransaction : previousTransactions) {
            if (transactionType.equals("All")) {
                if (ID == previousTransaction.clientID) {
                    found = true;
                    System.out.println("\nTransaction Date&Time: " + previousTransaction.dateAndTime.format(format) +
                                       "\nBalance before transaction: " + previousTransaction.beforeTransaction +
                                       "\nBalance after transaction: " + previousTransaction.afterTransaction +
                                       "\nTransaction type: " + previousTransaction.transactionType);
                }
            } else {
                if (ID == previousTransaction.clientID && transactionType.equals(previousTransaction.transactionType)) {
                    found = true;
                    System.out.println("\nTransaction Date&Time: " + previousTransaction.dateAndTime.format(format) +
                                       "\nBalance before transaction: " + previousTransaction.beforeTransaction +
                                       "\nBalance after transaction: " + previousTransaction.afterTransaction);
                }
            }
        }
        if (!found)
            System.out.println("Client's transaction history is empty.");
    }
}