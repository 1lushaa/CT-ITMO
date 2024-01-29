import java.io.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Collections;
public class WordStatCountPrefixL {
    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        try {
            Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            try {
                while (true) {
                    int ch = in.read();
                    if (ch == -1) {
                        break;
                    }
                    if (Character.isLetter(ch) || ch == '\'' || Character.DASH_PUNCTUATION == Character.getType(ch)) {
                        StringBuilder s = new StringBuilder("");
                        int counter = 0;
                        while (Character.isLetter(ch) || ch == '\'' || Character.DASH_PUNCTUATION == Character.getType(ch)) {
                            if (counter < 3) {
                                s.append((char)ch);
                                counter++;
                            }
                            ch = in.read();
                        }
                        if (counter == 3) {
                            String str = s.toString().toLowerCase();
                            if (!map.containsKey(str)) {
                                map.put(str, 1);
                            } else {
                                map.replace(str, map.get(str) + 1);
                            }
                        }
                    }
                }
                List<Integer> list = new ArrayList<Integer>();
                Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
                for (Map.Entry<String, Integer> pair : map.entrySet()) {
                    list.add(pair.getValue());
                }
                Collections.sort(list); 
                for (int second : list) {
                    for (Map.Entry<String, Integer> pair : map.entrySet()) {
                        if ((pair.getValue() == second) && !sorted.containsKey(pair.getKey())) {
                            sorted.put(pair.getKey(), second);
                        }
                    }
                }
                try {
                    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                    try {
                        for (Map.Entry<String, Integer> pair : sorted.entrySet()) {
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
            } catch (IOException e) {
                System.err.println("IO exception: " + e.getMessage());
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    System.err.println("IO exception: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}





