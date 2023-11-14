
package 자료구조8장;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 next;

	public Node1(int element) {
		data = element;
		next = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() { // 생성자
		first = null;
		// head 포인트인 first에 null을 넣음으로써 비어 있는 연결 리스트는
		// 머리 노드 자체가 아님을 표시함.
	}

	 public int Delete(int element) // delete the element
	 {

		 //리스트가 비어있는지 확인
		 if (first == null) {
		 //비어 있다면 -1 또는 다른값 반환
			 return -1;
		 }
		 //요소가 리스트의 첫 번째 노드인 경우, 첫 번째 노드를 건너뛰어 삭제
		 if (first.data == element) {
			 first = first.next;
			 return element;
		 }
		 //아닌 경우 삭제할 요소를 찾을때까지 리스트를 순회하면서 요소를 찾고 삭제
		 Node1 current = first;
		 Node1 previous = null;
		 
		 // 삭제할 요소를 찾으면 이전 노드의 next를 다음 노드로 설정하여 요소를 삭제
		 while(current != null) {
			 if(current.data == element) {
				 previous.next = current.next;
				 return element;
			 }
			 previous = current;
			 current = current.next;
		 }
		 //만약 요소를 찾지 못한 경우, -1 또는 다른 의미있는 값을 반환하여 삭제 실패를 나타냄
		 return -1;
	 }

	public void Show() { // 전체 리스트를 순서대로 출력한다.

		Node1 current = first;

		System.out.print("LinkedList: "  );

		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}

		System.out.println();

	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		Node1 tmp = new Node1(element);
		Node1 p = first, q = null;

		if (first == null || first.data > element) {
			tmp.next = first;
			first = tmp;
			return;
		}

		// 중복 값을 처리하지 않도록 수정
		while (p != null) {
			if (p.data == element) {
				return;
			}
			if (p.data < element) {
				q = p;
				p = p.next;
			} else {
				break;
			}
		}
		{
			tmp.next = p; // 10, 20, 30이 기존에 존재했는데 tmp가 25라면 20과 30 사이로 가야함. 그 과정을 처리한거.
			q.next = tmp; //
			// 5랑, 40을 insert 한 결과를 처리하는 코드 추가
		}
	}

	// 1. 일반적으로 중간에 삽입
	// 2. 처음, 마지막 삽입
	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.

		Node1 current = first;

		while (current != null) {
			if (current.data == data) {
				// 데이터가 리스트 안에 있으면 true 반환
				return true;
			}
			current = current.next;
		}

		// 데이터가 리스트 안에 없으면 false 반환
		return false;
	}
}

public class 실습8_1정수연결리스트_test {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			// 메세지값 읽어옴
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 생성자 호출
		Random rand = new Random();
		System.out.println("Linked List");
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int data = 0;

		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				data = rand.nextInt(20);
				// double d = Math.random();
				// data = (int) (d * 50);
				l.Add(data);
				break;
			case Delete: // 머리 노드 삭제
				data = sc.nextInt();
				int num = l.Delete(data);
				System.out.println("삭제된 데이터는 " + num);
				break;
			case Show: // 꼬리 노드 삭제
				l.Show();
				break;
			case Search: // 회원 번호 검색
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + "데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + "데이터가 존재합니다.");
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
