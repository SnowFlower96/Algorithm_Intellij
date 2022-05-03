package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SW_D3_9229_한빈이와SpotMart {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_9229.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] list = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(list);
			
			System.out.println("#" + tc + " " + solution(list, M, 0, 0, 0));
		}
	}
	
	public static int solution(int[] list, int M, int max, int cnt, int start) {
		int result = -1;
		
		if(cnt == 2) return max;
		
		for(int i = start; i < list.length; i++) {
			if(max + list[i] <= M) {
				result = Math.max(result, solution(list, M, max + list[i], cnt + 1, i + 1));
			}
			else {
				continue;
			}
		}
		return result;
	}

}
