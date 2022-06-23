package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_3499_퍼펙트셔틀 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_3499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] input = new String[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				input[i] = st.nextToken();
			}
			
			sb.append("#").append(tc);
			for(String str : shuffle(input)) {
				sb.append(" ").append(str);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	}

	public static String[] shuffle(String[] input) {
		int len = input.length;
		String[] result = new String[len];
		
		// 두 그룹의 시작 인덱스
		// 배열의 크기가 홀수이면 두 번째 그룹의 시작 인덱스가 1 증가
		int idx1 = 0;
		int idx2 = len % 2 == 0 ? len / 2 : len / 2 + 1;
		for(int i = 0; i < len; i++) {
			// i가 짝수이면 첫 번째 그룹
			if(i % 2 == 0) result[i] = input[idx1++];
			// i가 홀수이면 두 번째 그룹
			else result[i] = input[idx2++];
		}
		
		return result;
	}
}
