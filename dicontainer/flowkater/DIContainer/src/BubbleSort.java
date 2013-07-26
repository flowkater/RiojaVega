public class BubbleSort implements Sort {
	@Override
	public void sorting(int[] arr) {
		for (int i = arr.length - 1; i > 2; i--) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}
}
