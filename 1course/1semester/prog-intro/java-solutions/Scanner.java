import java.io.*;
import java.util.*;
public class Scanner implements AutoCloseable{

    private static final int SIZE = 128;
    private char[] buffer;
    private Reader in;
    private int bufferSize;
    private int bufferPosition;
    private int intPosition;
    private int wordPosition;
    private boolean ifNext;
    private boolean ifNextInt;
    private StringBuilder str;
    private boolean ifHasNextLine;
    private IntList ints;
    private ArrayList<String> words;

    private void intsUpdate() {
        intPosition = 0;
        ints = new IntList();
        ifNextInt = false;
        for (int j = 0; j < str.length(); j++) {
            if (!Character.isWhitespace(str.charAt(j))) {
                int end = j;
                while ((end < str.length()) && !Character.isWhitespace(str.charAt(end))) {
                    end++;
                }
                if (str.substring(j, end).matches("-?\\d+(\\.\\d+)?")) {
                    ints.add(Integer.parseInt(str.substring(j, end)));
                }
                j = end;
            }
        }
        if (ints.size() > 0) {
            ifNextInt = true;
        }
    }

    private void wordsUpdate() {
        wordPosition = 0;
        words.clear();
        ifNext = false;
        for (int j = 0; j < str.length(); j++) {
            if (Character.isLetter(str.charAt(j)) || str.charAt(j) == '\''
                    || Character.DASH_PUNCTUATION == Character.getType(str.charAt(j))) {
                int end = j;
                while ((end < str.length()) && (Character.isLetter(str.charAt(end))
                        || str.charAt(end) == '\'' || Character.DASH_PUNCTUATION == Character.getType(str.charAt(end)))) {
                    end++;
                }
                words.add(str.substring(j, end));
                j = end;
            }
        }
        if (words.size() > 0) {
            ifNext = true;
        }
    }

    private void bufferUpdate() throws IOException{
        bufferSize = 0;
        bufferPosition = 0;
        ifHasNextLine = true;
        int num = in.read(buffer, 0, SIZE);
        if (num >= 0) {
            bufferSize = num;
        } else {
            ifHasNextLine = false;
            in.close();
        }
    }

    private void findInBuffer() throws IOException{
        while (bufferPosition < bufferSize) {
            if (System.lineSeparator().charAt(0) == buffer[bufferPosition]) {
                int id = 0;
                StringBuilder sep = new StringBuilder("");
                boolean ifSep = false;
                while (System.lineSeparator().charAt(id) == buffer[bufferPosition]) {
                    sep.append(buffer[bufferPosition]);
                    bufferPosition++;
                    id++;
                    if (id == System.lineSeparator().length()) {
                        ifSep = true;
                        break;
                    }
                    if (bufferPosition == SIZE) {
                        bufferUpdate();
                    }
                }
                if (ifSep) {
                    break;
                } else {
                    str.append(sep);
                }
            }
            str.append(buffer[bufferPosition]);
            bufferPosition++;
            if (bufferPosition == SIZE) {
                bufferUpdate();
            }
        }
    }

    public Scanner(InputStream inputstream) throws IOException{
        in = new InputStreamReader(inputstream);
        str = new StringBuilder("");
        buffer = new char[SIZE];
        ints = new IntList();
        words = new ArrayList<>();
        bufferUpdate();
        findInBuffer();
        wordsUpdate();
        intsUpdate();
    }

    public Scanner(String input) throws IOException{
        in = new StringReader(input);
        str = new StringBuilder("");
        buffer = new char[SIZE];
        ints = new IntList();
        words = new ArrayList<>();
        bufferUpdate();
        findInBuffer();
        wordsUpdate();
        intsUpdate();
    }

    public boolean hasNextLine() {
        return ifHasNextLine;
    }

    public boolean hasNextInt() {
        return ifNextInt;
    }

    public boolean hasNext() {
        return ifNext;
    }

    public String nextLine() throws IOException{
        String toReturn = str.toString();
        str.setLength(0);
        if (bufferPosition == bufferSize) {
            bufferUpdate();
        }
        findInBuffer();
        wordsUpdate();
        intsUpdate();
        return toReturn;
    }

    public int nextInt() {
        intPosition++;
        if (intPosition >= ints.size()) {
            ifNextInt = false;
        }
        return ints.get(intPosition - 1);
    }

    public String next() {
        wordPosition++;
        if (wordPosition >= words.size()) {
            ifNext = false;
        }
        return words.get(wordPosition - 1);
    }

    public void close() throws IOException{
        in.close();
    }
}