package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D4_3234_준환이의양팔저울 {

	static int ans;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 총 테스트 케이스의 수
		int T = Integer.parseInt(br.readLine());
		
		// 테스트 케이스별로
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());  // 무게 추의 수
			int[] weights = new int[N];  // 무게 추 배열
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) weights[i] = Integer.parseInt(st.nextToken());

			solution(weights, N, 0, 0, 0, 0);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		// 결과 출력
		System.out.println(sb.toString());
		
		br.close();
	}

	public static void solution(int[] weights, int N, int cnt, int flag, int left, int right) {	
		// 무게 추를 전부 올렸을 때
		if(cnt == N) {
			ans++;
			return;
		}

		for(int i = 0; i < N; i++) {
			// 선택되어있으면
			if((flag & 1 << i) != 0) continue;
			
			// 왼쪽에 올려놓았을 때
			solution(weights, N, cnt + 1, flag | 1 << i, left + weights[i], right); 
			
			// 오른쪽에 올려놓을 때 왼쪽보다 가벼우면 
			if(right + weights[i] <= left) {
				// 오른쪽에 올려놓았을 때
				solution(weights, N, cnt + 1, flag | 1 << i, left, right + weights[i]); 				
			}
		}
	}
		
}