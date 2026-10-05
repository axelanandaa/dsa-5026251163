package lw03.unguided;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> enrollment = new HashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> checkResults = new ArrayList<>();
        int rejected = 0;

        Scanner sc = new Scanner(new File("src/lw03/unguided/enrollment.txt"));

        while (sc.hasNext()) {
            String op = sc.next();
            String code = sc.next();

            if (op.equals("CHECK")) {
                if (enrollment.containsKey(code)) {
                    checkResults.add(code + ": " + enrollment.get(code) + " students");
                } else {
                    checkResults.add(code + ": Not found");
                }
            } else {
                int count = sc.nextInt();
 
                if (count <= 0) {
                    rejected++;
                } else if (op.equals("REGISTER")) {
                    if (enrollment.containsKey(code)) {
                        enrollment.put(code, enrollment.get(code) + count);
                    } else {
                        enrollment.put(code, count);
                        courseOrder.add(code);
                    }
                } else if (op.equals("WITHDRAW")) {
                    if (enrollment.containsKey(code) && enrollment.get(code) >= count) {
                        enrollment.put(code, enrollment.get(code) - count);
                    } else {
                        rejected++;
                    }
                }
            }

            
        }
    }
}