import java.util.Arrays;

import org.pickers.di.SortContainer;
import org.pickers.sort.Sortable;


public class Main {

	public static void main(String[] args) throws Exception {
		
		SortContainer sortDIContainer = new SortContainer();
		
		Sortable sortable = sortDIContainer.getSortable();
		
		int[] result = sortable.sorting(new int[]{5,4,6,8,7,9,11,3,2,1});
		
		System.out.println(Arrays.toString(result));
	}
	
}
