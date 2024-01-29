package markup;
import java.util.List;
public class Allocation implements Mark {
    private List<Mark> marks;
    private String markUpStr;
    private String BBCodeStrStart;
    private String BBCodeStrEnd;

    public Allocation(List<Mark> list, String markUpStr, String BBCodeStrStart, String BBCodeStrEnd) {
        marks = list;
        this.markUpStr = markUpStr;
        this.BBCodeStrStart = BBCodeStrStart;
        this.BBCodeStrEnd = BBCodeStrEnd;
    }
    public void toMarkdown(StringBuilder s) {
        s.append(markUpStr);
        for (Mark mark : this.marks) {
            mark.toMarkdown(s);
        }
        s.append(markUpStr);
    }

    public void toBBCode(StringBuilder s) {
        s.append(BBCodeStrStart);
        for (Mark mark : marks) {
            mark.toBBCode(s);
        }
        s.append(BBCodeStrEnd);
    }
}
