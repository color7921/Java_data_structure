package 자료구조6장;
import java.util.Scanner;

public class 실습6_10QuickSortStack {
    // --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    // --- 퀵 정렬(비재귀 버전) ---//
    static void quickSort(int[] a, int left, int right) {
        IntStack lstack = new IntStack(right - left + 1);
        IntStack rstack = new IntStack(right - left + 1);

        lstack.push(left);
        rstack.push(right);

        while (!lstack.isEmpty()) {
            int pl = left = lstack.pop(); // 왼쪽 커서
            int pr = right = rstack.pop(); // 오른쪽 커서
            int x = a[(pl + pr) / 2]; // 피벗은 가운데 요소

            do {
                while (a[pl] < x)
                    pl++;
                while (a[pr] > x)
                    pr--;
                if (pl <= pr)
                    swap(a, pl++, pr--);
            } while (pl <= pr);

            if (left < pr) {
                lstack.push(left); // 왼쪽 그룹 범위의
                rstack.push(pr); // 인덱스를 푸시
            }
            if (pl < right) {
                lstack.push(pl); // 오른쪽 그룹 범위의
                rstack.push(right); // 인덱스를 푸시
            }
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("퀵 정렬");
        System.out.print("요솟수: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            double d = Math.random();
            x[i] = (int) (d * 20);
        }
        showData(x);

        quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

        System.out.println("오름차순으로 정렬했습니다.");
        showData(x);
    }

    static void showData(int[] d) {
        System.out.println();
        for (int i = 0; i < d.length; i++)
            System.out.print(d[i] + " ");
    }
}

class IntStack {
    private int[] data; // 스택용 배열
    private int capacity; // 스택의 크기
    private int top; // 스택 포인터

    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() {
        }
    }

    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() {
        }

        public OverflowIntStackException(int maxlen) {
            top = 0;
            capacity = maxlen;
            try {
                data = new int[capacity]; // 스택 본체용 배열을 생성
            } catch (OutOfMemoryError e) { // 생성할 수 없음
                capacity = 0;
            }
        }
    }

    public IntStack(int maxlen) {
        top = 0;
        capacity = maxlen;
        try {
            data = new int[capacity]; // 스택 본체용 배열을 생성
        } catch (OutOfMemoryError e) { // 생성할 수 없음
            capacity = 0;
        }
    }

    public void push(int x) throws OverflowIntStackException {
        if (top >= capacity) // 스택이 가득 참
            throw new OverflowIntStackException();
        data[top++] = x;
    }

    public int pop() throws EmptyIntStackException {
        if (top <= 0) // 스택이 빔
            throw new EmptyIntStackException();
        return data[--top];
    }

    public boolean isEmpty() {
        return top <= 0;
    }
}