package 자료구조3장;

//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
//comparator 구현 실습
import java.util.Arrays;
import java.util.Comparator;

public class Test_실습3_6_0스트링배열이진탐색 {

	private static final Comparator<String> Comparator = null;

	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "감", "배", "사과", "포도", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData(data);
		Arrays.sort(data);
		
		sortData(data);
		showData(data);
		
		String key = "감";
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = "사과";
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + idx);
	}
	
	static int linearSearch(String[] data, String key) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(key)) {
				return i;
			}
		}
		return -1;
	}
	
	static int binarySearch(String[] a, String key) {
        int pl = 0;
        int pr = a.length - 1;

        while (pl <= pr) {
            int pc = (pl + pr) / 2;
            int cmp = key.compareTo(a[pc]);

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

//	static int binarySearch(String[] a, String key) {
//		int pl = 0;
//		int pr = a.length - 1;
//
//		while (pl <= pr) {
//			int pc = (pl + pr) / 2;
//			int cmp = a[pc].compareTo(key);
//
//			if (cmp == 0) {
//				return pc;
//			} else if (cmp < 0) {
//				pl = pc + 1;
//			} else {
//				pr = pc - 1;
//			}
//		}
//
//		return -1;
//	}

	static void showData(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	static void swap(String[] arr, int ind1, int ind2) {
		String temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}

	static void sortData(String[] arr) {
		System.out.println();
		System.out.println("알파벳 순으로 정렬");
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].compareTo(arr[j]) > 0)
					swap(arr, i, j);
			}
		}
	}

}
