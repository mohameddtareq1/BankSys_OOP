package banksystem;

import banksystem.account.*;
import static banksystem.menu.LoginMenu.login;

import java.io.*;
import java.util.ArrayList;

public class Main {
    static File ClientFile = new File("clients.data");
    static File EmpFile = new File("employees.data");
    static File TransactionFile = new File("transactions.data");

    public static ArrayList<Client> clients = new ArrayList<>();
    public static ArrayList<Employee> employees = new ArrayList<>();
    public static ArrayList<PreviousTransaction> previousTransactions = new ArrayList<>();

    public static void main(String[] args) {
        readFile();

        login(clients, employees, previousTransactions);

        writeFile();

        System.exit(0);
    }

    public static void readFile(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ClientFile))) {
            clients = (ArrayList<Client>) inputStream.readObject();
        } catch (Exception e) {
            System.out.println("Exception encountered in clients' input stream.");
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(EmpFile))) {
            employees = (ArrayList<Employee>) inputStream.readObject();
        } catch (Exception e) {
            System.out.println("Exception encountered in employees' input stream.");
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(TransactionFile))) {
            previousTransactions = (ArrayList<PreviousTransaction>) inputStream.readObject();
        } catch (Exception e) {
            System.out.println("Exception encountered in transactions' input stream.");
        }
    }

    public static void writeFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ClientFile))) {
            outputStream.writeObject(clients);
        } catch (Exception e) {
            System.out.println("Exception encountered in clients' output stream.");
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(EmpFile))) {
            outputStream.writeObject(employees);
        } catch (Exception e) {
            System.out.println("Exception encountered in employees' output stream.");
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(TransactionFile))) {
            outputStream.writeObject(previousTransactions);
        } catch (Exception e) {
            System.out.println("Exception encountered in transactions' output stream.");
        }
    }
}