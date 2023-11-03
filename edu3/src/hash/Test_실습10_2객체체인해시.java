package 자료구조10장;


import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;
	String no; // 회원번호
	String name; // 이름

	void scanData(String message, int attribute) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message + "할 데이터를 입력하세요:");

        if ((attribute & NO) != 0) {
            System.out.print("회원번호: ");
            no = scanner.next();
        }

        if ((attribute & NAME) != 0) {
            System.out.print("이름: ");
            name = scanner.next();
        }
    }

    // NO_ORDER 상수 추가
    static final Comparator<SimpleObject5> NO_ORDER = new Comparator<SimpleObject5>() {
        @Override
        public int compare(SimpleObject5 o1, SimpleObject5 o2) {
            // 회원번호를 기준으로 비교
            return o1.no.compareTo(o2.no);
        }
    };
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		// --- 생성자(constructor) ---//

		
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

	
	
//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		 size = capacity; // size 변수를 제공된 capacity로 초기화
		    table = new Node2[size]; // 해시 테이블용 배열 생성
		}
	//ChainHash5 hashTable = new ChainHash5(10);

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		 int hashValue = hashFunction(st); // 해시 값 얻기

		    // 계산된 인덱스의 해시 테이블이 비어있으면 요소가 존재하지 않음
		    if (table[hashValue] == null) {
		        return 0; // 검색 데이터가 없음을 나타냄
		    } else {
		        // 연결 리스트를 훑어가며 요소 검색
		        Node2 currentNode = table[hashValue];
		        while (currentNode != null) {
		            if (c.compare(currentNode.data, st) == 0) {
		                return 1; // 검색 데이터가 존재함을 나타냄
		            }
		            currentNode = currentNode.next;
		        }
		        return 0; // 검색 데이터가 없음을 나타냄
		    }
		}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		 int hashValue = hashFunction(st); // 해시 값 얻기 (해시 함수를 구현해야 함)

		    // 만약 계산된 인덱스의 해시 테이블이 비어 있다면, 새로운 노드를 만들고 데이터를 추가
		    if (table[hashValue] == null) {
		        table[hashValue] = new Node2();
		        table[hashValue].data = st;
		        size++;
		        return 0; // 성공적으로 추가됨
		    } else {
		        // 인덱스에 이미 데이터가 있다면 연결 리스트를 훑어가며 중복을 확인
		        Node2 currentNode = table[hashValue];
		        while (currentNode != null) {
		            if (c.compare(currentNode.data, st) == 0) {
		                return 1; // 중복된 데이터가 있으면 1을 반환하여 실패를 나타냄
		            }
		            if (currentNode.next == null) {
		                break; // 연결 리스트의 끝에 도달했음
		            }
		            currentNode = currentNode.next;
		        }

		        // 연결 리스트의 끝에 새 데이터를 추가
		        currentNode.next = new Node2();
		        currentNode.next.data = st;
		        size++;
		        return 0; // 성공적으로 추가됨
		    }
		}

		// 해시 함수를 구현하여 해시 값을 계산
		private int hashFunction(SimpleObject5 st) {
		    // 데이터의 특성에 기반하여 직접 해시 함수를 구현해야 합니다.
		    // 간단히 st.hashCode() % size를 사용하여 기본 해시 함수를 구현할 수 있습니다.
		    return Math.abs(st.hashCode() % size);
		}

//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		 int hashValue = hashFunction(st); // 해시 값 얻기

		    // 계산된 인덱스의 해시 테이블이 비어있으면 요소가 존재하지 않음
		    if (table[hashValue] == null) {
		        return 0; // 삭제할 데이터가 없음을 나타냄
		    } else {
		        Node2 current = table[hashValue];
		        Node2 prev = null;

		        // 연결 리스트를 훑어가며 요소 검색
		        while (current != null) {
		            if (c.compare(current.data, st) == 0) {
		                // 찾은 요소를 삭제
		                if (prev == null) {
		                    // 첫 번째 노드를 삭제해야 할 경우
		                    table[hashValue] = current.next;
		                } else {
		                    // 중간이나 끝에 있는 노드를 삭제해야 할 경우
		                    prev.next = current.next;
		                }
		                size--;
		                return 1; // 삭제 성공
		            }

		            prev = current;
		            current = current.next;
		        }
		        return 0; // 삭제할 데이터가 없음을 나타냄
		    }
		}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < table.length; i++) {
	        System.out.printf("%d번째 리스트: ", i);

	        // 해당 인덱스의 연결 리스트를 훑어가며 데이터 출력
	        Node2 currentNode = table[i];
	        while (currentNode != null) {
	            System.out.printf("[%s, %s] ", currentNode.data.no, currentNode.data.name);
	            currentNode = currentNode.next;
	        }

	        System.out.println(); // 줄 바꿈
	    }
	}
	
	
}

public class Test_실습10_2객체체인해시 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

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
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
