package md2html;
public class Text implements Mark {
    private final StringBuilder str;

    public Text(String newStr) { str = new StringBuilder(newStr); }
    public Text(char newStr) { str = new StringBuilder(Character.toString(newStr)); }
    public void toHtml(StringBuilder s) { s.append(str); }
}
