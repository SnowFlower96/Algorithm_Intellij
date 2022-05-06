package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_10819_차이를최대로 {

	static int N;
	static int[] arr;
	static int[] order;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		order = new int[N];
		ans = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		permute(0, 0);
		
		System.out.println(ans);
	}
	
	private static void permute(int cnt, int flag) {
		if(cnt == N) {
			ans = Math.max(ans, getSum());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if((flag & 1 << i) != 0) continue;
			
			order[cnt] = arr[i];
			permute(cnt + 1, flag | 1 << i);
		}
	}

	private static int getSum() {
		int sum = 0;
		
		for(int i = 0; i < N - 1; i++) {
			sum += Math.abs(order[i] - order[i+1]);
		}
		
		return sum;
	}

}
