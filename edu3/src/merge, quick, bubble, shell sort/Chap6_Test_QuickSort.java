package 자료구조6장;

import java.util.Stack;

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
}

public class Chap6_Test_QuickSort {

//퀵 정렬(비재귀 버전)

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static int quickSort(int[] a, int left, int right) {

		int sortCount = 0;
		
		Stack<Point> st = new Stack<>();
		Point pt = new Point(left, right);
		st.push(pt);
		// 책보면서 코드 추가
		 while (!st.isEmpty()) {
	            Point current = st.pop();
	            left = current.getX();
	            right = current.getY();

	            if (right > left) {
	                int pivotIndex = partition(a, left, right);
	                sortCount++;
	                // 파티션한 결과를 기반으로 스택에 왼쪽과 오른쪽 부분 배열을 추가
	                if (pivotIndex - 1 > left) {
	                    st.push(new Point(left, pivotIndex - 1));
	                }
	                if (pivotIndex + 1 < right) {
	                    st.push(new Point(pivotIndex + 1, right));
	                }
	            }
	        }
		 return sortCount;
	    }

	    // 파티션 함수: 피벗을 기준으로 작은 값은 왼쪽으로, 큰 값은 오른쪽으로 배치
	    static int partition(int[] a, int left, int right) {
	        int pivot = a[right];
	        int i = left - 1;

	        for (int j = left; j < right; j++) {
	            if (a[j] < pivot) {
	                i++;
	                swap(a, i, j);
	            }
	        }

	        swap(a, i + 1, right);
	        return i + 1;
	    }
	

	public static void main(String[] args) {
		int nx = 10;
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		System.out.println();

		 int sortCount = quickSort(x, 0, nx - 1);
		//quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		
		 System.out.println("\n정렬을 " + sortCount + "번 수행했습니다.");
	}
}
