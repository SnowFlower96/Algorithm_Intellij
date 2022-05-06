package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_15683_감시 {
	
	// 우, 하, 좌, 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	// cctv 객체
	static class CCTV {
		int[] dir;
		int r;
		int c;
		
		public CCTV(int type, int r, int c) {
			super();
			this.r = r;
			this.c = c;
			setDir(type);
		}

		// cctv의 방향 배열 저장
		private void setDir(int type) {
			if(type == 1) dir = new int[] { 0 };
			else if(type == 2) dir = new int[] { 0, 2 };
			else if(type == 3) dir = new int[] { 0, 3 };
			else if(type == 4) dir = new int[] { 0, 2, 3 };
			else if(type == 5) dir = new int[] { 0, 1, 2, 3 };
		}
		
		// 시계방향으로 90도 회전
		public void rotate() {
			for(int i = 0; i < dir.length; i++) {
				dir[i] = (dir[i] + 1) % 4;
			}
		}

		@Override
		public String toString() {
			return "CCTV [dir=" + Arrays.toString(dir) + ", r=" + r + ", c=" + c + "]";
		}
	}
	
	static int N, M, ans;
	static int[][] office;
	static ArrayList<CCTV> cctv;
	
	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사무실의 크기 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	
		
		// 초기화
		ans = Integer.MAX_VALUE;
		office = new int[N][M];
		cctv = new ArrayList<CCTV>();
		
		// 사무실 환경 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				office[i][j] = val;
				// CCTV이면 객체 배열에 추가
				if(val >= 1 && val < 6) {
					cctv.add(new CCTV(val, i, j));
				}
			}
		}
			
		solution(0);
		System.out.println(ans);
		
		br.close();
	}

	/**
	 * 최소 사각지대 계산
	 * @param cnt : cctv 배열에서의 인덱스
	 */
	public static void solution(int cnt) {
		int blind = getBlind();  // 현재 사각지대 계산
		
		// cctv 작업이 모두 끝나면하면
		if(cnt == cctv.size()) {
			ans = Math.min(ans, blind);
			return;
		}
		
		// 4방향으로 회전
		for(int i = 0; i < 4; i++) {
			solution(cnt + 1);  // 다음 cctv 호출
			cctv.get(cnt).rotate();
		}
	}
	
	/**
	 * 현재 카메라의 방향에 따른사각지대 계산
	 * 벽면은 감시가 가능하지 않지만 사각지대도 아니므로 감시가능 구역으로 true
	 * @return 사각지대
	 */
	public static int getBlind() {
		boolean[][] isSurvailable = new boolean[N][M];
		
		// 벽은 감시 불가능하지만 사각지대는 아니므로 true
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(office[i][j] == 6) {
					isSurvailable[i][j] = true;
				}
			}
		}
		
		// cctv 별로 감시 가능한 장소 표기(감시 가능 : true)
		for(int i = 0; i < cctv.size(); i++) {
			CCTV c = cctv.get(i);
			// cctv의 각 방향별로
			for(int j = 0; j < c.dir.length; j++) {
				int idx = 0;
				while(true) {
					int nr = c.r + dr[c.dir[j]] * idx;
					int nc = c.c + dc[c.dir[j]] * idx;
					
					// 경계 밖이면 break
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || office[nr][nc] == 6) {
						break;
					}
					
					// 감시 가능
					isSurvailable[nr][nc] = true;
					
					
					idx++;
				}
			}
		}

		// 감시 불가능한 장소 카운트
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!isSurvailable[i][j]) cnt++;
			}
		}
		
		return cnt;
	}
	
}
