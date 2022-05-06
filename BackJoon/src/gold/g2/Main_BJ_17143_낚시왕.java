package gold.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17143_낚시왕 {

	static class Shark {
		
		int r, c;
		int speed, dir, size;
		
		public Shark(int r, int c, int speed, int dir, int size) {
			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}
		
	}
	
	static int R, C, M;
	static Shark[][] map;
	
	// 상, 하, 우, 좌
	static final int[] dr = { -1, 1, 0 ,0 };
	static final int[] dc = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R][C];		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(r, c, s, d, z);
		}
		
		int ans = solution();
		System.out.println(ans);
	}

	private static int solution() {
		int sum = 0;
		for(int i = 0; i < C; i++) {
			sum += capture(i);
			move();
		}
		return sum;
	}

	private static int capture(int i) {
		int size = 0;
		for(int r = 0; r < R; r++) {
			if(map[r][i] != null) {
				size = map[r][i].size;
				map[r][i] = null;
				return size;
			}
		}
		return size;
	}

	private static void move() {
		Shark[][] tempMap = new Shark[R][C];
		
		// 상어를 이동시켜 큐에 저장
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				// 현재 칸이 상어면
				if(map[i][j] != null) {
					int r = map[i][j].r;
					int c = map[i][j].c;
					int speed = map[i][j].speed;
					int dir = map[i][j].dir;
					int size = map[i][j].size;

					int length = dir < 2 ? R : C;
					int distance = speed % ((length - 1) * 2);

					for(int d = 0; d < distance; d++) {
						int nr = r + dr[dir];
						int nc = c + dc[dir];
						
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
							// 방향전환
							dir = dir == 0 || dir == 2 ? dir + 1 : dir - 1;
							// 후진
							r += dr[dir];
							c += dc[dir];
							continue;
						}
						r = nr;
						c = nc;
					}
					
					// 현재 상어가 들어갈 곳이 비어있는 경우
					if(tempMap[r][c] == null) tempMap[r][c] = new Shark(r, c, speed, dir, size);
					// 현재 상어가 들어갈 곳에 상어가 있는 경우 - 크기 비교
					else {
						// 현재 상어가 들어있는 상어보다 크면 - 상어 교체
						if(size > tempMap[r][c].size) tempMap[r][c] = new Shark(r, c, speed, dir, size);
					}
				}
			}
		}
		
		map = tempMap;
	}

	public static void print() {
		for(Shark[] row : map) {
			for(Shark col : row) System.out.printf("%d ", col != null ? 1 : 0);
			System.out.println();
		}
		System.out.println();
	}
}
