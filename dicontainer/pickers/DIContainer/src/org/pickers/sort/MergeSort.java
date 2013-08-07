package org.pickers.sort;

import java.util.Arrays;


public class MergeSort implements Sortable {

	@Override
	public int[] sorting(int[] collection) {
		// TODO Auto-generated method stub
		System.out.println("MergeSort run...");
		return merge(collection);
	}

	
	public int[] merge(int[] collection) {
		
		
		int mid = collection.length / 2;
		
		int[] leftMergeList = new int[mid-1];
		int[] rightMergeList = new int[collection.length - mid -1];
		
		
		leftMergeList = Arrays.copyOfRange(collection, 0, mid);
		
		rightMergeList = Arrays.copyOfRange(collection, mid , collection.length);

		if(leftMergeList.length > 1) {
			leftMergeList = merge(leftMergeList);
		}
		if(rightMergeList.length > 1){
			rightMergeList = merge(rightMergeList);
		}
		
		int leftPoint = 0;
		int rightPoint = 0;
		int[] result = new int[leftMergeList.length + rightMergeList.length];
		for(int i = 0; i < leftMergeList.length + rightMergeList.length ; i++) {
			if( rightPoint >= rightMergeList.length) {
				result[i] = leftMergeList[leftPoint];
				leftPoint++;
			}
			else if( leftPoint >= leftMergeList.length) {
				result[i] = rightMergeList[rightPoint];
				rightPoint++;
			}
			else if(leftMergeList[leftPoint] < rightMergeList[rightPoint]) {
				result[i] = leftMergeList[leftPoint];
				leftPoint++;
			}
			else if(leftMergeList[leftPoint] > rightMergeList[rightPoint]) {
				result[i] = rightMergeList[rightPoint];
				rightPoint++;
			}
			
		}
		
		
		return result;
		
	}
	
}
