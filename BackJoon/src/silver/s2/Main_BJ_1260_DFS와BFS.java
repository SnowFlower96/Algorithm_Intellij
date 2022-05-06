package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1260_DFS와BFS {

	static int N, M, V;
	static StringBuilder sb = new StringBuilder();
	static boolean[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new boolean[N][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x-1][y-1] = true;
			graph[y-1][x-1] = true;
		}
		
		dfs(new boolean[N], V - 1);
		sb.append("\n");
		bfs(V - 1);
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static void dfs(boolean[] Visited, int idx) {
		Visited[idx] = true;
		sb.append(idx + 1).append(" ");
		
		for(int j = 0; j < N; j++) {
			if(!Visited[j] && graph[idx][j]) {
				dfs(Visited, j);
			}
		}
	}
	
	public static void bfs(int idx) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		queue.offer(idx);
		visited[idx] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			sb.append(cur + 1).append(" ");
			
			for(int j = 0; j < N; j++) {
				if(!visited[j] && graph[cur][j]) {
					queue.offer(j);
					visited[j] = true;
				}
			}
		}
	}

}
