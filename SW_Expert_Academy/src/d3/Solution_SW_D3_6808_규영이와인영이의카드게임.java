package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D3_6808_규영이와인영이의카드게임 {

	static int[] gyu;  // 규영이 카드
	static int[] in;  // 인영이 카드
	static int[] cards;  // 인영이 카드 순
	static int cnt_gyu;  // 규영이가 이긴 횟수
	static int cnt_in;  // 인영이가 이긴 횟수
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// static 변수 초기화
			gyu = new int[9];  // 규영이 카드
			in = new int[9];  // 인영이 카드
			cards = new int[9];		
			cnt_gyu = 0;
			cnt_in = 0;
			boolean[] isGyu = new boolean[18];  // 규영이가 선택한 숫자

			// 규영이 카드 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());  // 규영이 카드 입력
				isGyu[gyu[i] - 1] = true;  // 규영이 선택
			}
			
			// 인영이 카드 입력
			int idx = 0;
			for(int i = 0; i < 18; i++) {
				if(!isGyu[i])
					in[idx++] = i + 1;
			}
						
			getCase(0, 0);
			sb.append("#").append(tc).append(" ");
			sb.append(cnt_gyu).append(" ").append(cnt_in).append("\n");
		}
		
		// 결과 출력
		System.out.println(sb.toString());
		
		br.close();
	}

	// 규영가 이겼는지 확인
	public static boolean isWin() {
		int score_gyu = 0;
		int score_in = 0;
		
		for(int i = 0; i < 9; i++) {
			// 규영이 카드가 큰 수 일때
			if(gyu[i] > cards[i]) {
				score_gyu += gyu[i] + cards[i];
			}
			// 인영이 카드가 큰 수 일때
			else if(gyu[i] < cards[i]) {
				score_in += gyu[i] + cards[i];
			}
		}

		return score_gyu > score_in ? true : false;
	}
	
	public static void getCase(int cnt, int flag) {
		if(cnt == 9) {
			if(isWin()) {
				cnt_gyu++;
			}
			else {
				cnt_in++;
			}
			
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if((flag & 1 << i) != 0) continue;
			
			cards[cnt] = in[i];
			getCase(cnt + 1, flag | 1 << i);
		}

	}
	
}
