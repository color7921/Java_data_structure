package 자료구조2장기본자료구조;

//교재 60 - 실습 2-6
//2번 실습
import java.util.Random;

public class Test_실습2_6정수배열정렬 {
	
	public static void main(String[] args) {
		
		int []data = new int[10];
		inputData(data);
		showData(data);
		sortData(data);
		showData(data);
		reverse(data);
		//reverseSort(data);
		showData(data);
		///*
	}
		//*/

		
	static void showData(int []arr) {
		for (int i = 0; i < arr.length; i++) {
          System.out.print(arr[i] + " ");
		}
	}
	static void inputData(int []arr) {
		Random rd = new Random();
		for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(100); 
		}
	}
	static void swap(int[]arr, int ind1, int ind2) {
		
			  int temp = arr[ind1];
				arr[ind1] = arr[ind2];
				arr[ind2] = temp;
				//System.out.print(ind1 + ind2);
	        
	}
	
	static void sortData(int []arr) {
		//sortData(arr);
		//Arrays.sort(arr);
		System.out.println();
		for (int i =0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i]>arr[j])
					swap(arr, i, j);
			}
		}
		//for (int num: arr) {
			//System.out.print(num+" ");
		//}
	}
	static void reverse(int[] arr) {
		System.out.print("\n역순배치");
		for(int i =0; i < arr.length /2; i++)
			swap(arr, i, arr.length - i -1);
		System.out.println();
		
	}
//	static void reverseSort(int []arr) {
//		reverseSort(arr);//역순으로 재배치
//		List<int[]> lst1 = Arrays.asList(arr);
//		Collections.reverse(lst1);
//		System.out.println();
//	
//	}

}
