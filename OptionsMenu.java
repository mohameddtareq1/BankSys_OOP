package banksystem.menu;

import banksystem.account.*;
import static banksystem.Main.employees;
import static banksystem.Main.previousTransactions;
import static banksystem.menu.LoginMenu.login;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionsMenu {
    public void optionsClient(Client client, ArrayList<Client> clients, ArrayList<PreviousTransaction> previousTransactions) {
        boolean performAnotherOperation = true;
        do {
            System.out.print("\n1.Transaction" +
                             "\n2.Transaction History" +
                             "\n3.Account Details" +
                             "\n4.Edit Personal Information");
            if (client.isSavingsAccount) {
                System.out.print("\n5.Savings Account Details" +
                                 "\n6.Logout" +
                                 "\nSelect: ");
            }
            else {
                System.out.print("\n5.Logout" +
                                 "\nSelect: ");
            }

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
                System.out.print("\n1.Deposit" +
                                 "\n2.Withdraw" +
                                 "\n3.Transfer" +
                                 "\nSelect: ");
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
                    client.depositTransaction(client, previousTransactions);
                else if (selection == 2)
                    client.withdrawalTransaction(client, previousTransactions);
                else if (selection == 3)
                    client.transferTransaction(client, clients, previousTransactions);
                else
                    System.out.println("\nError: Invalid selection!");
            } else if (selection == 2)
                previousTransactions.getFirst().selectTransactionHistory(client, previousTransactions);
            else if (selection == 3)
                client.displayAccountDetails();
            else if (selection == 4)
                client.editPersonalInfo(client);
            else if (client.isSavingsAccount && selection == 5) {
                ClientSavings temp = new ClientSavings(client.balance);
                temp.savingsAccountDetails(client.balance);
            }
            else if (client.isSavingsAccount && selection == 6) {
                login(clients, employees, previousTransactions);
                break;
            }
            else if (!client.isSavingsAccount && selection == 5) {
                login(clients, employees, previousTransactions);
                break;
            }
            else {
                System.out.println("\nError: Invalid selection!");
                continue;
            }

            do {
                System.out.print("\nDo you wish to perform another operation in \"Client Options Menu\"? (y/n): ");
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

    public void optionsEmployee(Employee employee, ArrayList<Client> clients) {
        boolean performAnotherOperation = true;
        do {
            System.out.print("\n1.Client Accounts Management" +
                             "\n2.Client Account Details" +
                             "\n3.Edit Personal Information" +
                             "\n4.Logout" +
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

            boolean invalidSelection = true;
            if (selection == 1) {
                do {
                    System.out.print("\n1.Create new client account" +
                                     "\n2.Edit client account" +
                                     "\n3.Delete client account" +
                                     "\nSelect: ");

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
                        invalidSelection = false;
                        employee.createClientAccount(clients);
                    } else if (selection == 2) {
                        invalidSelection = false;
                        employee.editClientAccount(employee.searchClientAccount(clients), clients);
                    } else if (selection == 3) {
                        invalidSelection = false;
                        employee.deleteClientAccount(clients, employee.searchClientAccount(clients));
                    } else
                        System.out.println("Error: Invalid selection!");
                } while (invalidSelection);
            } else if (selection == 2)
                employee.displayClientInformation(employee.searchClientAccount(clients));
            else if (selection == 3)
                employee.editPersonalInfo();
            else if (selection == 4) {
                login(clients, employees, previousTransactions);
                break;
            }
            else {
                System.out.println("Invalid selection!");
                continue;
            }

            do {
                System.out.print("\nDo you wish to perform another operation in \"Employee Options Menu\"? (y/n): ");
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

    public void optionsAdmin(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<PreviousTransaction> previousTransactions) {
        Admin admin = new Admin();

        boolean performAnotherOperation = true;
        do {
            System.out.print("\n1.Employee Accounts Management" +
                             "\n2.Display Transaction History" +
                             "\n3.Display Accounts" +
                             "\n4.Logout" +
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

            boolean invalidSelection = true;
            if (selection == 1) {
                do {
                    System.out.print("\n1.Create new employee account" +
                                     "\n2.Edit employee account" +
                                     "\n3.Delete employee account" +
                                     "\nSelect: ");
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
                        invalidSelection = false;
                        admin.registerEmployeeAccount(employees);
                    } else if (selection == 2) {
                        invalidSelection = false;
                        admin.editEmployeeAccount(admin.searchEmployeeAccount(employees), employees);
                    } else if (selection == 3) {
                        invalidSelection = false;
                        admin.deleteEmployeeAccount(admin.searchEmployeeAccount(employees), employees);
                    } else
                        System.out.println("Error: Invalid selection!");
                } while (invalidSelection);
            } else if (selection == 2) {
                admin.displayTransactions(clients, previousTransactions);
            } else if (selection == 3)
                admin.displayAccounts(clients, employees);
            else if (selection == 4) {
                login(clients, employees, previousTransactions);
                break;
            }
            else {
                System.out.println("Error: Invalid selection!");
                continue;
            }

            do {
                System.out.print("\nDo you wish to perform another operation in \"Admin Options Menu\"? (y/n): ");
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