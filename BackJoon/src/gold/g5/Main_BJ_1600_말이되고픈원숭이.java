package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1600_말이되고픈원숭이 {

	static class Point {
		int r, c, chance, cnt;

		public Point(int r, int c, int chance, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.chance = chance;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", chance=" + chance + ", cnt=" + cnt + "]";
		}
	
	}
	
	static int K, W, H, ans;
	static int[][] map;
	static boolean[][][] isVisited;
	
	// 말의 이동방식
	static final int[] horseDr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static final int[] horseDc = { 1, 2, 2, 1, -1, -2, -2, -1 };
	
	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		isVisited = new boolean[H][W][K + 1];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		System.out.println(solution(0, 0));
	}

	private static boolean checkValid(int r, int c, int chance) {
		if(r >= 0 && r < H && c >= 0 && c < W && map[r][c] == 0 && !isVisited[r][c][chance]) return true;
		else return false;
	}
	
	private static int solution(int i, int j) {
		int result = -1;
		
		Queue<Point> queue = new LinkedList<>();
		
		isVisited[i][j][0] = true;
		queue.offer(new Point(i, j, 0, 0));
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			int chance = cur.chance;
			int cnt = cur.cnt;
			
			// 목적지에 도달하면
			if(r == H - 1 && c == W - 1) {
				return cnt;
			}
			
			// 말처럼 움직일 수 있을 때
			if(chance < K) {
				for(int d = 0; d < 8; d++) {
					int nr = r + horseDr[d];
					int nc = c + horseDc[d];
					
					// 경계 밖이거나 이미 방문했거나 장애물이면 pass
					if(!checkValid(nr, nc, chance + 1)) continue;

					isVisited[nr][nc][chance + 1] = true;
					queue.offer(new Point(nr, nc, chance + 1, cnt + 1));
				}
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 경계 밖이거나 이미 방문했거나 장애물이면 pass
				if(!checkValid(nr, nc, chance)) continue;

				isVisited[nr][nc][chance] = true;
				queue.offer(new Point(nr, nc, chance, cnt + 1));
			}
		}
		
		return result;
	}
		
}
