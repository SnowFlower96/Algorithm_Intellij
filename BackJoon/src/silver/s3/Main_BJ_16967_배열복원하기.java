package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author jhno96
 * @date 2022. 4. 20.
 */
public class Main_BJ_16967_배열복원하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		
		int[][] B = new int[H + X][W + Y];
		for(int i = 0; i < H + X; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W + Y; j++) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		int[][] A = solution(H, W, X, Y, B);
		for(int[] row : A) {
			for(int el : row) sb.append(el).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int[][] solution(int H, int W, int X, int Y, int[][] B) {
		int[][] A = new int[H][W];
		
		// 겹치지 않는 부분 상단
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < W; j++) {
				A[i][j] = B[i][j];
			}
		}
		
		// 겹치지 않는 부분 좌측
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < Y; j++) {
				A[i][j] = B[i][j];
			}
		}
		
		// 겹치는 부분
		for(int i = X; i < H; i++) {
			for(int j = Y; j < W; j++) {
				A[i][j] = B[i][j] - A[i-X][j-Y];
			}
		}
		
		return A;
	}

}
