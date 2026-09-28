package lw02.unguided;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static final int MAX_BORROW = 2;

    public static void main(String[] args) throws Exception {

        LinkedList<String[]> requests = new LinkedList<>();

        Scanner sc = new Scanner(new File("src/lw02/unguided/borrowing.txt"));
        while (sc.hasNext()) {
            String name = sc.next();
            String title = sc.next();
            requests.add(new String[]{name, title});
        }
        sc.close();

        LinkedList<String[]> books = new LinkedList<>();
        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        LinkedList<String[]> members = new LinkedList<>();
        for (int i = 0; i < requests.size(); i++) {
            String name = requests.get(i)[0];

            boolean exists = false;
            for (int j = 0; j < members.size(); j++) {
                if (members.get(j)[0].equals(name)) {
                    exists = true;
                }
            }
            if (!exists) {
                members.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>();
        for (int i = 0; i < requests.size(); i++) {
            queue.add(requests.get(i));
        }

        LinkedList<String[]> successList = new LinkedList<>();
        Stack<String[]> failedList = new Stack<>();
        
    }
}