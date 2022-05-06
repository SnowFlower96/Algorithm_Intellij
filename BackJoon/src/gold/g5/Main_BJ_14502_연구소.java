package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * WS
 * @author jhno96
 * @date 2022. 4. 8.
 */
public class Main_BJ_14502_연구소 {

	static int N, M, ans;
	static int[][] map;
	static List<int[]> virus, blank;
	static int[] walls;
	
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		virus = new ArrayList<>();
		blank = new ArrayList<>();
		walls = new int[3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new int[] { i, j });
				else if(map[i][j] == 0) blank.add(new int[] { i, j });
			}
		}
		
		ans = 0;
		solution(0, 0);
		System.out.println(ans);
	}
	
	// 조합으로 세개의 빈칸에 벽을 세우는 경우의 수 계산
	private static void solution(int wallCnt, int start) {
		// 세개의 빈칸에 벽을 세우면
		if(wallCnt == 3) {
			int area = getSafeArea();
			ans = Math.max(ans, area);
			return;
		}
		
		for(int i = start; i < blank.size(); i++) {			
			walls[wallCnt] = i;
			solution(wallCnt + 1, i + 1);
		}
	}

	// 바이러스를 확산하고, 안전한 영역의 수 구하기
	private static int getSafeArea() {
		int cnt = 0;
		
		// 지도 복제
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) arr[i] = map[i].clone();
		
		// 조합으로 구한 벽을 세울 후보에 벽을 세우기
		for(int i = 0; i < 3; i++) {
			int r = blank.get(walls[i])[0];
			int c = blank.get(walls[i])[1];
			
			arr[r][c] = 1;
		}
		
		// 바이러스 확산
		Queue<int[]> queue = new LinkedList<>();
		
		// 모든 바이러스 시작점에 대하여
		for(int[] pos : virus) {
			queue.offer(pos);
		}
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(arr[nr][nc] == 0) {
					arr[nr][nc] = 2;
					queue.offer(new int[] { nr, nc });
				}
			}
		}

		// 안전 영역의 수 구하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] == 0) cnt++;
			}
		}

		return cnt;
	}
	
}
