package banksystem.account;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends Employee {

    public Employee searchEmployeeAccount(ArrayList<Employee> employees) {
        boolean invalid = true;
        do {
            System.out.print("\nSearch for the employee by:" +
                             "\n        1.Username" +
                             "\n        2.ID" +
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
                invalid = false;

                System.out.print("Username: ");
                input.nextLine();
                String employeeUsername = input.nextLine();

                for (Employee employee : employees) {
                    if (employeeUsername.equals(employee.getUsername()))
                        return employee;
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

                for (Employee employee : employees) {
                    if (clientID == employee.getEmployeeAccountID())
                        return employee;
                }
            } else
                System.out.println("Error: Invalid selection!");
        } while (invalid);

        System.out.println("Account doesn't exist!");
        return null;
    }

    public void registerEmployeeAccount(ArrayList<Employee> employees) {
        System.out.println(" --- EMPLOYEE ACCOUNT CREATION --- \n" +
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
                System.out.print("\nError: Invalid input!\nEnter a number: ");
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

            for (Employee employee : employees) {
                if (username.equals("admin") || username.equals(employee.getUsername())) {
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
                    System.out.print("\nError: Invalid input!\nEnter a number: ");
                    input.nextLine();
                }
            } while (true);

            for (Employee employee : employees) {
                if (ID == employee.getEmployeeAccountID()) {
                    isFound = true;
                    System.out.println("Error: ID already exists!");
                    break;
                }
            }
        } while (isFound);

        System.out.print("University graduated: ");
        input.nextLine();
        String uniGraduated = input.nextLine();

        System.out.print("Graduation year: ");
        int graduationYear;
        do {
            try {
                graduationYear = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\nError: Invalid input!\nEnter a number: ");
                input.nextLine();
            }
        } while (true);

        System.out.print("Full Grade (+/ /-) (A/B/C/D): ");
        input.nextLine();
        String gradeString = input.nextLine();

        boolean validInput;
        char employeePosition = 'E';
        do {
            validInput = false;
            System.out.print("Employee position (M/E): ");
            char answer = input.next().charAt(0);

            if (answer == 'M' || answer == 'm') {
                employeePosition = 'M';
                validInput = true;
            } else if (answer == 'E' || answer == 'e')
                validInput = true;
            else
                System.out.println("Error: Invalid input! Enter (M/E) only!");
        } while (!validInput);

        boolean adminAccess = false;
        do {
            validInput = false;
            System.out.print("Admin access (y/n): ");
            char answer = input.next().charAt(0);

            if (answer == 'y' || answer == 'Y') {
                adminAccess = true;
                validInput = true;
            } else if (answer == 'n' || answer == 'N')
                validInput = true;
            else
                System.out.println("Error: Invalid input! Enter (y/n) only!");
        } while (!validInput);

        employees.add(new Employee(firstName, lastName, phoneNumber, address,
                username, password, ID, uniGraduated, graduationYear, gradeString, employeePosition, adminAccess));
    }

    public void editEmployeeAccount(Employee employee, ArrayList<Employee> employees) {
        boolean repeat = true;
        do {
            System.out.print("\nSelect attribute to modify:" +
                             "\n        1.Firstname" +
                             "\n        2.Lastname" +
                             "\n        3.Phone number" +
                             "\n        4.Address" +
                             "\n        5.Username" +
                             "\n        6.Password" +
                             "\n        7.ID" +
                             "\n        8.University Graduated" +
                             "\n        9.Graduation Year" +
                             "\n       10.Grade" +
                             "\n       11.Position" +
                             "\n       12.Admin permissions" +
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
            input.nextLine();

            switch (selection) {
                case 1: {
                    System.out.print("New firstname: ");
                    employee.firstName = input.nextLine();
                    System.out.println("Firstname modified successfully!");
                    break;
                }
                case 2: {
                    System.out.print("New lastname: ");
                    employee.lastName = input.nextLine();
                    System.out.println("Lastname modified successfully!");
                    break;
                }
                case 3: {
                    System.out.print("New phone number: ");
                    do {
                        try {
                            employee.phoneNumber = input.nextLong();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("\nError: Invalid input!\n Enter a number: ");
                            input.nextLine();
                        }
                    } while (true);
                    System.out.println("Phone number modified successfully!");
                    break;
                }
                case 4: {
                    System.out.print("New address: ");
                    employee.address = input.nextLine();
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

                        for (Employee tempEmployee : employees) {
                            if (username.equals("admin") || username.equals(tempEmployee.getUsername())) {
                                isFound = true;
                                System.out.println("Error: Username already exists!");
                                break;
                            }
                        }
                    } while (isFound);

                    employee.username = username;
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

                    employee.setPassword(password);
                    System.out.println("Password modified successfully!");
                    break;
                }
                case 7: {
                    boolean isFound;
                    int ID;
                    do {
                        isFound = false;

                        System.out.print("New employee ID: ");
                        do {
                            try {
                                ID = input.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.print("\nError: Invalid input!\n Enter a number: ");
                                input.nextLine();
                            }
                        } while (true);

                        for (Employee tempEmployee : employees) {
                            if (ID == tempEmployee.getEmployeeAccountID()) {
                                isFound = true;
                                System.out.println("Error: ID already exists!");
                                break;
                            }
                        }
                    } while (isFound);

                    employee.employeeAccountID = ID;
                    System.out.println("Employee ID modified successfully!");
                    break;
                }
                case 8: {
                    System.out.print("New 'University Graduated': ");
                    employee.uniGraduated = input.nextLine();
                    System.out.println("'University graduated' modified successfully!");
                    break;
                }
                case 9: {
                    System.out.print("New graduation year: ");
                    do {
                        try {
                            employee.yearOfGraduation = input.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("\nError: Invalid input!\n Enter a number: ");
                            input.nextLine();
                        }
                    } while (true);
                    System.out.println("'Graduation Year' modified successfully!");
                    break;
                }
                case 10: {
                    System.out.print("New grade: ");
                    input.nextLine();
                    String gradeString = input.nextLine();
                    employee.grade[0]  = gradeString.charAt(0);;
                    employee.grade[1] = gradeString.charAt(1);;
                    System.out.println("Grade modified successfully!");
                    break;
                }
                case 11: {
                    boolean isValid;
                    char position;
                    do {
                        isValid = false;

                        System.out.print("New position (M/E): ");
                        position = input.next().charAt(0);
                        if (position == 'M' || position == 'm') {
                            employee.position = 'M';
                            isValid = true;
                        } else if (position == 'E' || position == 'e') {
                            employee.position = 'E';
                            isValid = true;
                        } else {
                            System.out.println("Error: Invalid position!");
                            continue;
                        }
                        System.out.println("Position modified successfully");
                    } while (!isValid);
                    break;
                }
                case 12: {
                    if (employee.position != 'M' && !employee.getAdminAccess()) {
                        boolean isValid;
                        char position;
                        do {
                            isValid = false;

                            System.out.print("New administrator access (Y/N): ");
                            position = input.next().charAt(0);
                            if (position == 'Y' || position == 'y') {
                                employee.setAdminAccess(true);
                                isValid = true;
                            } else if (position == 'N' || position == 'n') {
                                employee.setAdminAccess(false);
                                isValid = true;
                            } else {
                                System.out.println("Error: Invalid input!");
                                continue;
                            }
                            System.out.println("Administrator permissions modified successfully!");
                        } while (!isValid);
                        break;
                    } else {
                        System.out.println("Error: Selected employee is a manager and has admin permissions!\n" +
                                           "'Admin permission' modification process aborted!");
                    }
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

    public void deleteEmployeeAccount(Employee employee, ArrayList<Employee> employees) {
        if (employee == null)
            System.out.println("Account deletion failed: Account doesn't exist!");
        else if (employee.getAdminAccess())
            System.out.println("Account deletion failed: Employee has administrator permissions!");
        else {
            employees.remove(employee);
            System.out.println("Account deletion successful!");
        }
    }


    public void displayAccounts(ArrayList<Client> clients, ArrayList<Employee> employees) {
        Scanner input = new Scanner(System.in);

        System.out.print("Select which account type to display (Client/Employee): ");
        String answer = input.nextLine();

        if (answer.equals("client") || answer.equals("Client") || answer.equals("CLIENT")) {
            for (Client client : clients)
                client.displayAccountIdentification();
        } else if (answer.equals("employee") || answer.equals("Employee") || answer.equals("EMPLOYEE")) {
            for (Employee employee : employees) {
                displayEmployeeInformation(employee);
            }
        } else
            System.out.println("Cannot display accounts. Error: Invalid account type!");
    }

    public void displayTransactions(ArrayList<Client> clients, ArrayList<PreviousTransaction> previousTransactions) {
        previousTransactions.getFirst().selectTransactionHistory(this.searchClientAccount(clients), previousTransactions);
    }

    @Override
    public void displayEmployeeInformation(Employee employee) {
        System.out.println("\nUsername: " + employee.getUsername() +
                           "\nPassword: " + employee.getPassword() +
                           "\nID: " + employee.employeeAccountID +
                           "\nPosition: " + employee.position +
                           "\nAdmin access: " + employee.getAdminAccess());
    }
}