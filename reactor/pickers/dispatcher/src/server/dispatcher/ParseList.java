package server.dispatcher;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="events")
public class ParseList {

	@ElementList(name="event", inline=true)
	private ArrayList<ParseVO> parseList = new ArrayList<ParseVO>();

	public ArrayList<ParseVO> getParseList() {
		return parseList;
	}

	public void setParseList(ArrayList<ParseVO> parseList) {
		this.parseList = parseList;
	}
	
	
}

