package project3;

public class tester {

	public static void main(String[] args) {
		int[] arr = Menu.createRandArr(10);
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		HeapFunctions.heapSort(arr);
		System.out.println();
		Menu.printArray(arr);
	}
}
