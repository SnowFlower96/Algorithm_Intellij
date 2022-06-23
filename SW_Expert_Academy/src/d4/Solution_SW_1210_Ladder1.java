package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_1210_Ladder1 {

	// 좌, 우, 하
	static final int[] dr = { 0, 0, 1 };
	static final int[] dc = { -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();

			// 사다리 정보 저장
			int[][] ladder = new int[100][100];
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 출발지별로 출발
			for (int i = 0; i < 100; i++) {
				// 출발지가 아니면
				if (ladder[0][i] != 1)
					continue;
				
				// 출발지이면
				int[] pts = { 0, i }; // 행의 번호
				int idx = 2;
				// 맨 아래에 도달할 때까지
				while (pts[0] != 99) {
					idx = calcDir(ladder, pts, idx);
					pts[0] += dr[idx];
					pts[1] += dc[idx];
					
				}
				
				// X 표시에 도달
				if (ladder[pts[0]][pts[1]] == 2) {
					sb.append("#").append(tc).append(" ").append(i).append("\n");
					break;
				}
			}
		}

		System.out.println(sb.toString());

		br.close();
	}

	/**
	 * 다음이동할 방향 반환
	 * 
	 * @param ladder 전체 사다리
	 * @param pts    현재의 좌표
	 * @param dir    이전의 방향
	 * @return dr, dc의 인덱스
	 */
	public static int calcDir(int[][] ladder, int[] pts, int dir) {
		// 아래로 이동하고 있었을 때
		if (dir == 2) {
			// 좌, 우 체크
			for (int i = 0; i < 2; i++) {
				int next_r = pts[0] + dr[i];
				int next_c = pts[1] + dc[i];

				// 경계체크
				if (next_r >= 0 && next_r < 100 && next_c >= 0 && next_c < 100) {
					if (ladder[next_r][next_c] == 1) {
						dir = i;
						break;
					}
				}
			}
		}
		// 좌측 혹은 우측으로 이동하고 있었을 때
		else {
			// 경계에 위치할 때
			if(pts[0] == 0 || pts[0] == 99 || pts[1] == 0 || pts[1] == 99)
				return 2;
			
			// 다음 좌표에 사다리가 있는지 확인
			int next_r = pts[0] + dr[dir];
			int next_c = pts[1] + dc[dir];

			// 경계체크
			if (next_r >= 0 && next_r < 100 && next_c >= 0 && next_c < 100) {
				// 다음 좌표에 사다리가 없으면 방향을 아래로
				if (ladder[next_r][next_c] == 0) {
					dir = 2;
				}
			}
		}
		
		return dir;
	}

}
