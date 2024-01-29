import java.util.Arrays;
import java.io.IOException;
public class ReverseMinR {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            int[][] arr = new int[500][];
            String s;
            int ind = 0;
            while (in.hasNextLine()) {
                if (ind == arr.length) {
                    int[][] arr2 = Arrays.copyOf(arr, arr.length * 2);
                    arr = arr2;
                }
                s = in.nextLine();
                Scanner in2 = new Scanner(s);
                int[] subarr = new int[500];
                int curr = 0;
                while (in2.hasNextInt()) {
                    if (curr == subarr.length) {
                        int[] subarr2 = Arrays.copyOf(subarr, subarr.length * 2);
                        subarr = subarr2;
                    }
                    subarr[curr] = (curr == 0) ? in2.nextInt() : Math.min(in2.nextInt(), subarr[curr - 1]);
                    curr++;
                }

                arr[ind] = Arrays.copyOfRange(subarr, 0, curr);
                ind++;
            }

            for (int i = 0; i < ind; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.print("IOException: " + e.getMessage());
        }
    }
}
