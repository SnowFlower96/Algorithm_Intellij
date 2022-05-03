package sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * WS
 * @author jhno96
 * @date 2022. 4. 7.
 */
public class Solution_1249_보급로 {

	static class Point {
		
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_1249.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				char[] row = br.readLine().toCharArray();
				for(int j = 0; j < N; j++) {
					map[i][j] = row[j] - '0';
				}
			}
			
			sb.append("#").append(tc).append(" ").append(bfs(N, map)).append("\n");
//			sb.append("#").append(tc).append(" ").append(dijkstra(N, map, 0, 0)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs(int N, int[][] map) {
		int result = Integer.MAX_VALUE;
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		int[][] distance = new int[N][N];
		for(int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);
		
		queue.offer(new Point(0, 0));
		visit[0][0] = true;
		distance[0][0] = 0;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.r == N - 1 && cur.c == N - 1) result = Math.min(result, distance[cur.r][cur.c]);
			
			if(result <= distance[cur.r][cur.c]) continue;
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(!visit[nr][nc] || distance[nr][nc] > distance[cur.r][cur.c] + map[nr][nc]) {
					visit[nr][nc] = true;
					distance[nr][nc] = distance[cur.r][cur.c] + map[nr][nc];
					queue.offer(new Point(nr, nc));					
				}
			}
		}

		return result;
	}
	
	@SuppressWarnings("unused")
	private static int dijkstra(int N, int[][] map, int startR, int startC) {
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(minTime[i], Integer.MAX_VALUE);
		}
	
		PriorityQueue<int[]> pQueue = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		minTime[startR][startC] = 0;
		pQueue.offer(new int[] { startR, startC, minTime[startR][startC] });
		
		int r, c, minCost, nr, nc, current[];
		while(true) {
			current = pQueue.poll();  // pQueue 안의 정점 중 출발지에서 자신으로의 비용이 최소인 정점의 정보
			r = current[0];
			c = current[1];
			minCost = current[2];
			
			if(visited[r][c]) continue;
			
			visited[r][c] = true;
			
			if(r == N - 1 && c == N - 1) return minCost;  // 도착지라면 끝내기
			
			// 현 정점의 인접정점을 들여다보며 최소비용 갱신
			for (int d = 0; d < 4; d++) {
				nr = r + dr[d];
				nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N 
					// || visited[nr][nc]) 
					) continue;
				
				if(minTime[nr][nc] > minCost + map[nr][nc]) {
					minTime[nr][nc] = minCost + map[nr][nc];
					pQueue.offer(new int [] { nr, nc, minTime[nr][nc] });
				}
			}
		}

	}

}
