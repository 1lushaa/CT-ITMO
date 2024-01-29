package markup;
import java.util.List;
public class Strikeout extends Allocation {
    public Strikeout(List<Mark> list) { super(list, "~", "[s]", "[/s]"); }
}
