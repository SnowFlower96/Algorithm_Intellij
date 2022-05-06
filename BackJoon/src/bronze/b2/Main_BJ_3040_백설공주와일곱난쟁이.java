package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Main_BJ_3040_백설공주와일곱난쟁이_OOO
public class Main_BJ_3040_백설공주와일곱난쟁이 {

	static int[] dwarfs = new int[9];
	static int[] answer = new int[7];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 9난쟁이 입력
		for (int i = 0; i < 9; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());
		}

		combination(0, 0);

		System.out.println(sb.toString());

		br.close();
	}

	// 조합
	public static void combination(int cnt, int start) {
		// 7명의 조합이 될 때
		if (cnt == 7) {
			// 합이 100이면 StringBuilder에 append
			if (Arrays.stream(answer).sum() == 100) {
				for(int i = 0; i < 7; i++) {
					sb.append(answer[i]).append("\n");
				}
			}

			return;
		}

		for (int i = start; i < 9; i++) {
			answer[cnt] = dwarfs[i];
			combination(cnt + 1, i + 1);
		}
	}

}
