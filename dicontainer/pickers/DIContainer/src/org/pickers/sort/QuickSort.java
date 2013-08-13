package org.pickers.sort;

import java.util.Arrays;

public class QuickSort implements Sortable {

	@Override
	public int[] sorting(int[] collection) {
		
		System.out.println("QuickSort run...");
		
		Arrays.sort(collection);
		return collection;
	}

}
