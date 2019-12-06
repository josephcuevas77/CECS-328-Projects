package project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Menu {
	
	public static Scanner in = new Scanner(System.in);
	public static int[] minHeap = new int[0];

	public static void main(String[] args) {
		int[] minHeap = new int[0];
		int[] empMinHeap = new int[0];
		boolean isRunning = true;
		int choice = -1;
		printMenu();
		while(isRunning) {
			if(in.hasNextInt())choice = in.nextInt();
			else System.out.println("Not a valid menu input");
			while(choice < 1 || choice > 7){
				System.out.println("Please select one of the valid options 1-4");
				printMenu();
				if(in.hasNextInt())choice = in.nextInt();
				else System.out.println("Not a valid menu input");
			}
			switch(choice) {
			//Create an empty min heap
			case 1:
				empMinHeap = createEmptyMinHeap();
				System.out.println("Empty min heap has been created.");
				printMenu();
				break;
			//Create heap by inputting comma-delimited array of ints
			case 2:
				System.out.println("Please input a comma-delimited array on ints");
				in.nextLine();
				String userInput = in.nextLine();
				minHeap = userInToIntArray(userInput);
				HeapFunctions.buildMinHeap(minHeap);
				System.out.println("The heap created is: ");
				printArray(minHeap);
				printMenu();
				break;
			//Insert an integer into heap created in option 1 or 2
			case 3:
				System.out.println("Would you like to insert into the heap created from option 1 or 2?");
				int element = in.nextInt();
				while(element < 1 || element > 2) {
					System.out.println("Not a valid option, please try again.");
					element = in.nextInt();
				}
				switch(element) {
				case 1:
					System.out.println("Array after Insertion");
					HeapFunctions.insert(empMinHeap, empMinHeap.length, element);
					break;
				case 2:
					System.out.println("Array after Insertion");
					HeapFunctions.insert(minHeap, empMinHeap.length, element);
					break;
				}
				printMenu();
				break;
			//Pop an element from the heap from option 1 or 2
			case 4:
				System.out.println("Would you like to pop from the heap created from option 1 or 2?");
				int heap = in.nextInt();
				while(heap < 1 || heap > 2) {
					System.out.println("Not a valid option, please try again.");
					heap = in.nextInt();
				}
				switch(heap) {
				case 1:
					HeapFunctions.pop(empMinHeap);
					break;
				case 2:
					HeapFunctions.pop(minHeap);
					break;
				}
				printMenu();
				break;
			//Use heapSort to sort the array
			case 5:
				int[] copy = new int[minHeap.length];
				for(int i = 0; i < minHeap.length; i++) {
					copy[i] = minHeap[i];
				}
				System.out.println("Heap before heapSort is performed:");
				printArray(copy);
				System.out.println("Heap after heapSort is performed:");
				HeapFunctions.heapSort(copy);
				printArray(copy);
				printMenu();
				break;
				//User gives size of array thats generated, then use HeapSort and java's built in sort
			case 6:
				int length = 0;
				System.out.println("Please designate a length to a randomly generated array");
				length = in.nextInt();
				int[] newA = createRandArr(length);
				System.out.println("Now sorting with heapSort");
				long start = System.nanoTime();		//Start timer for HeapSort
				HeapFunctions.heapSort(newA);
				long end = System.nanoTime();
				System.out.println("Array after heapSort:");
				printArray(newA);
				System.out.println("\nNow sorting with Java integrated sort");
				long start2 = System.nanoTime();	//Start timing for Java sort
				Arrays.sort(newA);
				long end2 = System.nanoTime();
				System.out.println("Array after Java integrated Sort:");
				printArray(newA);
				long total = end - start;
				long total2 = end2 - start2;
				//Checks what format the time should be printed in
				if(total * Math.pow(10, -6) > 1000) { //If milliseconds > 1000 print in seconds
					System.out.println("\nTotal runtime of Coded HeapSort is: " + (total * Math.pow(10,-9)) + " seconds");
					System.out.println("Total runtime of Integrated Java Sort is: " + (total2 * Math.pow(10,-9)) + " seconds");
				}else
					System.out.println("\nTotal runtime of Coded HeapSort is: " + (total * Math.pow(10,-6)) + " milliseconds");
					System.out.println("Total runtime of Integrated Java Sort is: " + (total2 * Math.pow(10,-6)) + " milliseconds");
				printMenu();
				break;
			//Quit the program
			case 7:
				System.out.println("Program has quit.");
				isRunning = false;
				break;
			}
		}
	}

	private static int[] createEmptyMinHeap() {
		int[] newArr = new int[0];
		return newArr;
	}
	
	private static int[] userInToIntArray(String userInput) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		String none = "";
		userInput.replaceAll("\\s+","");
		for(int i = 0; i < userInput.length(); i++) {
			if(userInput.charAt(i) != ',') {
				none += userInput.charAt(i); 
			}else {
				arr.add(Integer.parseInt(none));
				none = "";
			}	
		}
		arr.add(Integer.parseInt(none));
		int [] a = new int[arr.size()];
		for(int i= 0;i < arr.size();i++) {
			a[i] = arr.get(i);
		}
		return a;
	}
	
	static int[] createRandArr(int length) {
		Random random = new Random();
		int[] randArr = new int[length];
		for(int i = 0; i < length; i++) {
			int randInt = random.nextInt(2001)-1000;
			randArr[i]= randInt;
		}
		return randArr;
	}
	
	static void printArray(int[] arr) {
		if(arr.length == 0) System.out.println("Array is empty");
		else {
			for(int i = 0; i < arr.length; i ++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void printMenu() {
		System.out.println("\n\nPlease Select from the Options below:\n"
				+ "1.Create an empty min heap.\n" 
				+ "2.Input a comma-separated list of ints to build min heap\n"
				+ "3.Insert single element into heap created from Option 1 or 2.\n" 
			 	+ "4.Pop an element from the heap created from Option 1 or 2.\n" 
			 	+ "5.Display integers in heap in sorted order.\n" 
			 	+ "6.Create random length array and sort it with Heap Sort\n"
			 	+ "7. Quit the program\n");
	}
}
