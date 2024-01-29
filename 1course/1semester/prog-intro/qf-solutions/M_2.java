import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ManagingDifficulties {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int f = 0; f < t; f++) {
            int n = in.nextInt();
            int arr[] = new int[n];
            Map<Integer, Integer> map  = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            int ans = 0;
            for (int j = n - 1; j >= 0; j--) {
                for (int i = 0; i < j; i++) {
                    if (map.containsKey(2 * arr[j] - arr[i])) {
                        ans += map.get(2 * arr[j] - arr[i]);
                    }
                }
                if (map.putIfAbsent(arr[j], 1) != null) {
                    map.put(arr[j], map.get(arr[j]) + 1);
                }
            }
            System.out.println(ans);
        }
    }
}
