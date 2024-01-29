package markup;
import java.util.List;
public class Paragraph implements Mark {
    private List<Mark> marks;

    public Paragraph(List<Mark> list) { marks = list; }

    public void toBBCode(StringBuilder s) {
        for (Mark mark : marks) {
            mark.toBBCode(s);
        }
    }
    public void toMarkdown(StringBuilder s) {
        for (Mark mark : marks) {
            mark.toMarkdown(s);
        }
    }
}
