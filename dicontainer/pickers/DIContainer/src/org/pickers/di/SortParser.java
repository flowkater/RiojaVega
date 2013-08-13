package org.pickers.di;

import java.io.File;

import org.pickers.sort.SortVO;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class SortParser {

	public static SortVO getSortVO() {
		
		Serializer serializer = new Persister();
		File source = new File("sort.xml");
		
		SortVO result = null;
		
		try {
			result = serializer.read(SortVO.class, source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
