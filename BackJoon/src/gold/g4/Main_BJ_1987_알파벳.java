package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1987_알파벳 {

	static int R, C, ans;
	static int[][] board;
	static boolean[] isVisited;
	
	// 상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 배열의 크기 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		isVisited = new boolean[26];
				
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = input.charAt(j) - 'A';
			}
		}

		solution(0, 0, 0);
		System.out.println(ans);
		
		br.close();
	}
	
	public static void solution(int r, int c, int cnt) {	
		// 현재 위치의 알파벳
		int idx = board[r][c];
		// 현재 위치가 밟았던 알파벳
		if(isVisited[idx]) {
			ans = Math.max(ans,  cnt);
			return;
		}
		
		// 현재 밟은 알파벳 추가
		isVisited[idx] = true;
		
		// 움직일 곳이 있으면
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 경계 확인
			if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
				solution(nr, nc, cnt + 1);
			}
		}
		
		// 현재 방문한 알파벳 제거
		isVisited[idx] = false;
	}
	
}
