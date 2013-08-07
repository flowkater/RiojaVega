package org.pickers.sort;

public class BubbleSort implements Sortable {

	@Override
	public int[] sorting(int[] array) {
		
		System.out.println("BubbleSort run...");
		
		for(int i = 0; i < array.length; i++) {
			
			for(int j = 0 ; j < array.length - i -1 ; j++) {
				
				if(array[j] > array[j+1] ) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
					
			}
			
		}
		
		return array;
	}

}
