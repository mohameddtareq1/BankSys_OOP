package banksystem.account;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientSavings {
    private float interestRate;

    public ClientSavings(float balance) {
        if (balance >= 3000)
            interestRate = 0.01F;
        else
            interestRate = -0.01F;
    }

    public void displayInterestRate() {
        System.out.println("\nInterest rate: " + interestRate * 100 + "%");
    }

    public void savingsAccountDetails(float balance) {

        boolean performAnotherOperation = true;
        do {
            System.out.print("\n1.Display interest rate" +
                             "\n2.Evaluate balance" +
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
                displayInterestRate();
            else if (selection == 2) {
                System.out.print("Enter the year: ");
                int years = input.nextInt() - 2023;
                for (int i = 0; i < years; i++)
                    balance += balance * interestRate * years;
                System.out.println("Balance after " + years + " years: " + balance);
            } else {
                System.out.println("Invalid selection!");
                continue;
            }

            do {
                System.out.print("\nDo you wish to perform another operation in \"Savings Account Details\"? (y/n): ");
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
}