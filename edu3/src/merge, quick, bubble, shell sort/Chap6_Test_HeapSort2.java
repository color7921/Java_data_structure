package 자료구조6장;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap1 {
	public void Insert(int x);
	public int DeleteMax();
}

class Heap1 implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public Heap1(int sz) {
		//생성자
	}

	public void display() {
		//출력
	}
	@Override
	public void Insert(int x) {

		
	}
	@Override
	public int DeleteMax() {
		return MaxSize;
		
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}
public class Chap6_Test_HeapSort2 {
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

				x[i] = random
				//난수생성
				heap.Insert(count);
				break;

			case 2:
				heap.display();
				break;
			
			case 3:
				while() {
					sorted[i] = heap.DeleteMax();
				}
				break;
			
			case 4:
				return;

			}
		} while (select < 5);

		return;
	}
}
