
// PhyscData 클래스를 Comparable로 수정해야 한다.
// stack을 이용한 객체들의 non-recusrive MergeSort 구현

package 자료구조6장;

public class Chap6_Test_MergeSort {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb) {

		int n = rightb - lefta + 1;
		PhyscData[] tmp = new PhyscData[n];
		int i = lefta, j = leftb, k = 0;

		// merge한 값을 tmp에 넣는다.
		while (i <= righta && j <= rightb) { // 작은쪽을 tmp배열에 저장
			if (a[i].compareTo(a[j]) < 0) {
				tmp[k++] = a[i++];
			} else {
				tmp[k++] = a[j++];
			}
		}

		// tmp를 정렬한다.
		while (i <= righta) {
			tmp[k++] = a[i++];
		}

		while (j <= rightb) {
			tmp[k++] = a[j++];
		}

		for (i = 0; i < n; i++) {
			a[lefta + i] = tmp[i];
		}
	}

	static class PhyscData implements Comparable<PhyscData> {
		private String name;
		private int height;
		private double vision;

		public PhyscData(String name, int height, double vision) {
			this.name = name;
			this.height = height;
			this.vision = vision;
		}

		public String getName() {
			return name;
		}

		public int getHeight() {
			return height;
		}

		public double getVision() {
			return vision;
		}

		// 키 순서대로 오름차순 정렬
		public int compareTo(PhyscData other) {
			if (this.height < other.height) {
				return -1; // this 객체가 작음
			} else if (this.height > other.height) {
				return 1; // this 객체가 큼
			} else {
				return 0; // 키가 같음
			}
		}

		// 시력 순서대로 오름차순 정렬
		public int compareTo1(PhyscData other) {

			if (this.vision < other.vision) {
				return -1;
			} else if (this.vision > other.vision) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);
		merge(a, left, mid, mid + 1, right);
		return;
	}

	public static void main(String[] args) {

		PhyscData[] x = { new PhyscData("강민하", 162, 0.3), new PhyscData("김찬우", 173, 0.7),
				new PhyscData("박준서", 171, 2.0), new PhyscData("유서범", 171, 1.5), new PhyscData("이수연", 168, 0.4),
				new PhyscData("장경오", 171, 1.2), new PhyscData("황지안", 169, 0.8), };
		int nx = x.length;

		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (int i = 0; i < x.length; i++)
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
