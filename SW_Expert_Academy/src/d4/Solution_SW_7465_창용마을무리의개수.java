package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_SW_D4_7465_창용마을무리의개수 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_7465.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] matrix = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				matrix[i] = i; 
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 집합
				union(matrix, a, b);
			}
			
			// 루트 노드 갱신
			for(int i = 1; i < matrix.length; i++) {
				findSet(matrix, i);
			}
			
			// 결과 append
			sb.append("#").append(tc).append(" ").append(getGroup(matrix)).append("\n");
		}
		
		// 결과 출력
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
	
	// 그룹의 수 확인 -> 중복을 제거한 matrix의 값의 개수 확인 
	public static int getGroup(int[] matrix) {
		Set<Integer> set = new HashSet<>();
		
		for(int i = 1; i < matrix.length; i++) {
			set.add(matrix[i]);
		}
		return set.size();
	}

}
