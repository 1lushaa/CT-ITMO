package markup;
import java.util.List;
public class Emphasis extends Allocation {
    public Emphasis(List<Mark> list) { super(list, "*", "[i]", "[/i]"); }
}
