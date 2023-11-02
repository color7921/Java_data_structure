package 자료구조2장기본자료구조;

public class Test_실습2_14스트링배열정렬 {

	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};
//string -> 클래스
		//data[i].compareto()
		showData(data);
		sortData(data);
		showData(data);
	}
	static void showData(String[]arr) {
		for (int i = 0; i < arr.length; i++) {
	          System.out.print(arr[i] + " ");
			}
	}

	static void swap(String[]arr, int ind1, int ind2) {
		 String temp = arr[ind1];
			arr[ind1] = arr[ind2];
			arr[ind2] = temp;
	}
	
	static void sortData(String []arr) {
		System.out.println();
		System.out.println("알파벳 순으로 정렬");
		for (int i =0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i].compareTo(arr[j]) > 0)
					swap(arr, i, j);
			}
		}
	}
// if(data[i] > data[j] ---> string이면 안된다.
	// swap(data,i,j);
}


