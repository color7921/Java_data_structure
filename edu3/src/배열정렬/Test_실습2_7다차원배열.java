package 자료구조2장기본자료구조;

//3번 실습
//교재 62 - 두 배열의 비교
//교재 79 - 다차원배열
import java.util.Random;
public class Test_실습2_7다차원배열 {
	public static void main(String[] args) {
		int [][]A = new int[2][3];
		int [][]B = new int[3][4];
		int [][]C = new int[2][4];
		int [][]D = new int[2][3];
		inputData(A);inputData(B);inputData(D);
		System.out.println("A[][]");
		showData(A);
		System.out.println("D[][]");
		showData(D);
		System.out.println();
		System.out.println("B[][]");
		showData(B);
		int [][]E = addMatrix(A,D);
		System.out.println("E[][]");
		showData(E);
		int [][]C1 = multiplyMatrix(A,B);
		
		int[][] F = transposeMatrix(A);
	        System.out.println("F[][] (Transposed A)");
	        showData(F);
	    }
	
	

//		
//		boolean result = equals(A, D);
//		System.out.println("equals(A,D) = " + result);

	static void inputData(int [][]data) {
		Random rd = new Random();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = rd.nextInt(10); 
				}
            }
	}
	static void showData(int[][]items) {
		for (int i = 0; i < items.length; i++) {
			 for (int j = 0; j < items[i].length; j++) {
	          System.out.print(items[i][j] + " ");
			}
			 System.out.println();
		}
	}
	static boolean equals(int[][]a, int[][]b) {
		 if (a.length != b.length || a[0].length != b[0].length) {
		        return false;  // 매트릭스가 다른 차원을 가지고 있다.
		    }

		    for (int i = 0; i < a.length; i++) {
		        for (int j = 0; j < a[0].length; j++) {
		            if (a[i][j] != b[i][j]) {
		                return false;  // 원소가 같지 않다.
		            }
		        }
		    }

		    return true;  // 매트릭스가 같다.
		}

	//}
	  static int[][] multiplyMatrix(int A[][], int B[][]) {
	    	System.out.println();
	    	//showMatrix(A);
	    	//showMatrix(B);
	        int E[][] = new int[A.length][B[0].length];
	        int sum = 0;
	        for (int i = 0; i < A.length; i++) {
	            for (int j = 0; j < B[0].length; j++) {
	                sum = 0;
	                for (int k = 0; k < A[0].length; k++) {
	                    sum += A[i][k] * B[k][j];
	                }
	                E[i][j] = sum;
	            }
	        }
	       // showMatrix(E);
			return E;
	    }

	  static int[][] transposeMatrix(int[][] matrix) {
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        int[][] result = new int[cols][rows];  
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                result[j][i] = matrix[i][j];  
	            }
	        }

	        return result;
	    }

	  
	static int[][] addMatrix(int [][]X, int[][]Y) {
		
		  int[][] result = new int[X.length][X[0].length];
	        for (int i = 0; i < X.length; i++) {
	            for (int j = 0; j < X[i].length; j++) {
	                result[i][j] = X[i][j] + Y[i][j];
	            }
	        }
	        return result;
	}

}
