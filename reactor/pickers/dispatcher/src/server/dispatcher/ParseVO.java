package server.dispatcher;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="event")
public class ParseVO {

	@Element(name="header")
	private String code;
	
	@Element(name="handler")
	private String className;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
