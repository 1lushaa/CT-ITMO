import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
public class Next {
    private static final Scanner in = new Scanner(System.in);
    private static int n, k;
    private static ArrayList<ArrayList<Integer>> parse() {
        String str = in.nextLine();
        Scanner in2 = new Scanner(str);
        n = in2.nextInt();
        k = in2.nextInt();
        int cnt = 0;
        ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
        while (cnt < k) {
            String curr = in.nextLine();
            Scanner in3 = new Scanner(curr);
            ArrayList<Integer> list = new ArrayList<>();
            while (in3.hasNextInt()) {
                list.add(in3.nextInt());
            }
            sets.add(list);
            cnt++;
        }
        return sets;
    }
 
    private static void getNext(ArrayList<ArrayList<Integer>>sets) {
        if (n == 0 && k == 0) {
            return;
        }
        ArrayList<Integer> prev = new ArrayList<>();
        boolean flag = false;
        int max = -1;
        for (int i = sets.size() - 1; i >= 0; i--) {
            if (max > sets.get(i).get(sets.get(i).size() - 1) && !prev.isEmpty()) {
                int m = getMin(prev, sets.get(i).get(sets.get(i).size() - 1));
                sets.get(i).add(m);
                delete(prev, m);
                max = updateMax(prev);
                break;
            }
            for (int j = sets.get(i).size() - 1; j >= 0; j--) {
                if (j != 0 && !prev.isEmpty() && max > sets.get(i).get(j)) {
                    int m = getMin(prev, sets.get(i).get(j));
                    int old = sets.get(i).get(j);
                    sets.get(i).set(j, m);
                    delete(prev, m);
                    prev.add(old);
                    max = updateMax(prev);
                    flag = true;
                    break;
                } else {
                    prev.add(sets.get(i).get(sets.get(i).size() - 1));
                    max = updateMax(prev);
                    sets.get(i).remove(sets.get(i).size() - 1);
                    if (sets.get(i).isEmpty()) {
                        sets.remove(sets.size() - 1);
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        Collections.sort(prev);
        for (int i = 0; i < prev.size(); i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(prev.get(i));
            sets.add(list);
        }
        System.out.println(n + " " + sets.size());
        for (int i = 0; i < sets.size(); i++) {
            for (int j = 0; j < sets.get(i).size(); j++) {
                System.out.print(sets.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
 
    private static int getMin(ArrayList<Integer> prev, int cmp) {
        int min = 1000000;
        for (int i = 0; i < prev.size(); i++) {
            if (prev.get(i) > cmp) {
                min = Math.min(min, prev.get(i));
            }
        }
        return min;
    }
 
    private static int updateMax(ArrayList<Integer> list) {
        int max = -1;
        for (int i = 0; i < list.size(); i++) {
            max = Math.max(max, list.get(i));
        }
        return max;
    }
 
    private static void delete(ArrayList<Integer> list, int num) {
        int id = 0;
        boolean f = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == num) {
                id = i;
                f = true;
                break;
            }
        }
        if (f) {
            list.remove(id);
        }
    }
 
    public static void main(String[] args) {
        while (in.hasNextLine()) {
            getNext(parse());
            if (n == 0 && k == 0) {
                return;
            }
            in.nextLine();
        }
    }
}
