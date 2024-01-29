package md2html;

import java.util.List;
public class Allocation implements Mark {
    private final List<Mark> marks;
    private final String HtmlStart;
    private final String HtmlEnd;

    public Allocation(List<Mark> list, String HtmlStart, String HtmlEnd) {
        this.marks = list;
        this.HtmlStart = convertStartToHtml(HtmlStart);
        this.HtmlEnd = convertEndToHtml(HtmlEnd);
    }

    public Allocation(List<Mark> list, char HtmlStart, char HtmlEnd) {
        this.marks = list;
        this.HtmlStart = convertStartToHtml(Character.toString(HtmlStart));
        this.HtmlEnd = convertEndToHtml(Character.toString(HtmlEnd));
    }

    public Allocation(List<Mark> list, int HeaderLevel) {
        this.marks = list;
        this.HtmlStart = "<h" + HeaderLevel + ">";
        this.HtmlEnd = "</h" + HeaderLevel + ">";
    }

    public Allocation(List<Mark> list) {
        this.marks = list;
        this.HtmlStart = "<p>";
        this.HtmlEnd = "</p>";
    }

    public void toHtml(StringBuilder s) {
        s.append(HtmlStart);
        for (Mark mark : marks) {
            mark.toHtml(s);
        }
        s.append(HtmlEnd);
    }

    public String convertStartToHtml(String str) {
        return switch (str) {
            case "*", "_" -> "<em>";
            case "**", "__" -> "<strong>";
            case "--" -> "<s>";
            case "''" -> "<q>";
            case "`" -> "<code>";
            default -> "";
        };
    }

    public String convertEndToHtml(String str) {
        return switch (str) {
            case "*", "_" -> "</em>";
            case "**", "__" -> "</strong>";
            case "--" -> "</s>";
            case "''" -> "</q>";
            case "`" -> "</code>";
            default -> "";
        };
    }
}
