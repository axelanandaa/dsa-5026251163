package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();

        File dataFile = locateTransactionFile();
        if (dataFile == null) {
            System.out.println("Error: transactions.txt not found.");
            System.out.println("Place transactions.txt in the project root, or in src/lw02/prelab/, "
                    + "or in the same folder you run the program from.");
            return;
        }

        try (Scanner fileScanner = new Scanner(dataFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                transactions.add(parts); // parts = { name, type, amount }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: transactions.txt not found.");
            return;
        }

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] transaction : transactions) {
            String name = transaction[0];
            if (findCustomer(customers, name) == null) {
                customers.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        Stack<String[]> failedWithdrawals = new Stack<>();

        String[] transaction;
        while ((transaction = queue.poll()) != null) {
            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = findCustomer(customers, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedWithdrawals.push(transaction);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedWithdrawals.isEmpty()) {
            String[] failed = failedWithdrawals.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }

    private static File locateTransactionFile() {
        String[] candidates = {
                "transactions.txt",
                "src/lw02/prelab/transactions.txt",
                "lw02/prelab/transactions.txt",
                "../transactions.txt",
                "../../transactions.txt"
        };
        for (String path : candidates) {
            File file = new File(path);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    private static String[] findCustomer(LinkedList<String[]> customers, String name) {
        for (String[] customer : customers) {
            if (customer[0].equals(name)) {
                return customer;
            }
        }
        return null;
    }
}