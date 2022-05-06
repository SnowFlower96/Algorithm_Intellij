package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_4344_평균은넘겠지 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] scores = new int[N];
			int sum = 0;
			for(int j = 0; j < N; j++) {
				scores[j] = Integer.parseInt(st.nextToken());
				sum += scores[j];
			}
			
			double avg = (double)sum / N;
			int cnt = 0;
			for(int j = 0; j < N; j++) {
				if(scores[j] > avg) cnt++;
			}
			double result = ((double)cnt / N) * 100;
			sb.append(String.format("%.3f%%\n", result));
		}
		System.out.println(sb.toString());
		
		br.close();

	}

}
