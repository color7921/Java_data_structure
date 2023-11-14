
package 자료구조8장;

/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름

	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject() {
		no = null;
		name = null;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next(); // 14행 no
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next(); // 15행 name
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		@Override
		public int compare(SimpleObject d1, SimpleObject d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		@Override
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node2 {
	SimpleObject data;
	Node2 link;

	public Node2(SimpleObject element) {
		data = element;
		link = null;
	}
}

class LinkedList2 {
	Node2 first;

	public LinkedList2() {
		first = null;
	}

	public int Delete(SimpleObject element, Comparator<SimpleObject> cc) // delete the element
	{
		Node2 current = first; // 첫 번째 노드에서 시작
		Node2 previous = null; // 이전 노드를 추적하기 위한 변수
		int deletedCount = 0; // 삭제된 요소의 수를 추적하기 위한 변수

		while (current != null) {
			// 현재 노드의 데이터를 가져옴
			SimpleObject currentData = current.data;

			// 현재 노드의 데이터와 삭제 대상 요소를 비교
			int comparisonResult = cc.compare(currentData, element);

			if (comparisonResult == 0) {
				// 삭제 대상 요소를 찾은 경우
				if (previous != null) {
					// 이전 노드의 링크를 현재 노드의 링크로 업데이트하여 삭제 수행
					previous.link = current.link;
				} else {
					// 첫 번째 노드를 삭제해야 하는 경우
					first = current.link;
				}

				deletedCount++;

				current = current.link;

			} else if (comparisonResult > 0) {
				// 검색 대상 요소보다 큰 요소를 찾은 경우 종료

				break;
			} else {
				// 계속 검색
				previous = current;
				current = current.link;
			}
		}

		return deletedCount;
	}

	// 전체 리스트를 순서대로 출력
	public void Show() {
		// 첫 번째 노드에서 시작
		Node2 current = first;

		System.out.println("연결 리스트 내용:");

		while (current != null) {
			// 현재 노드에 저장된 SimpleObject의 내용을 출력
			System.out.println(current.data.toString());

			// 다음 노드로 이동
			current = current.link;
		}
	}

	public void Add(SimpleObject element, Comparator<SimpleObject> cc) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		// 새 노드 생성
		Node2 newNode = new Node2(element);

		// 리스트가 비어 있거나 새 노드를 처음에 삽입해야 할 경우
		// 새 노드를 삽입할 올바른 위치를 찾을 때까지 반복
		if (first == null || cc.compare(element, first.data) <= 0) {
			newNode.link = first;
			first = newNode;
			return;
		}

		// 요소를 비교하면서 연결 리스트를 탐색
		Node2 current = first;
		while (current.link != null && cc.compare(element, current.link.data) > 0) {
			current = current.link;
		}

		// 새 노드를 연결 리스트의 올바른 위치에 삽입하기 위해 link 참조를 조정
		newNode.link = current.link;
		current.link = newNode;

	}

	public boolean Search(SimpleObject element, Comparator<SimpleObject> cc) { // 전체 리스트를 순서대로 출력한다.

		Node2 current = first;

		while (current != null) {
			// 현재 노드의 데이터를 가져오기
			SimpleObject currentData = current.data;

			// 현재 노드의 데이터와 검색 대상 요소를 비교하기
			int comparisonResult = cc.compare(currentData, element);

			if (comparisonResult == 0) {
				// 검색 대상 요소를 찾은 경우
				return true;
			} else if (comparisonResult > 0) {
				// 검색 대상 요소보다 큰 요소를 찾은 경우

				return false;
			}
			current = current.link;
		}
		return false;
	}
}

public class 실습8_2객체연결리스트_test {

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
		Menu menu; // 메뉴
		System.out.println("Linked List");
		LinkedList2 l = new LinkedList2();
		Scanner sc = new Scanner(System.in);
		SimpleObject data;

		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				data = new SimpleObject();
				data.scanData("입력", 3);
				l.Add(data, SimpleObject.NO_ORDER);
				break;
			case Delete: // 머리 노드 삭제
				data = new SimpleObject();
				data.scanData("삭제", SimpleObject.NO); // 삭제할때는 번호만 입력받음 (1만)
				int num = l.Delete(data, SimpleObject.NO_ORDER);
				System.out.println("삭제된 데이터 성공은 " + num);
				break;
			case Show: // 꼬리 노드 삭제
				l.Show();
				break;
			case Search: // 회원 번호 검색
				data = new SimpleObject();
				data.scanData("탐색", SimpleObject.NO);
				boolean result = l.Search(data, SimpleObject.NO_ORDER);
				if (result)
					System.out.println("검색 성공 = " + result);
				else
					System.out.println("검색 실패 = " + result);
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
