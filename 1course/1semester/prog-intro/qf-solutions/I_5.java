import java.util.Scanner;
import java.util.ArrayList;
public class IdealPyramid {
    public final static int INF = 1000000000;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // slow scanner
        int n = in.nextInt();
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] coordinates = new int[3];
            coordinates[0] = in.nextInt();
            coordinates[1] = in.nextInt();
            coordinates[2] = in.nextInt();
            list.add(coordinates);
        }
        int x1 = INF;
        int x2 = -INF;
        int y1 = INF;
        int y2 = -INF;
        for (int i = 0; i < n; i++) {
            x1 = Math.min(x1, list.get(i)[0] - list.get(i)[2]);
            x2 = Math.max(x2, list.get(i)[0] + list.get(i)[2]);
            y1 = Math.min(y1, list.get(i)[1] - list.get(i)[2]);
            y2 = Math.max(y2, list.get(i)[1] + list.get(i)[2]);
        }
        int h = (int)Math.round(Math.ceil((double)Math.max(x2 - x1, y2 - y1) / 2));
        System.out.println((int)((x2 + x1) / 2) + " "  + (int)((y2 + y1) / 2) + " " + h);
    }
}
