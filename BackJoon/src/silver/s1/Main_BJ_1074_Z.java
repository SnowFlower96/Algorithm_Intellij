package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1074_Z {
	
	static int cnt = 0;
	static int N, R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int size = (int) Math.pow(2, N);
		
		solution(size, 0, 0);
				
		br.close();
	}
	
	// r, c : 시작 인덱스
	private static void solution(int size, int r, int c) {
		// 목표 인덱스에 도달
		if(r == R && c == C) {
			System.out.println(cnt);
			System.exit(0);
		}
		
		// 현재 위치안에 목표 인덱스가 존재
		if(r <= R && R < (r + size) && c <= C && C < (c + size)) {
			// 다음 중앙값
			int center = size / 2;
			solution(center, r, c);  // 좌상
			solution(center, r, c + center);  // 우상
			solution(center, r + center, c);  // 좌하
			solution(center, r + center, c + center);  // 우하
		}
		// 현재 위치 안에 목표인덱스가 없으면 size * size만큼 cnt 증가
		else cnt += size * size;
	}
	
}
