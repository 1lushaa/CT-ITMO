import java.util.Scanner;
import java.lang.Math;

public class AccurateMovement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        System.out.println(2 * (int)Math.round(Math.ceil((double)(n - b) / (double)(b - a))) + 1);
    }
}
