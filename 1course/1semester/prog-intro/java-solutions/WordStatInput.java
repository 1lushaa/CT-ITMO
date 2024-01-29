import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;
public class WordStatInput {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        try{
            Scanner in = new Scanner(new FileInputStream(args[0]));
            while (in.hasNextLine()) {
                while (in.hasNext()) {
                    String curr = in.next().toLowerCase();
                    if (!map.containsKey(curr)) {
                        map.put(curr, 1);
                    } else {
                        map.replace(curr, map.get(curr) + 1);
                    }
                }
                in.nextLine();
            }
            try {
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                try {
                    for (Map.Entry<String, Integer> pair : map.entrySet()) {
                        out.write(pair.getKey() + " " + pair.getValue() + "\n");
                    }
                } catch (IOException e) {
                    System.err.println("IO exception: " + e.getMessage());
                } finally {
                    try {
                        out.close();
                    } catch (IOException e) {
                        System.err.println("IO exception: " + e.getMessage());
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        } catch(IOException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
    }
}





