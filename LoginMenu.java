package banksystem.menu;

import banksystem.account.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenu {
    public static void login(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<PreviousTransaction> previousTransactions) {
        String username;
        String password;
        boolean loginSuccessful = false;
        OptionsMenu optionsMenu = new OptionsMenu();

        Scanner input = new Scanner(System.in);

        System.out.println("\n\t  =============\n\t  *** LOGIN ***" +
                           "\n\t  =============\nTYPE 'EXIT' TO CLOSE & SAVE" +
                           "\n---------------------------\n");
        do {
            System.out.print("Username: ");
            username = input.nextLine();
            if (username.equals("exit") || username.equals("EXIT") || username.equals("Exit"))
                break;
            System.out.print("Password: ");
            password = input.nextLine();

            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("\n\t---LOGGED IN AS ADMIN---\n\t-----2FA INITIATION-----\n");
                loginSuccessful = loginAdminVerification(optionsMenu, clients, employees, previousTransactions);
            } else {
                for (Client client : clients) {
                    if (username.equals(client.getUsername()) && password.equals(client.getPassword())) {
                        loginSuccessful = true;
                        System.out.println("\n\t---LOGGED IN AS CLIENT---");
                        optionsMenu.optionsClient(client, clients, previousTransactions);
                        break;
                    }
                }
                for (Employee employee : employees) {
                    if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
                        loginSuccessful = true;
                        System.out.println("\n\t---LOGGED IN AS EMPLOYEE---");
                        optionsMenu.optionsEmployee(employee, clients);
                        break;
                    }
                }
            }
            if (!loginSuccessful)
                System.out.println("\nLogin Failed: Incorrect username or password!\n\n" +
                                   "\t--------------------------\n" +
                                   "\tRESTARTING LOGIN OPERATION\n" +
                                   "\t--------------------------\n");
        } while (!loginSuccessful);
    }


    public static boolean loginAdminVerification(OptionsMenu optionsMenu, ArrayList<Client> clients, ArrayList<Employee> employees,
                                       ArrayList<PreviousTransaction> previousTransactions) {

        Scanner input = new Scanner(System.in);
        boolean verified = false;

        System.out.print("Employee username: ");
        String username = input.nextLine();
        System.out.print("Employee password: ");
        String password = input.nextLine();

        for (Employee employee : employees) {
            if (username.equals(employee.getUsername()) && password.equals(employee.getPassword()) && employee.getAdminAccess()) {
                verified = true;
                break;
            }
        }
        if (verified) {
            System.out.println("\n\t-----2FA SUCCESSFUL-----");
            optionsMenu.optionsAdmin(clients, employees, previousTransactions);
            return true;
        } else {
            System.out.println("\n\t------2FA FAILED------");
            return false;
        }
    }
}