import java.io.IOException;
public class ReverseMinRAbc {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            try {
                while (in.hasNextLine()) {
                    String prev = "zzzzzzzzzz";
                    while (in.hasNext()) {
                        String str = in.next();
                        if (str.charAt(0) == '-' && prev.charAt(0) == '-') {
                            if (str.length() == prev.length()) {
                                boolean ifPrinted = false;
                                for (int i = 1; i < str.length(); i++) {
                                    if (str.charAt(i) > prev.charAt(i)) {
                                        System.out.print(str + " ");
                                        prev = str;
                                        ifPrinted = true;
                                        break;
                                    }
                                    if (str.charAt(i) < prev.charAt(i)) {
                                        System.out.print(prev + " ");
                                        ifPrinted = true;
                                        break;
                                    }
                                }
                                if (!ifPrinted) {
                                    System.out.print(prev + " ");
                                }
                            } else {
                                if (str.length() > prev.length()) {
                                    System.out.print(str + " ");
                                    prev = str;
                                } else {
                                    System.out.print(prev + " ");
                                }
                            }
                        } else if (str.charAt(0) != '-' && prev.charAt(0) != '-') {
                            if (str.length() == prev.length()) {
                                boolean ifPrinted = false;
                                for (int i = 0; i < str.length(); i++) {
                                    if (str.charAt(i) < prev.charAt(i)) {
                                        System.out.print(str + " ");
                                        prev = str;
                                        ifPrinted = true;
                                        break;
                                    }
                                    if (str.charAt(i) > prev.charAt(i)) {
                                        System.out.print(prev + " ");
                                        ifPrinted = true;
                                        break;
                                    }
                                }
                                if (!ifPrinted) {
                                    System.out.print(prev + " ");
                                }
                            } else {
                                if (str.length() < prev.length()) {
                                    System.out.print(str + " ");
                                    prev = str;
                                } else {
                                    System.out.print(prev + " ");
                                }
                            }
                        } else if (str.charAt(0) == '-' && prev.charAt(0) != '-') {
                            System.out.print(str + " ");
                            prev = str;
                        } else {
                            System.out.print(prev + " ");
                        }
                    }
                    System.out.println();
                    in.nextLine();
                }
            } catch (IOException e) {
                System.err.print("IOException: " + e.getMessage());
            } finally {
                in.close();
            }
        } catch(IOException e) {
            System.err.print("IOException: " + e.getMessage());
        }
    }
}


