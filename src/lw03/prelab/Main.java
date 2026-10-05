package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    static void problem1() {
        List<String> playlist = new ArrayList<>();

        try (Scanner sc = new Scanner(new File("src/lw03/prelab/playlist.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] cmd = line.split(" ", 2);
                String type = cmd[0];

                if (type.equals("ADD") && cmd.length == 2) {
                    playlist.add(cmd[1]);
                } else if (type.equals("INSERT") && cmd.length == 2) {
                    String[] p = cmd[1].split(" ", 2); // <INDEX> <SONG>
                    if (p.length == 2) {
                        int idx = Integer.parseInt(p[0]);
                        if (idx >= 0 && idx <= playlist.size()) {
                            playlist.add(idx, p[1]);
                        }
                    }
                } else if (type.equals("REMOVE") && cmd.length == 2) {
                    playlist.remove(cmd[1]); // hanya kemunculan pertama
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File playlist.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;

        try (Scanner sc = new Scanner(new File("src/lw03/prelab/participants.txt"))) {
            while (sc.hasNextLine()) {
                String name = sc.nextLine().trim();
                if (name.isEmpty()) continue;

                if (!participants.add(name)) { // add() false jika sudah ada
                    duplicates++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File participants.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no++ + ". " + name);
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    static void problem3() throws FileNotFoundException {
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;

        try (Scanner sc = new Scanner(new File("src/lw03/prelab/inventory.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] p = line.split("\\s+"); // <TYPE> <PRODUCT> <QUANTITY>
                if (p.length != 3) continue;
                String type = p[0];
                String product = p[1];
                int qty = Integer.parseInt(p[2]);

                if (type.equals("ADD")) {
                    if (stock.containsKey(product)) {
                        stock.put(product, stock.get(product) + qty);
                    } else {
                        stock.put(product, qty);
                    }
                } else if (type.equals("SELL")) {
                    if (stock.containsKey(product) && stock.get(product) >= qty) {
                        stock.put(product, stock.get(product) - qty);
                    } else {
                        failedSales++;
                    }
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}