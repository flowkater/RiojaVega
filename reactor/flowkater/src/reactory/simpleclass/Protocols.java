package reactory.simpleclass;

import java.util.HashMap;
import java.util.Map;

import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

@Root(name = "protocols")
public class Protocols {

	@ElementMap(entry = "protocol", key = "header", attribute = true, inline = true)
	private Map<String, String> protocols = new HashMap<String, String>();

	public Map<String, String> getProtocols() {
		return protocols;
	}

}
