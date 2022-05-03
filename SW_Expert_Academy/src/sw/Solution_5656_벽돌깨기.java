package sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * WorkShop
 * @author jhno96
 * @date 2022. 4. 5.
 */
public class Solution_5656_벽돌깨기 {

	static class Point {
		
		int r, c, num;

		public Point(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", num=" + num + "]";
		}
		
	}
	
	static int N, W, H, ans;
	static int[][] map;
	static int[] order;
	
	// 시계방향
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_5656.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			order = new int[N];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = Integer.MAX_VALUE;
			permutation(0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int[][] deepCopy(){
		int[][] copy = new int[H][W];
		
		for(int i = 0; i < H; i++) {
			System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
		}
		
		return copy;
	}
	
	private static void permutation(int cnt) {
		if(cnt == N) {
			int[][] copy = deepCopy();
			int blockCnt = shoot(copy);
			ans = Math.min(ans, blockCnt);
			return;
		}
	
		for(int i = 0; i < W; i++) {
			order[cnt] = i;
			permutation(cnt + 1);
		}
	}

	// 벽돌 깨기
	private static int shoot(int[][] arr) {		
		// 각 순서별로 벽돌 깨기
		for(int i = 0; i < N; i++) {
			int r = 0;
			int c = order[i];
			
			// 맨 위에 있는 벽돌 찾기
			while(r < H - 1) {
				if(arr[r][c] != 0) break;
				r++;
			}
			if(r == H) continue;
			
			search(arr, r, c);
			arange(arr);
		}
		
		return countBlock(arr);
	}


	private static void search(int[][] arr, int r, int c) {
		// 처음 닿은 칸이 1이면 해당 칸만 영향을 받으므로 해당 칸만 0으로 변경
		if(arr[r][c] == 1) {
			arr[r][c] = 0;
			return;
		}
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[H][W];
		
		queue.offer(new Point(r, c, arr[r][c]));
		isVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			arr[cur.r][cur.c] = 0;
			for(int delta = 1; delta < cur.num; delta++) {
				for(int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d] * delta;
					int nc = cur.c + dc[d] * delta;
					
					// 경계, 방문 체크
					if(nr < 0 || nr >= H || nc < 0 || nc >= W || isVisited[nr][nc]) continue;
					
					if(arr[nr][nc] > 0) {
						isVisited[nr][nc] = true;
						queue.offer(new Point(nr, nc, arr[nr][nc]));
					}
				}
			}
		}
	}
	
	// 빈 공간 제거
	private static void arange(int[][] arr) {
		// 좌측바닥에서부터
		for(int c = 0; c < W; c++) {  // 열
			int r = H - 1;
			while(r > 0) {  //행
				if(arr[r][c] == 0) {
					int nr = r - 1;
					while(nr > 0 && arr[nr][c] == 0) nr--;
					
					arr[r][c] = arr[nr][c];
					arr[nr][c] = 0;
				}
				r--;
			}
		}
	}

	// 블록 개수 계산
	private static int countBlock(int[][] arr) {
		int cnt = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++){
				if(arr[i][j] > 0) cnt++;
			}
		}
		
		return cnt;
	}
	
}
