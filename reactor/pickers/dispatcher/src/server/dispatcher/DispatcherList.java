package server.dispatcher;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="dispatchers")
public class DispatcherList {

	@ElementList
	private List<DispatcherVO> dispatcherList = new ArrayList<DispatcherVO>();

	public List<DispatcherVO> getDispatcherList() {
		return dispatcherList;
	}

	public void setDispatcherList(List<DispatcherVO> dispatcherList) {
		this.dispatcherList = dispatcherList;
	}
	
	
	
}
