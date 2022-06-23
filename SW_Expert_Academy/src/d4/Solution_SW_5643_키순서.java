package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * HW
 * @author jhno96
 * @date 2022. 4. 6.
 */
public class Solution_SW_5643_키순서 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_5643.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			int[][] matrix = new int[N + 1][N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				matrix[a][b] = 1;
			}
						
			int ans = 0;
			for(int i = 1; i <= N; i++) {
				int tallerCnt = getCnt(N, matrix, i, true);
				int smallerCnt = getCnt(N, matrix, i, false);
				
				if(tallerCnt + smallerCnt == N - 1) ans++;
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	// 큰 사람을 구하려면 flag = true, 작은 사람을 구하려면 flag = false
	private static int getCnt(int N, int[][] matrix, int idx, boolean flag) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] isVisited = new boolean[N + 1];
		
		queue.offer(idx);
		isVisited[idx] = true;
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			cnt++;
			int cur = queue.poll();
			
			for(int i = 1; i <= N; i++) {
				int temp = flag ? matrix[cur][i] : matrix[i][cur];
				if(!isVisited[i] && temp != 0) {
					isVisited[i] = true;
					queue.offer(i);
				}
			}
		}
		
		return cnt - 1;
	}


}
