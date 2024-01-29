package md2html;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static int index;
    public static StringBuilder markdown;
    public static int headerCheck() {
        int headerLevel = 0;
        index = 0;
        while (markdown.charAt(index) == '#') {
            headerLevel++;
            index++;
        }
        if (headerLevel != 0 && Character.isWhitespace(markdown.charAt(index)) && headerLevel <= 6 ) {
            index++;
            return headerLevel;
        }
        index = 0;
        return 0;
    }
    public static void delSeparator(int cnt) {
        if (markdown.length() >= System.lineSeparator().length() * cnt) {
            markdown.delete(markdown.length() - System.lineSeparator().length() * cnt, markdown.length());
        }
    }
    public static boolean textCompare(Mark mark1, Mark mark2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        mark1.toHtml(s1);
        mark2.toHtml(s2);
        return s1.toString().contentEquals(s2);
    }
    public static Allocation findEnding(String str) {
        index += str.length();
        List<Mark> allocation = new ArrayList<>();
        allocation.add(new Text(str));
        while (index + str.length() - 1 < markdown.length()) {
            if (markdown.substring(index, index + str.length()).equals(str)
                    && markdown.charAt(index - 1) != '\\') {
                if (str.length() == 1 && index + 1 < markdown.length() &&
                        !(Character.toString(markdown.charAt(index + 1)).equals(str)) || str.length() > 1) {
                    allocation.remove(0);
                    Allocation newAllocation = new Allocation(allocation, str, str);
                    if (str.length() > 1) {
                        ++index;
                    }
                    return newAllocation;
                }
            }
            check(allocation);
            index++;
        }
        return new Allocation(allocation, "", "");
    }
    public static void check(List<Mark> allocation) {
        boolean lenCheck = index <= 0 || markdown.charAt(index - 1) != '\\';
        if (index + 1 < markdown.length() && markdown.substring(index, index + 2).equals("''") && lenCheck) {
            allocation.add(findEnding("''"));
        } else if (index + 1 < markdown.length() && (markdown.substring(index, index + 2).equals("**")
        || markdown.substring(index, index + 2).equals("__")) && lenCheck) {
            allocation.add(findEnding(markdown.substring(index, index + 2)));
        } else if ((markdown.charAt(index) == '*' || markdown.charAt(index) == '_') && lenCheck) {
            allocation.add(findEnding(markdown.substring(index, index + 1)));
        } else if ((markdown.charAt(index) == '`') && lenCheck) {
            allocation.add(findEnding("`"));
        } else if (index + 1 < markdown.length() && markdown.substring(index, index + 2).equals("--") && lenCheck) {
            allocation.add(findEnding("--"));
        } else {
            Text text = new Text(markdown.charAt(index));
            if (textCompare(text, new Text('<'))) {
                allocation.add(new Text("&lt;"));
            } else if (textCompare(text, new Text('>'))) {
                allocation.add(new Text("&gt;"));
            }else if (textCompare(text, new Text('&'))) {
                allocation.add(new Text("&amp;"));
            } else if (!textCompare(text, new Text('\\'))) {
                allocation.add(text);
            }
        }
    }

    public static void main(String[] args) {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])))) {
            while (in.ready()) {
                markdown = new StringBuilder();
                boolean ifEnd = true;
                do {
                    String currStr = in.readLine();
                    markdown.append(currStr).append(System.lineSeparator());
                    if (currStr.isEmpty()) {
                        delSeparator(2);
                        ifEnd = false;
                        break;
                    }
                } while (in.ready());
                if (ifEnd) {
                    delSeparator(1);
                }
                if (markdown.length() == System.lineSeparator().length() &&
                markdown.substring(0, System.lineSeparator().length()).equals(System.lineSeparator())) {
                    continue;
                } else {
                    StringBuilder html = new StringBuilder();
                    int headerLevel = headerCheck();
                    List<Mark> allocation = new ArrayList<>();
                    while (index < markdown.length()) {
                        check(allocation);
                        index++;
                    }
                    Allocation alloc = headerLevel != 0 ?
                            new Allocation(allocation, headerLevel) : new Allocation(allocation);
                    alloc.toHtml(html);
                    try {
                        out.write(html + System.lineSeparator());
                    } catch(IOException e) {
                        System.out.println("IOException: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}