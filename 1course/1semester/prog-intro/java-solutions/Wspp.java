import java.util.LinkedHashMap;
import java.util.Map;
import java.io.*;
public class Wspp {
    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(new FileInputStream(args[0]));
            int num = 1;
            Map<String, IntList> map = new LinkedHashMap<String, IntList>();
            try {
                while (in.hasNextLine()) {
                    while (in.hasNext()) {
                        String curr = in.next().toLowerCase();
                        int[] arr = {1, num};
                        if (map.putIfAbsent(curr, new IntList(arr)) != null) {
                            IntList list = map.get(curr);
                            list.set(0, list.get(0) + 1);
                            list.add(num);
                            map.put(curr, list);
                        }
                        num++;
                    }
                    in.nextLine();
                }
            } catch (IOException e) {
                System.err.print("IOException: " + e.getMessage());
            } finally {
                in.close();
            }
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                try {
                    for (Map.Entry<String, IntList> pair : map.entrySet()) {
                        out.write(pair.getKey());
                        IntList arr = pair.getValue();
                        for (int i = 0; i < arr.size(); i++) {
                            out.write(" " + arr.get(i));
                        }
                        out.write("\n");
                    }
                } catch (IOException e) {
                    System.err.println("IOException: " + e.getMessage());
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        System.err.println("IOException: " + e.getMessage());
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("FileNotFoundException: " + e.getMessage());
            }
        } catch(IOException e) {
            System.err.print("FileNotFoundException: " + e.getMessage());
        }
    }
}








