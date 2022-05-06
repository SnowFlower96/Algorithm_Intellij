package gold.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_3109_빵집 {

	static int R, C, ans;
	static int[][] map;

	// 우상, 우, 우하
	static int[] dr = { -1, 0, 1 };
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C]; // 0 : 빈칸, -1 : 건물, 1 : 지나간 자리

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j) == '.' ? 0 : -1;
			}
		}
		
		// 각 행마다
		for(int i = 0; i < R; i++) {
			// 마지막까지 파이프를 연결할 수 있으면
			if(isAvailable(i, 0)) ans++;
		}
		
		System.out.println(ans);
		br.close();
	}

	// 해당 위치에서 위치로 파이프를 연결할 수 있는지 확인
	private static boolean isAvailable(int r, int c) {
		// 현재 위치에 파이프 설치
		map[r][c] = -1;
		
		// 마지막에 도달하면 true 리턴
		if(c == C - 1) return true;
		
		for (int d = 0; d < 3; d++) {
			if (isIn(r + dr[d]) && map[r + dr[d]][c + 1] == 0) { // 경계 내에 있고 파이프를 연결할 공간 확인
				// 재귀 호출로 다음 위치로 이동할 수 있는지 확인
				if(isAvailable(r + dr[d], c + 1)) return true;
			}
		}

		return false;
	}

	// 경계체크, 우측으로만 이동하므로 행의 값만 확인
	private static boolean isIn(int r) {
		if (0 <= r && r < R)
			return true;

		return false;
	}

}
