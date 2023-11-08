
package 자료구조3장;

//3장 객체 배열 정렬 - binary search
/*
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator(); 클래스의 객체생정
* Arrays.sort(array, myComparator); 비교연산자를 mycomparator로 써라
* Collections.sort(list, myComparator);
*/

import java.util.Arrays;
import java.util.Comparator;

class Fruit3 {
	String name;
	int price;
	String expire;

	public Fruit3(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}

	@Override
	public String toString() {
		return "<" + name + ", " + price + ", " + expire + ">";
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
}

class FruitNameComparator implements Comparator<Fruit3> {
	public int compare(Fruit3 f1, Fruit3 f2) {
		int resultname = f1.getName().compareTo(f2.getName());
		if (resultname != 0)
			return resultname;

		int resultprice = Integer.compare(f1.getPrice(), f2.getPrice());
		if (resultprice != 0)
			return resultprice;

		return f1.expire.compareTo(f2.expire);
		// 구현
	}
}

public class Test_실습3_7객체배열이진탐색 {

	private static void sortData(Fruit3[] arr, Comparator<Fruit3> cc_price) {
		// 구현
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (cc_price.compare(arr[i], arr[j]) > 0) {
					Fruit3 temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	static void swap(Fruit3[] arr, int ind1, int ind2) {
		Fruit3 temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}

	static void sortData(Fruit3[] arr, FruitNameComparator cc) {
		// 구현
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (cc.compare(arr[i], arr[j]) > 0)
					swap(arr, i, j);
			}
		}
	}

	static void showData(Fruit3[] arr) {
		System.out.println();
		for (Fruit3 fx : arr) {
			System.out.print(fx + " ");
		}
		System.out.println();
	}

	// 교재 111 페이지 참조하여 구현
	static int binarySearch(Fruit3[] a, Fruit3 f, Comparator<Fruit3> cc) {

		int left = 0;
		int right = a.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			int comparisonResult = cc.compare(a[mid], f);

			if (comparisonResult == 0) {

				return mid;
			} else if (comparisonResult < 0) {

				left = mid + 1;
			} else {

				right = mid - 1;
			}
		}

		return -1;
	}

	// 구현

	public static void main(String[] args) {

		Fruit3[] arr = { new Fruit3("사과", 200, "2023-5-8"), new Fruit3("사과", 200, "2023-6-8"),
				new Fruit3("오렌지", 200, "2023-7-8"), new Fruit3("바나나", 50, "2023-5-18"),
				new Fruit3("수박", 880, "2023-5-28"), new Fruit3("체리", 10, "2023-9-8") };
		System.out.println("\n정렬전 객체 배열: ");
		showData(arr);
		FruitNameComparator cc = new FruitNameComparator();
		Arrays.sort(arr, cc);// cc = 비교연산자
		sortData(arr, cc);
		// showData(arr);
		// *
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // Fruit3에 compareTo()가 있어도 람다식 우선 적용
		// 람다 함수(익명 함수)에 a,b를(compare(T t1, T t2)로) 넘겨줌 return a.get() - > b.get()
		System.out.println("\n람다식 정렬(가격)후 객체 배열: ");
		showData(arr);
		// */
		// *

		Arrays.sort(arr, new Comparator<Fruit3>() {
			@Override
			public int compare(Fruit3 a1, Fruit3 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData(arr);
		// *
		Comparator<Fruit3> cc_name = new Comparator<Fruit3>() {

			@Override
			public int compare(Fruit3 o1, Fruit3 o2) {
				// TODO Auto-generated method stub
				return 0;
			}// 익명클래스 사용

			// 구현

		};
		Comparator<Fruit3> cc_price = new Comparator<Fruit3>() {

			@Override
			public int compare(Fruit3 o1, Fruit3 o2) {
				// TODO Auto-generated method stub
				return 0;
			}

			// 구현

		};

		Fruit3 newFruit3 = new Fruit3("체리", 500, "2023-5-18");
		int result3 = Arrays.binarySearch(arr, newFruit3, cc_name);
		System.out.println("\nArrays.binarySearch() 조회결과::" + result3);
		result3 = binarySearch(arr, newFruit3, cc_name);
		System.out.println("\nbinarySearch() 조회결과::" + result3);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData(arr);
		result3 = Arrays.binarySearch(arr, newFruit3, cc_price);
		System.out.println("\nArrays.binarySearch() 조회결과::" + result3);
		result3 = binarySearch(arr, newFruit3, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3);
		// */
	}

}
