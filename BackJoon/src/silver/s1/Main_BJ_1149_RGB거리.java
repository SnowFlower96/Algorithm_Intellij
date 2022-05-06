package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 과제
 * @author jhno96
 * @date 2022. 3. 31.
 */

public class Main_BJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][3];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 두번째 차원은 각 RGB로 했을 때의 최소값
		int[][] table = new int[N + 1][3];
		
		for(int i = 1; i <= N; i++) {
			// 더할 RGB값은 이전에 중복되지 않은 값 중 최소값
			table[i][0] = Math.min(table[i-1][1], table[i-1][2]) + arr[i][0];
			table[i][1] = Math.min(table[i-1][0], table[i-1][2]) + arr[i][1];
			table[i][2] = Math.min(table[i-1][0], table[i-1][1]) + arr[i][2];
		}	
		
		int result = Math.min(table[N][0], Math.min(table[N][1], table[N][2]));
		System.out.println(result);
	}

}
