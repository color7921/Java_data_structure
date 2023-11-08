package 자료구조3장;

import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
        return "Name: " + name + ", Height: " + height + ", Vision: " + vision;
    }
	public int compareTo(PhyscData p) {
		//System.out.println("compareTo() 호출");
		return name.compareTo(p.name);// Arrays.sort랑 세트
	}
}

public class Test_실습3_6_1객체배열이진탐색 {

	static void sortData(PhyscData[] p) {
		for (int i = 0; i < p.length - 1; i++) {
			for (int j = 0; j < p.length - i - 1; j++) {
				if (p[j].compareTo(p[j + 1]) > 0) {
					PhyscData temp = p[j];
					p[j] = p[j + 1];
					p[j + 1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {

		PhyscData[] data = { 
				new PhyscData("홍길동", 162, 0.3), 
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7), 
				new PhyscData("김홍길동", 172, 0.3), 
				new PhyscData("길동", 182, 0.6),
				new PhyscData("길동", 167, 0.2), 
				new PhyscData("길동", 167, 0.5), };
		showData(data);
		Arrays.sort(data); // Override 호출 해야됨
		sortData(data);
		//Arrays.binarySearch(data, key);
		showData(data);
		PhyscData key = new PhyscData("길동", 167, 0.2);
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);
		
		key = new PhyscData("길동", 167, 0.5);
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);
	}

	static int linearSearch(PhyscData[] arr, PhyscData key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].compareTo(key) == 0) {
		//배열의 각 요소를 key와 비교함
				return i;
			}
		}
		return -1; 
		//key가 배열내에 존재하지 않음
	}

	static int binarySearch(PhyscData[] arr, PhyscData key) {
		int pl = 0;
		int pr = arr.length - 1;

		while (pl <= pr) {
			int pc = (pl + pr) / 2;
			int cmp = key.compareTo(arr[pc]);

			if (cmp == 0) {
				return pc;
			} else if (cmp > 0) {
				pl = pc + 1;
			} else {
				pr = pc - 1;
			}
		}

		return -1;
	}

	static void showData(PhyscData[] arr) {
		System.out.println();
		for (PhyscData fruit : arr) {
			System.out.print(fruit + " ");
		}
		System.out.println();
	}

}
