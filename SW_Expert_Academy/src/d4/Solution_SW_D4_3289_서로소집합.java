package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D4_3289_서로소집합 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_3289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] matrix = new int[n + 1];
			for(int i = 1; i <= n; i++) {
				matrix[i] = i;
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(op == 0) {
					union(matrix, a, b);
				}
				else if(op == 1) {
					sb.append(findSet(matrix, a) == findSet(matrix, b) ? 1 : 0);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}
	
	public static int findSet(int[] matrix, int a) {
		if(a == matrix[a]) return a;
		return matrix[a] = findSet(matrix, matrix[a]);
	}

	public static boolean union(int[] matrix, int a, int b) {
		int aRoot = findSet(matrix, a);
		int bRoot = findSet(matrix, b);
		
		if(aRoot == bRoot) return false;
		
		matrix[bRoot] = aRoot;
		return true;
	}
}
