import java.util.Scanner;

public class JusttheLastDigit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] num = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = in.next();
            int[] arr = new int[n];
            for (int k = 0; k < n; k++) {
                arr[k] = Integer.parseInt(Character.toString(str.charAt(k)));
            }
            num[i] = arr;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (num[i][j] != 0) {
                    for (int k = j + 1; k < n; k++) {
                        num[i][k] = (num[i][k] - num[j][k] + 10) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    System.out.print(0);
                } else {
                    if (num[i][j] > 0) {
                        System.out.print(1);
                    } else {
                        System.out.print(0);
                    }
                }
            }
            System.out.println();
        }
    }
}
