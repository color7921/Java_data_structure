package 자료구조4장;

/*
 * Queue of ArrayList of Point
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" +"x = " + x + ", " + "y = " + y + ")";
	}

	
}

//int형 고정 길이 큐
class objectQueue {

//	private int[] que; // 큐용 배열 - 교재 버젼
	private List<Point> que;
	// private List<Integer> que; // 수정본
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException() {
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException() {
		}
	}

//--- 생성자(constructor) ---//
	public objectQueue(int maxlen) {
		// 구현
		que = new ArrayList<>(maxlen);
		capacity = maxlen;
		front = rear = num = 0;
	}

//--- 큐에 데이터를 인큐 ---//
	public Point enque(Point x) throws OverflowQueueException {
		// 구현
		if (isFull()) {
			throw new OverflowQueueException();
		}
		que.add(x);
		rear = (rear + 1) % capacity;
		num++;
		return x;
	}

//--- 큐에서 데이터를 디큐 ---//
	public Point deque() throws EmptyQueueException {
		// 구현
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		Point removedItem = que.get(front);
		front = (front + 1) % capacity;
		num--;

		return removedItem;
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public Point peek() throws EmptyQueueException {
		// 구현
		if (isEmpty()) {
			throw new EmptyQueueException();
		}

		return que.get(front);
	}

//--- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(Point x) {
		// 구현
		for (int i = 0; i < num; i++) {
			int index = (front + i) % capacity;
			if (que.get(index) == x) {
				return index;
			}
		}
		return -1;
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return num;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		// 구현
		if (isEmpty()) {
			System.out.println("큐가 비었습니다.");
			return;
		}

		System.out.print("큐 원소(rear의 앞부분): ");
		for (int i = 0; i < num; i++) {
			int index = (front + i) % capacity;
			System.out.print(que.get(index) + " ");
		}
		System.out.println();
	}
}

public class 실습4_4객체큐_리스트 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue s = new objectQueue(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {
			case 1: // 인큐
				System.out.print("데이터: ");
				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				p = new Point(rndx, rndy);
				try {
					s.enque(p);
				} catch (objectQueue.OverflowQueueException e) {
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = s.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (objectQueue.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = s.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				s.dump();
				break;
			}
		}
	}
}