package server.dispatcher;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name="dispatcher")
public class DispatcherVO {

	@Attribute
	private String name;
	
	@Attribute
	private Integer port;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "DispatcherVO [name=" + name + ", port=" + port + "]";
	}
	
	
	
}
