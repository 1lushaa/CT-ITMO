import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class WsppPosition {
    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(new FileInputStream(args[0]));
            try {
                Map<String, ArrayList<IntList>> map = new LinkedHashMap<String, ArrayList<IntList>>();
                int strnum = 1;
                while (in.hasNextLine()) {
                    ArrayList<String> words = new ArrayList<String>();
                    while (in.hasNext()) {
                        words.add(in.next().toLowerCase());
                    }
                    for (int i = 0; i < words.size(); i++) {
                        int[] number = {1};
                        IntList cnt = new IntList(number);
                        int[] num = {strnum, words.size() - i};
                        IntList nums = new IntList(num);
                        ArrayList<IntList> toput = new ArrayList<>();
                        toput.add(cnt);
                        toput.add(nums);
                        if (map.putIfAbsent(words.get(i), toput) != null) {
                            ArrayList<IntList> sz = map.get(words.get(i));
                            sz.get(0).set(0, sz.get(0).get(0) + 1);
                            int[] pair = {strnum, words.size() - i};
                            IntList ints = new IntList(pair);
                            sz.add(ints);
                            map.put(words.get(i), sz);
                        }
                    }
                    in.nextLine();
                    strnum++;
                }
                try {
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                    try {
                        for(Map.Entry<String, ArrayList<IntList>> pair : map.entrySet()) {
                            out.write(pair.getKey());
                            for (int i = 0; i < pair.getValue().size(); i++) {
                                IntList toWrite = pair.getValue().get(i);
                                if (i == 0) {
                                    out.write(" " + toWrite.get(0));
                                } else {
                                    out.write(" " + toWrite.get(0) + ":" + toWrite.get(1));
                                }
                            }
                            out.write("\n");
                        }
                    } catch(IOException e) {
                        System.err.println("IOException: " + e.getMessage());
                    } finally {
                        try{
                            out.close();
                        } catch(IOException e) {
                            System.err.println("IOException: " + e.getMessage());
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("FileNotFoundException: " + e.getMessage());
                }
            } catch(IOException e) {
                System.out.print("IOException: " + e.getMessage());
            } finally {
                in.close();
            }
        } catch(IOException e) {
            System.err.print("FileNotFoundException: " + e.getMessage());
        }
    }
}
