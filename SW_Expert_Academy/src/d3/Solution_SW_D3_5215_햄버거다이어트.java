package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D3_5215_햄버거다이어트 {

	static int answer;
	static int N;
	static int[] scores;
	static int[] calories;
	static int L;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			scores = new int[N];
			calories = new int[N];
			L = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}

			answer = 0;
			getCombination(0, 0, 0);
			System.out.println("#" + tc + " " + answer);
		}

		br.close();

	}

	public static void getCombination(int idx, int sumScore, int sumCal) {
		
		if (idx == N) {
			if(answer < sumScore && sumCal <= L) {
				answer = sumScore;
			}
			return;
		}

		getCombination(idx + 1, sumScore + scores[idx], sumCal + calories[idx]);
		getCombination(idx + 1, sumScore, sumCal);

	}

}
