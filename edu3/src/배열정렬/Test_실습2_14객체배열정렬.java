package 자료구조2장기본자료구조;

import java.util.Arrays;

class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;
	public PhyscData2(String name, int height, double vision) {
	    this.name = name;
	    this.height = height;
	    this.vision = vision;
	}
	@Override
	//comparable  자기자신과 매개변수 비교 -- compareto
	//comparator 2개의 매개변수 비교 -- compare
	
	
	public int compareTo(PhyscData2 p) {
		  int result = this.name.compareTo(p.name);
		  if(result != 0) 
			  return result;
	
		  result = this.height - p.height;
		  if(result != 0) {
			  return result;
		  }
		  return Double.compare(this.vision, p.vision);
	}
	
}
public class Test_실습2_14객체배열정렬 {

	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("홍길동", 164, 1.3),
				new PhyscData2("홍길동", 164, 0.7),
				new PhyscData2("홍길동", 164, 0.3),
				new PhyscData2("이길동", 182, 0.6),
				new PhyscData2("이길동", 182, 0.2),
				new PhyscData2("이길동", 182, 0.5),
		};
		//Arrays.sort(null, null); comparator 
		   Arrays.sort(data); 
	        for (PhyscData2 item : data) {
	            System.out.println(item.name + ", " + item.height + ", " + item.vision);
	        }

//	        int[] data1 = new int[10];
//	        showData(data1);
//	        sortData(data1);
//	        showData(data1);
//	    }
////
//	    static void showData(int[] arr) {
//	        for (int i = 0; i < arr.length; i++) {
//	            System.out.print(arr[i] + " ");
//	        }
//	        System.out.println();
//	    }
////
//	    static void swap(int[] arr, int ind1, int ind2) {
//	        int temp = arr[ind1];
//	        arr[ind1] = arr[ind2];
//	        arr[ind2] = temp;
//	    }
////
//	    static void sortData(int[] arr) {
//	        for (int i = 0; i < arr.length; i++) {
//	            for (int j = i + 1; j < arr.length; j++) {
//	                if (arr[i] > arr[j])
//	                    swap(arr, i, j);
//	            }
//	        }
	    }
	}
