package server.dispatcher;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class Demultiplexer {

	public HandleMap select() {
		
		Serializer serializer = new Persister();
		
		File source = new File("eventConfig.xml");
		
		ParseList list = null;
		try {
			list = serializer.read(ParseList.class, source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<ParseVO> iterator = list.getParseList().iterator();
		
		HandleMap result = new HandleMap();
		
		while(iterator.hasNext()) {
			
			ParseVO parseObj = iterator.next();
			
			try {
				result.put( parseObj.getCode(), 
							(EventHandler)Class.forName(parseObj.getClassName()).newInstance()    
						  );
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	
	
}
