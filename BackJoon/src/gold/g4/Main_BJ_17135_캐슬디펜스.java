package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17135_캐슬디펜스 {
	
	static class Pos {
	
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int N, M, D, ans;
	static int[][] map, mapCopy;
	static int[] archers;
	
	// 좌, 우, 상
	static final int[] dr = { 0, -1, 0 };
	static final int[] dc = { -1, 0, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		archers = new int[3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		combination(0, 0);
		
		System.out.println(ans);
	}

	// 궁수 위치를 놓을 조합 생성
	private static void combination(int cnt, int start) {
		if(cnt == 3) {
			int result = process();
			ans = Math.max(ans, result);
//			System.out.println(Arrays.toString(archers) + " " + result);
			return;
		}
		
		for(int i = start; i < M; i++) {
			archers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}
	
	private static int process() {
		int cnt = 0;
		
		// 맵 복사
		mapCopy = new int[N][M];
		for(int i = 0; i < N; i++) {
			mapCopy[i] = map[i].clone();
		}
		
		for(int i = 0; i < N; i++) {
			// 궁수 타깃 계산 및 사살
			cnt += shoot();
			// 몬스터 이동
			moveMonsters();
		}
		
		// 사살된 몬스터 숫자 반환
		return cnt;
	}

	private static int shoot() {
		List<Pos> targets = new ArrayList<>();
		int cnt = 0;
		
		outer : for(int col : archers) {
			boolean[][] isVisited = new boolean[N][M];
			Queue<Pos> queue = new LinkedList<>();
			
			queue.offer(new Pos(N - 1, col));
			isVisited[N - 1][col] = true;
			
			int depth = 1;
			while(!queue.isEmpty()) {
				int size = queue.size();
				
				while(size-- > 0) {
					Pos cur = queue.poll();
					int r = cur.r;
					int c = cur.c;
					
					if(mapCopy[r][c] == 1) {
						targets.add(new Pos(r, c));
						continue outer;
					}
					
					for(int d = 0; d < 3; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || isVisited[nr][nc] || depth + 1 > D) continue;
						
						queue.offer(new Pos(nr, nc));
						isVisited[nr][nc] = true;
					}
				}				
				depth++;
			}
		}
		
		for(Pos p : targets) {
			if(mapCopy[p.r][p.c] == 1) {
				mapCopy[p.r][p.c] = 0;
				cnt++;				
			}
		}

		return cnt;
	}

	private static void moveMonsters() {
		for(int i = N - 2; i >= 0; i-- ) {
			mapCopy[i + 1] = mapCopy[i];
		}
		mapCopy[0] = new int[M];
	}
	
}
