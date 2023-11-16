package 자료구조6장;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);

	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; //
	private int MaxSize; //

	public Heap(int sz) {
		// 생성자
		MaxSize = sz;
		heap = new int[MaxSize];
		n = 0;

	}

	public void display() {
		// 출력
		if (n == 0) {
			System.out.println("Heap is empty.");
			return;
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}

	@Override
	public void Insert(int x) {
		if (n == MaxSize) {
			HeapFull();
			return;
		}

		n++;
		int i = n;

		while (i > 1 && x > heap[i / 2]) {
			heap[i] = heap[i / 2];
			i /= 2;
		}

		heap[i] = x;
	}

	@Override
	public int DeleteMax() {

		if (n == 0) {
			HeapEmpty();
			return -1;
		}

		int max = heap[1];
		int last = heap[n];
		n--;

		int i = 1;

		while (2 * i <= n) {
			int child = 2 * i;
			if (child < n && heap[child] < heap[child + 1]) {
				child++;
			}
			if (last >= heap[child]) {
				break;
			}

			heap[i] = heap[child];
			i = child;
		}

		heap[i] = last;

		return max;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

public class Chap6_Test_HeapSort {
	static void showData(int[] d) {
		for (int i = 0; i < d.length; i++)
			System.out.print(d[i] + " ");
		System.out.println();
	}

	public static void main(String[] args, int i) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10;
	    int[] x = new int[count+1];
	    int []sorted = new int[count];

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1:

				if (i < count) {
			        int randomNumber = rnd.nextInt(100); 
			        x[i] = randomNumber; 
			        heap.Insert(randomNumber); 
			        i++; 
			    } else {
			        System.out.println("힙이 가득 찼습니다.");
			    }
			    break;

			case 2:
				heap.display();
				break;
			
			case 3:
				int index = 0;
				while(index > 0) {
					sorted[i] = heap.DeleteMax();
					index ++;
				}
				break;
			
			case 4:
				return;
			}
		} while (select < 5);

		return;
	}
}
