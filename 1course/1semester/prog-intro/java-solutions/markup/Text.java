package markup;
public class Text implements Mark {
    private StringBuilder str;

    public Text(String newStr) { str = new StringBuilder(newStr); }

    public void toMarkdown(StringBuilder s) { s.append(str); }

    public void toBBCode(StringBuilder s) { s.append(str); }
}
