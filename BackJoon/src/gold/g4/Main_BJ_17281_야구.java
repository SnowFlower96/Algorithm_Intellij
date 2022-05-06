package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_BJ_17281_야구 {

	static int inning;
	static int[] order;
	static boolean[] isSelected;
	static int answer;
	static int[][] player;
	static boolean[] base;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		inning = Integer.parseInt(br.readLine());
		base = new boolean[3];
		player = new int[inning][9];
		order = new int[9];
		isSelected = new boolean[9];
		order[3] = 1;
		for(int i = 0; i < inning; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1번 선수를 4번 타자로
		order[3] = 1;
		isSelected[3] = true;

		permute(2);
		
		System.out.println(answer);
	}
	
	private static int play() {
		int score = 0;
		
		int curIdx = 0;
		for(int i = 0; i < inning; i++) {
			int out = 0;
			base = new boolean[3];

			// 3아웃이 될 때까지
			while(out < 3) {
				int value = player[i][order[curIdx] - 1];
				// 아웃
				if(value == 0) {
					out++;
				}
				else {
					// 진루타만큼 이동
					for(int v = 0; v < value; v++) {
						if(base[2]) score++;
						base[2] = base[1];
						base[1] = base[0];
						base[0] = v == 0 ? true : false;
					}
				}
				curIdx = (curIdx + 1) % 9;
			}
		}
		
		return score;
	}
	
	/**
	 * 모든 타순 경우의 수 순열
	 * @param cnt 타순이 정해진 선수의 수
	 */
	private static void permute(int cnt) {
		// 모든 선수의 타순이 정해지면
		if(cnt == 10) {
			answer = Math.max(answer, play());
			return;
		}

		// 0 번째 선수 => 1번째 선수는 타선이 정해져 있음
		for(int i = 1; i <= 9; i++) {
			if(isSelected[i - 1]) continue;
			
			isSelected[i - 1] = true;
			order[i - 1] = cnt;
			permute(cnt + 1);
			isSelected[i - 1] = false;
		}
	}
}

