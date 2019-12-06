package project3;

public class HeapFunctions {

	//Heapify()
	public static void percolateDown(int[] arr, int n, int i) {
		int smallest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 
  
        // Checks if left child is smaller than root 
        if (left < n && arr[left] < arr[smallest]) 
            smallest = left;
        	//Menu.printArray(arr);
  
        // Checks if right child is smaller than smallest so far 
        if (right < n && arr[right] < arr[smallest]) 
            smallest = right; 
        	//Menu.printArray(arr);
  
        // If smallest is not root 
        if (smallest != i) { 
        	//Swap() the smallest to the root
            swap(arr, i, smallest); 
           // Menu.printArray(arr);
  
            //Heapify the sub-tree recursively 
            percolateDown(arr, n, smallest); 
        } 
			
	}

	public static void buildMinHeap(int[] arr) {
		System.out.println("\nMin Heap Built:");
		for(int i = (arr.length/2) - 1; i >= 0; i--) {
			percolateDown(arr, arr.length, i);
		}
		Menu.printArray(arr);
		System.out.println();
	}
	
	public static void pop(int[] arr) {
		int n = arr.length;
		int[] newArr = new int[n - 1];
		if(arr.length == 0) {
			arr = new int[0];
		} else {
			//set last element as first
			arr[0] = arr[n - 1];
			//truncate last element
			for(int j = 0; j < newArr.length; j++) {
            	newArr[j] = arr[j];
            }
		}
		//rebuild heap
		buildMinHeap(newArr);
		System.out.println("Heap after pop function");
		for(int i = 0; i < newArr.length; i ++) {
			System.out.print(newArr[i] + " ");
		}
	}
	
	public static void insert(int[] arr,int n, int element) {
		int[] newHeap = new int[n + 1];
		newHeap[n] = element;
		for(int i = 0; i < n; i++) {
			newHeap[i] = arr[i];
			System.out.print(newHeap[i] + " ");
		}
		for (int j = newHeap.length - 1; j >= 0; j--) {
			percolateDown(newHeap, n, j);
		}
		System.out.println();
		for(int i = 0; i < n; i++) {
			System.out.print(newHeap[i] + " ");
		}
	}
	
	public static void heapSort(int[] arr) {
		// Build heap (rearrange array) 
        buildMinHeap(arr); 
  
        // One by one extract an element from heap 
        for (int i = arr.length - 1; i >= 0; i--) { 
              
            // Move current root to end 
            swap(arr,0,i);
  
            // call perlocateDown on the reduced heap 
            percolateDown(arr, i, 0); 
        } 
		
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a]; 
        arr[a] = arr[b]; 
        arr[b] = temp;
	}
}
