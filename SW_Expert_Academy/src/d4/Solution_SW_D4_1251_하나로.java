package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D4_1251_하나로 {

	public static voi3d main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] x = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] y = new int [N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
		}
		
		br.close();
	}
	
	private static double getDistance() {
		return 0;
	}

}
