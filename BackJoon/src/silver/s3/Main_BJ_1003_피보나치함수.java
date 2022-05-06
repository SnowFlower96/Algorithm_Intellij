package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1003_피보나치함수 {

	static Integer[][] dp = new Integer[41][2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			fibo(n);
			sb.append(dp[n][0]).append(" ").append(dp[n][1]).append("\n");
		}
		
		System.out.println(sb);
	}

	private static Integer[] fibo(int n) {
		if(dp[n][0] == null || dp[n][1] == null) {
			dp[n][0] = fibo(n - 1)[0] + fibo(n - 2)[0];
			dp[n][1] = fibo(n - 1)[1] + fibo(n - 2)[1];
		}
		
		return dp[n];
	}

}
