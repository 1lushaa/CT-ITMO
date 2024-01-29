import java.io.IOException;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int[][] arr = new int[100][];
            int ind = 0;
            while (in.hasNextLine()) {
                if (ind == arr.length) {
                    arr = Arrays.copyOf(arr, arr.length * 10);
                }
                int[] subarr = new int[100];
                int curr = 0;
                while (in.hasNextInt()) {
                    if (curr == subarr.length) {
                        subarr = Arrays.copyOf(subarr, subarr.length * 10);
                    }
                    subarr[curr] = in.nextInt();
                    curr++;
                }
                arr[ind] = Arrays.copyOfRange(subarr, 0, curr);
                ind++;
                in.nextLine();
            }
            for (int i = ind - 1; i >= 0; i--) {
                for (int j = arr[i].length - 1; j >= 0; j--) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.print("IOException: " + e.getMessage());
        }
    }
}