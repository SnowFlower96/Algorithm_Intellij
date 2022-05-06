package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_7576_토마토 {

	static class Tomato {
		
		int r, c;

		public Tomato(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	// 사방탐색 - 상 하 좌 우
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	
	static int N, M;  // 상자의 크기
	static int day, tomatoCnt, tomatoTotal;  // 익는데 걸리는 시간, 현재 익은 토마토의 수, 전체 토마토의 수
	static int[][] box;  // 상자
	
	// bfs용 변수
	static Queue<Tomato> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		tomatoTotal = N * M;  // 예상가능한 총 토마토의 수
		
		// 상자 정보 입력
		for(int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				int val = Integer.parseInt(st.nextToken());
				box[i][j] = val;
				
				// 익은 토마토이면 큐에 추가 및 방문표시
				if(val == 1) {
					queue.offer(new Tomato(i, j));
					tomatoCnt++;  // 익은 토마토 수 증가
				}
				// 빈칸이면 방문할필요가 없으므로 방문표시
				else if(val == -1) {
					tomatoTotal--;  // 총 칸수에서 빈칸 제외
				}
			}
		}
		
		// 모든 토마토가 익어있지 않으면 모두 익을 때까지의 최소 날짜 계산
		if(tomatoCnt < tomatoTotal) {
			bfs();
		}
		
		System.out.println(tomatoCnt < tomatoTotal ? -1 : day);
		
		br.close();
	}

	private static void bfs() {
		while(!queue.isEmpty()) {
			// 현재 익은 토마토수가 총 토마토 수 일때
			if(tomatoCnt == tomatoTotal) break;
			
			// BFS의 레벨별로
			int qSize = queue.size();
			while(qSize-- > 0) {
				Tomato cur = queue.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					// 경계체크 및 빈칸 확인
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || box[nr][nc] == -1) continue;
					
					// 다음 이동할 토마토가 익지 않았을때
					if(box[nr][nc] == 0) {
						queue.offer(new Tomato(nr, nc));  // 다음에 방문할 곳 추가
						box[nr][nc] = 1;  // 익은 토마토로 변경
						tomatoCnt++;  // 익은 토마토 증가
					}
				}
			}
			// 하루 증가
			day++;
		}	
	}
	
}
