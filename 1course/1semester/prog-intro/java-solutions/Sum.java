public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            args[i] += " ";
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    int end = j;
                    while (!Character.isWhitespace(args[i].charAt(end)) && (end < args[i].length())) {
                        end++;
                    }
                    sum += Integer.parseInt(args[i].substring(j, end));
                    j = end;
                }
            }
        }
        System.out.println(sum);
    }
};