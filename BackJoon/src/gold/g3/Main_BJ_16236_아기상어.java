package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16236_아기상어 {

	static class Shark {
		int r, c;
		int size;
		int eatCnt;
		int moveCnt = 0;

		public Shark(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.eatCnt = 0;
		}

		public Integer[] getPoint() {
			return new Integer[] {r, c};
		}
		
		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", size=" + size + "]";
		}
		
		public void eat(Point fish) {
			if(++eatCnt == size) {
				size++;
				eatCnt = 0;
			}
			
			this.r = fish.r;
			this.c = fish.c;
			
			moveCnt += fish.distance;
		}
	}
	
	static class Point {
		int r, c, distance;

		public Point(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
		
	}
	
	// 상, 좌, 우, 하
	static final int[] dr = {-1, 0, 0, 1};
	static final int[] dc = {0, -1, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 값 입력
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		Shark shark = new Shark(0, 0, 2);  // 시작점
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int item = Integer.parseInt(st.nextToken());
				// 상어이면 시작점 위치 저장
				if(item == 9) {
					shark.r = i;
					shark.c = j;
				} 
				else if(item >= 0) {  // 물고기이면
					map[i][j] = item;
				}
			}
		}
		
		while(true) {			
			Point fish = getFish(N, map, shark);
			if(fish == null) break;
			shark.eat(fish);
			map[fish.r][fish.c] = 0; 
		}
		
		System.out.println(shark.moveCnt);
	}
	
	// 현재 위치에서 bfs
	private static Point getFish(int N, int[][] map, Shark shark) {
		PriorityQueue<Point> fishes = new PriorityQueue<>((o1, o2) -> {
			int diff1 = o1.r - o2.r;  // 행 오름차순
			int diff2 = o1.c - o2.c;  // 열 오름차
			
			return diff1 != 0 ? diff1 : diff2;
		});
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.offer(new Point(shark.r, shark.c, 0));
		visited[shark.r][shark.c] = true;
		
		int minDis = Integer.MIN_VALUE;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(minDis == cur.distance) break;
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
								
				// 경계밖, 방문, 물고기가 더 클때 continue
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] > shark.size) continue;
				
				// 이동만 가능
				if(shark.size == map[nr][nc] || map[nr][nc] == 0) {
					queue.offer(new Point(nr, nc, cur.distance + 1));
					visited[nr][nc] = true;
				}					
				
				// 먹을 수 있는 물고기일 때
				else if(shark.size > map[nr][nc]) {
					fishes.offer(new Point(nr, nc, cur.distance + 1));
					visited[nr][nc] = true;
					minDis = cur.distance + 1;
				}
			}
		}

		return fishes.poll();
	}

}
