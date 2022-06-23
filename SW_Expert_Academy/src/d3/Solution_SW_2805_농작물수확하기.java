package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SW_2805_농작물수확하기 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				String input = br.readLine();
				for(int j = 0; j < N; j++) {
					arr[i][j] = input.charAt(j) - '0';
				}
			}
			System.out.println("#" + tc + " " + solution(arr));
		}
		
		br.close();
	}

	public static int solution(int[][] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			int blank = Math.abs(arr.length / 2 - i);
			for(int j = blank; j < arr.length - blank; j++) {
				sum += arr[i][j];
			}
		}
		
		return sum;
	}
}
