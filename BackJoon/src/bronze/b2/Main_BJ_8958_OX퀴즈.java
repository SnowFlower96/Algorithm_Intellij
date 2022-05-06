package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_8958_OX퀴즈 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int sum = 0;
			int cnt = 0;
			String input = br.readLine();
			for(int j = 0; j < input.length(); j++) {
				if(input.charAt(j) == 'O') {
					cnt++;
					sum += cnt;
				}
				else if(input.charAt(j) == 'X') {
					cnt = 0;
				}
			}
			sb.append(sum + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();

	}

}
