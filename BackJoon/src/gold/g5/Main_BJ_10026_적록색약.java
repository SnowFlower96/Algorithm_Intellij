package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_10026_적록색약 {

	// 상 하 좌 우
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	
	static int N;
	static char[][] picture;
	static int[][] district;
	static int curDistrict;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 그림 입력
		N = Integer.parseInt(br.readLine());
		picture = new char[N][N];
		for(int i = 0; i < N; i++) {
			picture[i] = br.readLine().toCharArray();
		}
				
		// 적록색약이 아닌 사람이 봤을 때
		curDistrict = 0;
		district = new int[N][N];  // 구역 초기화
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(district[i][j] == 0) {
					curDistrict++;
					setDist(i, j, picture[i][j], false);
				}
			}
		}
		sb.append(curDistrict).append(" ");
		
		// 적록색약인 사람이 봤을 때
		curDistrict = 0;
		district = new int[N][N];  // 구역 초기화
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(district[i][j] == 0) {
					curDistrict++;
					setDist(i, j, picture[i][j], true);
				}
			}
		}
		sb.append(curDistrict);
		
		System.out.println(sb.toString());
		
		br.close();
	}
	

	/**
	 * 좌표를 입력받아 구역 나누기
	 * @param r : 행 좌표
	 * @param c : 열 좌표
	 * @param color : 시작좌표의 색상
	 * @param isWeak : 적록색약인지
	 */
	public static void setDist(int r, int c, char color, boolean isWeak) {
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 경계 밖이거나 구역으로 정해졌 있을 때
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || district[nr][nc] > 0) continue;
			
			// 적록색약인 사람일 때
			if(isWeak) {
				// 시작 좌표의 색상이 파란색일 때
				if(color == 'B') {
					if(color == picture[nr][nc]) {
						district[nr][nc] = curDistrict;
						setDist(nr, nc, color, isWeak);						
					}
				}
				// 시작 좌표의 색상이 초록 또는 파랑색일때
				else {
					if(picture[nr][nc] == 'G' || picture[nr][nc] == 'R'){
						district[nr][nc] = curDistrict;
						setDist(nr, nc, color, isWeak);
					}
				}
			}
			// 적록색약이 아닌 사람일 때
			else {
				if(picture[nr][nc] == color) {
					district[nr][nc] = curDistrict;
					setDist(nr, nc, color, isWeak);				
				}				
			}
		}
	}
		
}
