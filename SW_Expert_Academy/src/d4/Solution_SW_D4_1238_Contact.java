package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW_D4_1238_Contact {

	static int N = 100;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());  // 데이터의 길이
			int S = Integer.parseInt(st.nextToken());  // 시작점 (1~100) 
			
			boolean[][] matrix = new boolean[N][N];  // 연락망
			boolean[] hasNumber = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < L / 2; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				matrix[x - 1][y - 1] = true;
				hasNumber[x - 1] = true;
				hasNumber[y - 1] = true;
			}						
			
			sb.append("#").append(tc).append(" ").append(bfs(matrix, S - 1, hasNumber)).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static int bfs(boolean[][] matrix, int idx, boolean[] hasNumber) {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[N];
		
		queue.offer(idx);
		visited[idx] = 1;
		int max = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i = 0; i < N; i++) {
				if(visited[i] == 0 && matrix[cur][i]) {
					queue.offer(i);
					visited[i] = visited[cur] + 1;
				}
			}
			max = visited[cur];
		}
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			if(visited[i] == max) {
				result = Math.max(result, i);
			}
		}
		return result + 1;
	}

}
