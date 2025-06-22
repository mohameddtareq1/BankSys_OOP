package banksystem.account;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Employee extends Account {
    protected int employeeAccountID;
    public String uniGraduated;
    public int yearOfGraduation;
    protected char[] grade = new char[2];
    protected char totalGradePart2;
    protected char position;
    private boolean adminAccess;

    public Employee() {
        super();
    }

    public Employee(String fName, String lName, long pNumber, String address,
                    String username, String password, int employeeAccountID,
                    String uniGraduated, int yearOfGraduation, String grade, char position, boolean adminAccess) {

        super(fName, lName, pNumber, address, username, password);
        this.employeeAccountID = employeeAccountID;
        this.uniGraduated = uniGraduated;
        this.yearOfGraduation = yearOfGraduation;
        if (grade.charAt(0) != '+' && grade.charAt(0) != '-') {
            this.grade[0] = grade.charAt(0);
            this.grade[1] = '';
        } else {
            this.grade[0] = grade.charAt(0);
            this.grade[1] = grade.charAt(1);
        }
        this.position = position;
        this.adminAccess = adminAccess;
    }


    public int getEmployeeAccountID() {
        return employeeAccountID;
    }

    public void setEmployeeAccountID(int employeeAccountID) {
        this.employeeAccountID = employeeAccountID;
    }

    public boolean getAdminAccess() {
        return adminAccess;
    }

    public void setAdminAccess(boolean adminAccess) {
        this.adminAccess = adminAccess;
    }


    public void editPersonalInfo() {
        boolean repeat = true;
        do {
            System.out.print("\nSelect attribute to modify:\n" +
                             "        1.Phone number\n" +
                             "        2.Address\n" +
                             "Selection:");
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt();

            switch (selection) {
                case 1: {
                    System.out.print("New phone number: ");
                    this.phoneNumber = input.nextLong();
                    System.out.println("Phone number edited successfully!");
                    break;
                }
                case 2: {
                    System.out.print("New address: ");
                    this.address = input.nextLine();
                    System.out.println("Address edited successfully!");
                    break;
                }
                default: {
                    System.out.println("Invalid selection!");
                    continue;
                }
            }

            System.out.println("Do you wish to modify another attribute? (y/n)");
            char answer = input.next().charAt(0);
            if (answer != 'y' && answer != 'Y')
                repeat = false;

        } while (repeat);
    }

    public void displayEmployeeInformation(Employee employee) {
        System.out.println("\nName: " + firstName + " " + lastName +
                           "\nPhone number: " + phoneNumber +
                           "\nAddress: " + address + "\n" +
                           "\nGraduated from " + uniGraduated + ", " + yearOfGraduation + " with " + grade[0] + grade[1] + " grade." +
                           "\nUsername: " + getUsername() +
                           "\nPassword: " + getPassword() +
                           "\nID: " + employeeAccountID +
                           "\nPosition: " + position +
                           "\nAdmin access: " + adminAccess + "\n");
    }

    public void createClientAccount(ArrayList<Client> clients) {
        System.out.println(" --- CLIENT ACCOUNT CREATION --- \n" +
                           "Input details of the new account:\n" +
                           "---------------------------------\n");
        Scanner input = new Scanner(System.in);

        System.out.print("First name: ");
        String firstName = input.nextLine();

        System.out.print("Last name: ");
        String lastName = input.nextLine();

        System.out.print("Phone number: ");
        long phoneNumber;
        do {
            try {
                phoneNumber = input.nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\nError: Invalid input!\n Enter a number: ");
                input.nextLine();
            }
        } while (true);

        System.out.print("Address: ");
        input.nextLine();
        String address = input.nextLine();

        boolean isFound;

        String username;
        do {
            isFound = false;

            System.out.print("Username: ");
            username = input.nextLine();

            for (Client client : clients) {
                if (username.equals("admin") || username.equals(client.getUsername())) {
                    isFound = true;
                    System.out.println("Error: Username already exists!");
                    break;
                }
            }
        } while (isFound);

        String password;
        do {
            isFound = false;

            System.out.print("Password: ");
            password = input.nextLine();

            if (password.equals("admin")) {
                isFound = true;
                System.out.println("Error: Forbidden password!");
            }
        } while (isFound);

        int ID;
        do {
            isFound = false;

            System.out.print("ID: ");
            do {
                try {
                    ID = input.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("\nError: Invalid input!\n Enter a number: ");
                    input.nextLine();
                }
            } while (true);
            for (Client client : clients) {
                if (ID == client.getClientAccountID()) {
                    isFound = true;
                    System.out.println("Error: ID already exists!");
                    break;
                }
            }
        } while (isFound);

        boolean isValid;
        char answer;
        boolean isSavingsAccount = false;
        do {
            isValid = false;

            System.out.print("Savings Account (Y/N): ");
            answer = input.next().charAt(0);

            if (answer == 'Y' || answer == 'y') {
                isSavingsAccount = true;
                isValid = true;
            } else if (answer == 'N' || answer == 'n')
                isValid = true;
            else
                System.out.println("Error: Invalid input!");
        } while (!isValid);

        clients.add(new Client(firstName, lastName, phoneNumber, address,
                username, password, ID, true, 0, isSavingsAccount));
    }

    public void editClientAccount(Client client, ArrayList<Client> clients) {
        boolean repeat = true;
        do {
            System.out.print("\nSelect attribute to modify:\n" +
                             "        1.Firstname\n" +
                             "        2.Lastname\n" +
                             "        3.Phone number\n" +
                             "        4.Address\n" +
                             "        5.Username\n" +
                             "        6.Password\n" +
                             "        7.Account State\n" +
                             "        8.Balance\n" +
                             "        9.Savings Account Status\n" +
                             "Selection:");
            Scanner input = new Scanner(System.in);
            int selection = input.nextInt();

            switch (selection) {
                case 1: {
                    System.out.print("New firstname: ");
                    client.firstName = input.nextLine();
                    System.out.println("Firstname modified successfully!");
                    break;
                }
                case 2: {
                    System.out.print("New lastname: ");
                    client.lastName = input.nextLine();
                    System.out.println("Lastname modified successfully!");
                    break;
                }
                case 3: {
                    System.out.print("New Phone number: ");
                    do {
                        try {
                            client.phoneNumber = input.nextLong();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("\nError: Invalid input!\n Enter a number: ");
                            input.nextLine();
                        }
                    } while (true);
                    input.nextLine();
                    System.out.println("Phone number modified successfully!");
                    break;
                }
                case 4: {
                    System.out.print("New address: ");
                    client.address = input.nextLine();
                    System.out.println("Address modified successfully!");
                    break;
                }
                case 5: {
                    boolean isFound;
                    String username;

                    do {
                        isFound = false;
                        System.out.print("New username: ");
                        username = input.nextLine();

                        for (Client tempClient : clients) {
                            if (username.equals("admin") || username.equals(tempClient.getUsername())) {
                                isFound = true;
                                System.out.println("Error: Username already exists!");
                                break;
                            }
                        }
                    } while (isFound);

                    client.username = username;
                    System.out.println("Username modified successfully!");
                    break;
                }
                case 6: {
                    boolean isFound;
                    String password;
                    do {
                        isFound = false;
                        System.out.print("New password: ");
                        password = input.nextLine();

                        if (password.equals("admin")) {
                            isFound = true;
                            System.out.println("Error: Forbidden password!");
                        }
                    } while (isFound);

                    client.setPassword(password);
                    System.out.println("Password modified successfully!");
                    break;
                }
                case 7: {
                    System.out.print("New account state (T/F): ");
                    char answer = input.next().charAt(0);

                    if (answer == 't' || answer == 'T')
                        client.setAccountState(true);
                    else if (answer == 'f' || answer == 'F')
                        client.setAccountState(false);

                    System.out.println("Account state modified successfully!");
                    break;
                }
                case 8: {
                    System.out.print("New balance: ");
                    do {
                        try {
                            client.balance = input.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("\nError: Invalid input!\n Enter a number: ");
                            input.nextLine();
                        }
                    } while (true);
                    System.out.println("Balance modified successfully!");
                    break;
                }
                case 9: {
                    boolean isValid;
                    char answer;
                    do {
                        isValid = false;

                        System.out.print("New 'Savings' status (Y/N): ");
                        answer = input.next().charAt(0);
                        if (answer == 'Y' || answer == 'y') {
                            client.isSavingsAccount = true;
                            isValid = true;
                        } else if (answer == 'N' || answer == 'n') {
                            client.isSavingsAccount = false;
                            isValid = true;
                        } else {
                            System.out.println("Error: Invalid input!");
                            continue;
                        }
                        System.out.println("'Savings Account' status modified successfully!");
                    } while (!isValid);
                    break;
                }
                default: {
                    System.out.println("Invalid selection!");
                    continue;
                }
            }

            System.out.println("Do you wish to modify another attribute? (y/n)");
            char answer = input.next().charAt(0);
            if (answer != 'y' && answer != 'Y')
                repeat = false;
            input.close();
        } while (repeat);
    }

    public void deleteClientAccount(ArrayList<Client> clients, Client client) {
        if (client == null)
            System.out.println("Account deletion failed: Account doesn't exist!");
        else {
            clients.remove(client);
            System.out.println("Account deletion successful!");
        }
    }

    public void displayClientInformation(Client client) {
        System.out.println("\nClient Information" +
                           "\n------------------");
        client.displayAccountDetails();
    }

    public Client searchClientAccount(ArrayList<Client> clients) {
        boolean invalid = true;
        do {
            System.out.print("\nSearch for the client by:\n" +
                             "        1.Username\n" +
                             "        2.ID\n" +
                             "Selection: ");
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
                invalid = false;

                System.out.print("Username: ");
                input.nextLine();
                String clientUsername = input.nextLine();

                for (Client client : clients) {
                    if (clientUsername.equals(client.getUsername()))
                        return client;
                }
            } else if (selection == 2) {
                invalid = false;

                System.out.print("ID: ");
                int clientID;
                do {
                    try {
                        clientID = input.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.print("\nError: Invalid input!\n Enter a number: ");
                        input.nextLine();
                    }
                } while (true);

                for (Client client : clients) {
                    if (clientID == client.getClientAccountID())
                        return client;
                }
            } else
                System.out.println("Error: Invalid selection!");
        } while (invalid);

        System.out.println("Account doesn't exist!");
        return null;
    }
}