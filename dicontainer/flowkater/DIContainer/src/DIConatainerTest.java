public class DIConatainerTest {
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		int[] intArr = { 1, 29, 15, 37, 8, 123, 32612, 1, 2, 23 };
		// int[] intArr = { 3, 1, 9, 7, 9, 5 };

		SortableContainer sortableContainer = new SortableContainer(); // 팩토리 객체로 변경, sortFactory,
														// sort 관련된 네이밍
		Sort sortContainer = sortableContainer.getInstance(); // getInstance 메서드 (사용자입장에서 네이밍)

		sortContainer.sorting(intArr);

		for (int i : intArr)
			System.out.println(i);
	}
}
