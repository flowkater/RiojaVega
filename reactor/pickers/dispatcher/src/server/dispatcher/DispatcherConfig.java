package server.dispatcher;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class DispatcherConfig {

	//dispatcher config file
	private File file = null;
	
	// key is dispatcher Name
	private Map<String, DispatcherVO> dispatcherMap = new HashMap<String, DispatcherVO>();
	
	public DispatcherConfig() {
		//default file path
		file = new File("dispatcherConfig.xml");
	}
	
	public DispatcherConfig(File configFilePath) {
		file = configFilePath;
	}
	
	private void parseConfig() {
		
		Serializer serializer = new Persister();
		
		DispatcherList dList = null;
		
		try {
			dList = serializer.read(DispatcherList.class, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(DispatcherVO dispatcher : dList.getDispatcherList()) {
			
			dispatcherMap.put(dispatcher.getName(), dispatcher);
			
		}
		
	}
	
	public DispatcherVO getDispatcherInfo(String dispatcherName) {
		return dispatcherMap.get(dispatcherName);
	}
	
}
