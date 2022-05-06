package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_13458_시험감독 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		System.out.println(solution(N, A, B, C));
	}

	private static long solution(int N, int[] A, int B, int C) {
		long cnt = N;
		
		for(int i = 0; i < N; i++) {
			int left = A[i] - B;
			if(left <= 0) continue;
			if(left % C != 0) cnt += left / C + 1;
			else cnt += left / C;
		}
		
		return cnt;
	}

}
