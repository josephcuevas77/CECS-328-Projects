package project1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import project1.Algorithms;

public class projectMenu {
	
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		boolean isRunning = true;
		int choice = -1;
		printMenu();
		while(isRunning) {
			if(in.hasNextInt())choice = in.nextInt();
			else System.out.println("Not a valid menu input");
			while(choice < 1 || choice > 4){
				System.out.println("Please select one of the valid options 1-4");
				printMenu();
				if(in.hasNextInt())choice = in.nextInt();
				else System.out.println("Not a valid menu input");
			}
			switch(choice) {
			case 1:
				userInputMSS();
				break;
			case 2:
				randArrMSS();
				break;
			case 3:
				randArrSingleMSS();
				break;
			case 4:
				System.out.println("Program has quit.");
				isRunning = false;
				break;
			}
		}
		in.close();
	}

	//Option 1
	private static void userInputMSS() {
		System.out.println("Please input a comma-delimited array on ints");
		in.nextLine();
		String userInput = in.nextLine();
		int[] arr = userInToIntArray(userInput);
		//Solution 1
		int mSS1 = Algorithms.Solution1(arr);
		System.out.println("MSS from Solution 1: " + mSS1);
		int mSS2 = Algorithms.Solution2(arr);
		System.out.println("MSS from Solution 2: " + mSS2);
		long start = System.nanoTime();
		int mSS3 = Algorithms.Solution3(arr,0,arr.length - 1);
		long end = System.nanoTime();
		long total = end - start;
		displayRunTime(total, 3);
		System.out.println("MSS from Solution 3: " + mSS3);
		int mSS4 = Algorithms.Solution4(arr);
		System.out.println("MSS from Solution 4: " + mSS4);
		printMenu();
	}

	//Option 2
	private static void randArrMSS() {
		int length = 0;
		System.out.println("Please designate a length to a randomly generated array");
		length = in.nextInt();
		int[] newA = createRandArr(length);
		System.out.println("Which algorithms would you like the array to be run on? (1-4)");
		in.nextLine();
		String solList = in.nextLine();
		solList.replaceAll("\\s+","");
		for(int i = 0; i < solList.length(); i++) {
			switch(solList.charAt(i)) {
			case '1':
				int mSS1 = Algorithms.Solution1(newA);
				System.out.println("MSS from Solution 1: " + mSS1);
				break;
			case '2':
				int mSS2 = Algorithms.Solution2(newA);
				System.out.println("MSS from Solution 2: " + mSS2);
				break;
			case '3':
				long start = System.nanoTime();
				int mSS3 = Algorithms.Solution3(newA,0,newA.length-1);
				long end = System.nanoTime();
				long total = end - start;
				displayRunTime(total, 3);
				System.out.println("MSS from Solution 3: " + mSS3);
				break;
			case '4':
				int mSS4 = Algorithms.Solution4(newA);
				System.out.println("MSS from Solution 4: " + mSS4);
				break;
			}
		}
		printMenu();
	}

	//Option 3
	private static void randArrSingleMSS() {
		int length = 0;
		int length2 = 0;
		System.out.println("Which algorithm would you like the array to be run through: 1,2,3, or 4?"
				+ "\nPlease select one.");
		int solChoice = in.nextInt();
		System.out.println("Please designate a length to a randomly generated array (m)");
		length = in.nextInt();
		System.out.println("Please designate a length to another randomly generated array (n)");
		length2 = in.nextInt();
		in.nextLine();
		int[] newA = createRandArr(length);
		int[] newA2 = createRandArr(length2);
		
		switch(solChoice) {
		case 1:
			int mSS1 = Algorithms.Solution1(newA);
			System.out.println("MSS from Solution 1 for array 'm': " + mSS1);
			predictRunTime(newA2,1,length,length2);
			int mSS1a = Algorithms.Solution1(newA2);
			System.out.println("MSS from Solution 1 for array 'n': " + mSS1a);
			break;
		case 2:
			int mSS2 = Algorithms.Solution2(newA);
			System.out.println("MSS from Solution 2 for array 'm': " + mSS2);
			predictRunTime(newA2,2,length,length2);
			int mSS2a = Algorithms.Solution2(newA2);
			System.out.println("MSS from Solution 2 for array 'n': " + mSS2a);
			break;
		case 3:
			long start = System.nanoTime();
			int mSS3 = Algorithms.Solution3(newA,0,newA.length-1);
			long end = System.nanoTime();
			long start2 = System.nanoTime();
			int mSS3a = Algorithms.Solution3(newA2,0,newA2.length-1);
			long end2 = System.nanoTime();
			long total  = end - start;
			long total2  = end2 - start2;
			displayRunTime(total, 3);
			System.out.println("MSS from Solution 3 for array 'm' is: " + mSS3);
			predictRunTime(newA2,3,length,length2);
			displayRunTime(total2, 3);
			System.out.println("MSS from Solution 3 for array 'n' is: " + mSS3a);
			break;
		case 4:
			int mSS4 = Algorithms.Solution4(newA);
			System.out.println("MSS from Solution 4 for array 'm' is: " + mSS4);
			predictRunTime(newA2,4,length,length2);
			int mSS4a = Algorithms.Solution4(newA2);
			System.out.println("MSS from Solution 2 for array 'n': " + mSS4a);
			break;
		}
		printMenu();
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
//		for(int i : arr) {
//			System.out.println(i);
//		}
		int [] a = new int[arr.size()];
		for(int i= 0;i < arr.size();i++) {
			a[i] = arr.get(i);
		}
		return a;
	}

	private static void printMenu() {
		System.out.println("MAIN MENU\n"
				+ "Select One of the Four Options to find MSS\n"
				+ "1. MSS of all 4 Solutions from user-inputted array\n"
				+ "2. Select which Solution on Randomly Gen. Arrays\n"
				+ "3. Select One of Four to find MSS\n"
				+ "4. Quit");
	}

	public static int[] createRandArr(int length){
		Random random = new Random();
		int[] randArr = new int[length];
		for(int i = 0; i < length; i++) {
			int randInt = random.nextInt(101)-50;
			randArr[i]= randInt;
		}
		return randArr;
	}
	
	public static void displayRunTime(long total, int sol) {
		if(total * Math.pow(10, -6) > 1000) { //If milliseconds > 1000 print in seconds
			System.out.println("\nTotal runtime of Solution " + sol + " is: " + (total * Math.pow(10,-9)) + " seconds");
		}else
			System.out.println("\nTotal runtime of Solution " + sol + " is: " + (total * Math.pow(10,-6)) + " milliseconds");
	}
	
	public static void predictRunTime(int[] a,int sol, int arr1L, int arr2L) {
		long runTime = 0;
		long start = 0;
		long end = 0;
		switch(sol) {
		case 1:
			start = System.nanoTime();
			Algorithms.Solution2(a);
			end = System.nanoTime();
			runTime = ((end-start) * (long)(Math.pow(arr2L, 2)/Math.pow(arr1L, 2)));
			break;
		case 2:
			start = System.nanoTime();
			Algorithms.Solution2(a);
			end = System.nanoTime();
			runTime = (long)((end-start) * (Math.pow(arr2L, 3)/Math.pow(arr1L, 3)));
			break;
		case 3:
			start = System.nanoTime();
			Algorithms.Solution3(a,0,a.length - 1);
			end = System.nanoTime();
			runTime = ((end-start) * (long)((Math.log(arr2L)/Math.log(2))/(Math.log(arr1L)/Math.log(2))));
			break;
		case 4:
			start = System.nanoTime();
			Algorithms.Solution4(a);
			end = System.nanoTime();
			runTime = (long)(end-start) * (long)(arr1L/arr2L);
			break;
		}
		if(runTime * Math.pow(10, -6) > 1000)
			System.out.println("The predicited runtime for Solution " + sol + " for array 'n' is " + (runTime* Math.pow(10, -9)) + " seconds");
		else
			System.out.println("The predicited runtime for Solution " + sol + " for array 'n' is " + (runTime* Math.pow(10, -6)) + " milliseconds");
	}

}
