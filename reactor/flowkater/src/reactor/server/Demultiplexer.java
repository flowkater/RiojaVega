package reactor.server;

import java.io.File;
import java.util.Map;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import reactory.simpleclass.Protocols;

public class Demultiplexer {
	public Map<String, String> select() {
		Serializer serializer = new Persister();
		File source = new File("ProtocolConfig.xml");
		Protocols protocols = new Protocols();

		try {
			protocols = serializer.read(Protocols.class, source);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return protocols.getProtocols();
	}
}