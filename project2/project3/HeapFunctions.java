package project3;
//Joseph Cuevas
//Jet Ysalina
public class HeapFunctions {

	//Heapify()
	public static void percolateDown(int[] arr, int n, int i) {
		int largest = i; 
        int left = 2 * i + 1; 
        int right = 2 * i + 2; 
  
        // Checks if left child is smaller than root 
        if (left < n && arr[left] > arr[largest]) 
            largest = left;
  
        // Checks if right child is smaller than largest so far 
        if (right < n && arr[right] > arr[largest]) 
            largest = right; 
  
        // If largest is not root 
        if (largest != i) { 
        	//Swap() the largest to the root
            swap(arr, i, largest); 
  
            //Heapify the sub-tree recursively 
            percolateDown(arr, n, largest); 
        } 
			
	}

	public static void buildMinHeap(int[] arr) {
		int n = arr.length;
		for(int i = (n/2) - 1; i >= 0; i--) {
			percolateDown(arr, n, i);
		}
		System.out.println();
	}
	
	public static int[] pop(int[] arr) {
		int n = arr.length;
		int[] newArr = new int[n - 1];
		if(n == 0) {
			System.out.println("Cant' Pop from an empty array.");
			arr = new int[0];
		} else {
			System.out.println("Heap before pop function:");
			Menu.printArray(arr);
			//set last element as first
			arr[0] = arr[n - 1];
			//truncate last element
			for(int j = 0; j < newArr.length; j++) {
            	newArr[j] = arr[j];
            }
			//rebuild heap
			buildMinHeap(newArr);
			System.out.println("Heap after pop function:");
			Menu.printArray(newArr);
			//return newArr;
		}
		return newArr;
	}
	
	public static int[] insert(int[] arr,int n, int element) {
		//Make new heap to increase array size
		int[] newHeap = new int[n + 1];
		//Set new element as last index 
		newHeap[n] = element;
		//Copy over previous elements from first array
		for(int i = 0; i < n ; i++) {
			newHeap[i] = arr[i];
		}
		//Rebuild heap
		for (int j = newHeap.length - 1; j >= 0; j--) {
			percolateDown(newHeap, n, j);
		}
		System.out.println();
		Menu.printArray(newHeap);
		return newHeap;
	}
	
	public static void heapSort(int[] arr) {
		// Build heap (rearrange array) 
        buildMinHeap(arr); 
  
        // Extract an element from heap 
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
