public class QuickSort implements Sort {
	@Override
	public void sorting(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/*
	 * QuickSort
	 */
	public void quickSort(int[] arr, int p, int r) {
		// System.out.println("----- p: " + p + "-----r: " + r);
		if (p < r) {
			int q = partition(arr, p, r);
			// System.out.println("----- q: " + q + "-----");
			quickSort(arr, p, q - 1);
			quickSort(arr, q + 1, r);
		}
	}

	/*
	 * QucickSort 에서 사용할 분할 알고리즘
	 */
	private int partition(int[] arr, int p, int r) {
		int x = arr[r];
		int i = p - 1;
		// System.out.println("----- i: " + i + "-----");

		for (int j = p; j < r; j++) {
			if (arr[j] <= x) {
				int tmp = arr[++i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}

		int tmp = arr[i + 1];
		arr[i + 1] = arr[r];
		arr[r] = tmp;

		return i + 1;
	}
}
