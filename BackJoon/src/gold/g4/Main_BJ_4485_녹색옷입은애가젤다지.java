package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_4485_녹색옷입은애가젤다지 {

	static class Point implements Comparable<Point>{
		int r, c, weight;

		public Point(int r, int c, int weight) {
			super();
			this.r = r;
			this.c = c;
			this.weight = weight;
		}

		@Override
		public int compareTo(Point o) {
			return this.weight - o.weight;
		}
		
	}
	
	static StringBuilder sb;
	static int N;
	static int[][] map;
	static int[][] cost;
	
	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		sb = new StringBuilder();
		StringTokenizer st;
		
		int cnt = 0;
		while(++cnt > 0) {
			N = Integer.parseInt(br.readLine());
			
			// 입력이 0이면 종료
			if(N == 0) break;
			
			// 전역변수 초기화
			map = new int[N][N];
			cost = new int[N][N];
			for(int[] row : cost) {
				Arrays.fill(row, Integer.MAX_VALUE);				
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Dijkstra(cnt);
		}
		
		System.out.println(sb.toString());
		
	}

	private static void Dijkstra(int cnt) {		
		// 시작지점
		cost[0][0] = map[0][0];
		
		// 우선순위 큐
		PriorityQueue<Point> pQueue = new PriorityQueue<Point>();
		pQueue.offer(new Point(0, 0, map[0][0]));
		
		while(!pQueue.isEmpty()) {
			Point cur = pQueue.poll();
			
			// 사방 탐색
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				// 경계체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(cost[nr][nc] > cost[cur.r][cur.c]+ map[nr][nc] ) {
					cost[nr][nc] = cost[cur.r][cur.c] + map[nr][nc];
					pQueue.offer(new Point(nr, nc, cost[nr][nc]));
				}
			}
		}
		
		sb.append("Problem ").append(cnt).append(": ").append(cost[N - 1][N - 1]).append("\n");
	}

}
