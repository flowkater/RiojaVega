package org.pickers.sort;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class SortVO {

	@Element
	private String sortName;
	@Element
	private String sortClass;
	
	
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortClass() {
		return sortClass;
	}
	public void setSortClass(String sortClass) {
		this.sortClass = sortClass;
	}
	
	
}
