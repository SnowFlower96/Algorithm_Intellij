package gold.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1194_달이차오른다가자 {

	static class Point {
		int r, c, key, move;

		public Point() { }
		public Point(int r, int c, int key, int move) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.move = move;
		}
		
		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", key=" + key + ", move=" + move + "]";
		}

	}
	
	static int N, M;
	static char[][] maze;
	static Point pos;  // 민식이의 위치
	
	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				maze[i][j] = input[j];
				if(input[j] == '0') pos = new Point(i, j, 0, 0);  // 민식이의 위치
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][][] isVisited = new boolean[1<<6][N][M];
		
		queue.offer(pos);
		
		// 현재 갈 수 있는 곳의 최단거리 구하기
		while(!queue.isEmpty()) {
			Point cur = queue.poll();

			// 현재칸이 출구면 종료
			if(maze[cur.r][cur.c] == '1') return cur.move;
			
			// 현재의 키 조합으로 방문했었으면 다음 큐 탐색
			if(isVisited[cur.key][cur.r][cur.c]) continue;

			// 현재의 키 조합의 방문 처리
			isVisited[cur.key][cur.r][cur.c] = true;
			
			// 사방탐색
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				// 경계, 벽, 방문 체크
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || maze[nr][nc] == '#' || isVisited[cur.key][nr][nc]) continue;
				
				// 문을 만났을 때
				if(maze[nr][nc] >= 'A' && maze[nr][nc] <= 'F') {
					int door = maze[nr][nc] - 'A';
					// 현재의 키로 문을 통과할 수 있을 때
					if((cur.key & 1 << door) != 0) {
						queue.offer(new Point(nr, nc, cur.key, cur.move + 1));
					}
				}
				
				// 열쇠를 만났을 때
				else if(maze[nr][nc] >= 'a' && maze[nr][nc] <= 'f') {
					// 이미 열쇠에 도달했으면 pass
					int ch = maze[nr][nc] - 'a';
					queue.offer(new Point(nr, nc, cur.key | 1 << ch, cur.move + 1));
				}
				
				// 빈칸 만날 때
				else {
					queue.offer(new Point(nr, nc, cur.key, cur.move + 1));
				}
			}
		}

		return -1;
	}
	
}
