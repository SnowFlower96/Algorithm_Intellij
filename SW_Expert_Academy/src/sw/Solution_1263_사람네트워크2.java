package sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * WS
 * @author jhno96
 * @date 2022. 4. 5.
 */
public class Solution_1263_사람네트워크2 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_1263.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] matrix = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && matrix[i][j] == 0) matrix[i][j] = Integer.MAX_VALUE / 2;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(floydWarshall(N, matrix)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int floydWarshall(int N, int[][] matrix) {
		for(int k = 0; k < N; k++) { // 모든 우회
			for(int i = 0; i < N; i++) {
				if(i == k) continue;
				for(int j = 0; j < N; j++) {
					if(i == j || k == j) continue;
					if(matrix[i][j] > matrix[i][k] + matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
		
		int[] CC = new int[N];
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				sum += matrix[i][j];
			}
			CC[i] = sum;
		}
		
		int result = Integer.MAX_VALUE;
		for(int cc : CC) result = Math.min(result, cc);

		return result;
	}
	
}
