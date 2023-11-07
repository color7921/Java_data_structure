package 자료구조4장;

/*
 * 원형 큐로서 큐에 Item 객체를 저장 - 교재 소스코드를 원형 큐가 되도록 수정하는 연습
 *   - 원형 큐를 어려워 한다 
 * 원형 큐 실습보다는 객체들의 큐를 ArrayList로 구현하는 실습이 더 유용
 */

import java.util.Scanner;

import 자료구조4장.IntStack.OverflowIntStackException;

class Item {
	int data;

	public Item(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return " < " + this.data + " > ";
	}
}

public class 실습4_5원형큐 {

	static final int QUEUE_SIZE = 4;
	Item[] circleQueue;
	int front, rear;
	int num;
	static boolean isEmpty;

	public 실습4_5원형큐() {
		// 생성자구현
		circleQueue = new Item[QUEUE_SIZE];
		front = rear = num = 0;
		isEmpty = true;
	}

	public boolean isFull() {
		return (rear + 1) % QUEUE_SIZE == front;
	}

	public boolean isEmpty() {
		return rear == front;
	}
	
	public void push(Item i) {
		// 구현
		if (isFull()) {
			System.out.println("가득 찼습니다.");
			return;
		}
		// ﻿배열이 가득 찼을 때 full 출력

		circleQueue[rear] = i;
		rear++;
		rear %= QUEUE_SIZE;
	}

	Item pop() {
		 if (isEmpty()) {
		        System.out.println("큐가 비었습니다.");
		        return null;
		    }
		    Item item = circleQueue[front];
		    circleQueue[front] = null; 
		    front = (front + 1) % QUEUE_SIZE;
		    num--;
		    return item;
		// 구현

	}

	void clear() {
		// 구현
		for (int i = 0; i < QUEUE_SIZE; i++) {
	        circleQueue[i] = null; 
	    }
	    front = rear = num = 0;
	}

	void print() {
		// 구현
	    if (isEmpty()) {
	        System.out.println("큐가 비었습니다.");
	        return;
	    }

	    System.out.print("큐의 개수: "+rear+"개");
	    int current = front;
	    for (int i = 0; i < num; i++) {
	        System.out.print(circleQueue[current]);
	        current = (current + 1) % QUEUE_SIZE;

	        if (i < num - 1) {
	            System.out.print(", ");
	        }
	    }
	    System.out.println();
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int pick;
		실습4_5원형큐 cq = new 실습4_5원형큐();
		while (true) {

			System.out.println("1. Push   2. Pop   3. Clear   4. Print");
			System.out.print("명령을 선택해주세요. => ");
			pick = sc.nextInt();
			Item result = null;
			switch (pick) {
			case 1:
				 int data = (int) Math.round(Math.random() * 10);
	                cq.push(new Item(data));
				//cq.push((int) Math.round(Math.random() * 10));
				break;
			case 2:
				result = cq.pop();
				System.out.println("pop: result = " + result);
				break;
			case 3:
				cq.clear();
				break;
			case 4:
				cq.print();
				System.out.println();
				break;
			default:
				continue;
			}
		}

	}
}
