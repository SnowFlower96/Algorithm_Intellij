package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2563_색종이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 도화지
		boolean[][] canvas = new boolean[100][100];
		
		// 색종이의 수
		int N = Integer.parseInt(br.readLine());

		// 색종이 붙이기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			for(int r = x; r < x + 10; r++) {
				for(int c = y; c < y + 10; c++) {
					canvas[r][c] = true;
				}
			}
		}
		
		// 면적 구하기
		int cnt = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(canvas[i][j]) cnt++;
			}
		}
		
		// 결과 출력
		System.out.println(cnt);
		
		br.close();
	}

}
