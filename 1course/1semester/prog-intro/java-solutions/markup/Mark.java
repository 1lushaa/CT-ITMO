package markup;

public interface Mark {
    void toMarkdown(StringBuilder s);
    void toBBCode(StringBuilder s);
}
