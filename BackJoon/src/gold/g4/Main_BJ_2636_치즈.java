package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_2636_치즈 {

	static int R, C, hour, lastCnt;
	static int[][] arr;
	static int cnt;
	static boolean[][] isAir;
	static boolean[][] isVisited;

	// 상 우 하 좌
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		isAir = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					cnt++;
			}
		}

		solution();
		System.out.println(hour);
		System.out.println(lastCnt);
	}

	private static void solution() {
		// 테두리는 공기처리
		// 행
		for (int i = 0; i < C; i++) {
			isAir[0][i] = true;
			isAir[R - 1][i] = true;
		}
		// 열
		for (int i = 0; i < R; i++) {
			isAir[i][0] = true;
			isAir[i][C - 1] = true;
		}

		// 다 녹을 때까지 반복
		while (cnt > 0) {
			isVisited = new boolean[R][C];  // bfs 방문 확인용 배열
			bfs(0, 0);  // 공기 탐색 bfs
			lastCnt = cnt;  // 녹기 전 치즈 개수
			melt();  // 녹이기
			hour++;  // 시간 추가
		}

	}

	// BFS 탐색으로 공기에 닿아 있는 빈공간 및 테두리 치즈 확인
	private static void bfs(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();

		isVisited[i][j] = true;
		queue.offer(new int[] { i, j });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			// 사방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];

				// 경계 체크
				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;

				if (isVisited[nr][nc])
					continue;

				// 방문 체크
				isVisited[nr][nc] = true;

				// 현재칸이 공기이면
				if (isAir[cur[0]][cur[1]]) {
					// 다음칸이 빈 공간이면
					if (arr[nr][nc] == 0) {
						isAir[nr][nc] = true;
						queue.offer(new int[] { nr, nc });
					}
					// 다음칸이 치즈 칸이면
					else {
						arr[nr][nc] = -1;
					}
				}
			}
		}
	}

	// 공기와 접촉된 치즈 녹이기
	private static void melt() {
		for (int i = 1; i < R - 1; i++) {
			for (int j = 1; j < C - 1; j++) {
				if (arr[i][j] == -1) {
					isAir[i][j] = true;
					arr[i][j] = 0;
					cnt--;
				}
			}
		}
	}

}
