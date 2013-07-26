import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Config {

	@Element
	private String sort;

	public String getSort() {
		return sort.trim();
	}

	// public int get() {
	// if (sort.trim().equals("BubbleSort"))
	// return 1;
	// else if (sort.trim().equals("QuickSort"))
	// return 2;
	// else
	// return 0;
	// }
}
