package org.pickers.di;

import org.pickers.sort.Sortable;

public class SortContainer {

	Sortable sortObj = null;
	
	
	public Sortable getSortable() throws Exception {
		
		if ( sortObj == null)
			sortObj = (Sortable) Class.forName(SortParser.getSortVO().getSortClass()).newInstance();
		
		return sortObj;
	}
	
}
