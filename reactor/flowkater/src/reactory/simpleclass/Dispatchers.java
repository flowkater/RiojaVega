package reactory.simpleclass;

import java.util.HashMap;
import java.util.Map;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

@Root(name = "dispatchers")
public class Dispatchers {
	@ElementMap(entry = "dispatcher", key = "port", attribute = true, inline = true)
	private Map<Integer, String> dispatchers = new HashMap<Integer, String>();

	public Map<Integer, String> getDispatchers() {
		return dispatchers;
	}
}
