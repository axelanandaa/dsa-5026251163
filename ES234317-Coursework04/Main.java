/**
 * ES234317 - Algorithms and Data Structures
 * Gasal | 2026-2027
 * Coursework   : 04
 * Student ID   : 5026251163
 * Full Name    : Axelle Ananda Beta Perdana
 * Class        : C
 */

import java.util.*;

public class Main {

    // Soal 1
    public static Set<Integer> twice(List<Integer> list) {
        Set<Integer> once = new HashSet<Integer>();
        Set<Integer> twice = new HashSet<Integer>();
        Set<Integer> more = new HashSet<Integer>();

        for (int n : list) {
            if (more.contains(n)) {
                continue;
            } else if (twice.contains(n)) {
                twice.remove(n);
                more.add(n);
            } else if (once.contains(n)) {
                once.remove(n);
                twice.add(n);
            } else {
                once.add(n);
            }
        }
        return twice;
    }

    // Soal 2
    public static boolean hasDuplicateValue(Map<String, String> m) {
        Set<String> seen = new HashSet<String>();
        for (String value : m.values()) {
            if (!seen.add(value)) {
                return true;
            }
        }
        return false;
    }

    // Soal 3
    public static boolean isPalindrome(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<Integer>();
        int size = q.size();

        for (int i = 0; i < size; i++) {
            int n = q.remove();
            stack.push(n);
            q.add(n);
        }

        boolean result = true;
        for (int i = 0; i < size; i++) {
            int n = q.remove();
            int m = stack.pop();
            if (n != m) {
                result = false;
            }
            q.add(n);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> v = Arrays.asList(1, 3, 1, 4, 3, 7, -2, 0, 7, -2, -2, 1);
        System.out.println(twice(v));

        Map<String, String> m = new HashMap<String, String>();
        m.put("Marty", "Stepp");
        m.put("Stuart", "Reges");
        m.put("Jessica", "Miller");
        m.put("Amanda", "Camp");
        m.put("Meghan", "Miller");
        m.put("Hal", "Perkins");
        System.out.println(hasDuplicateValue(m));                       
        System.out.println(hasDuplicateValue(new HashMap<String, String>())); 

        Queue<Integer> q1 = new LinkedList<Integer>(Arrays.asList(3, 8, 17, 9, 17, 8, 3));
        System.out.println(isPalindrome(q1) + " " + q1);   

        Queue<Integer> q2 = new LinkedList<Integer>(Arrays.asList(3, 8, 17, 9, 4, 17, 8, 3));
        System.out.println(isPalindrome(q2) + " " + q2);   

        System.out.println(isPalindrome(new LinkedList<Integer>()));
    }
}
