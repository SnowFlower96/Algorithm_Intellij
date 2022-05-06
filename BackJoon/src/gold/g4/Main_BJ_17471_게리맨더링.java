package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * WS
 * @author jhno96
 * @date 2022. 4. 6.
 */
public class Main_BJ_17471_게리맨더링 {

	static int N, ans;
	static int[][] matrix;
	static int[] population;
	static int[] firstDistrict;
	static int[] secondDistrict;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		matrix = new int[N + 1][N + 1];
		population = new int[N + 1];
		
		// 인구수 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접한 구역의 정보
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int count = Integer.parseInt(st.nextToken());
			for(int j = 0; j < count; j++) {
				int to = Integer.parseInt(st.nextToken());
				matrix[i][to] = 1;
				matrix[to][i] = 1;
			}
		}

		ans = Integer.MAX_VALUE;
		
		for(int max = 1; max <= N/2; max++) {
			firstDistrict = new int[max];
			secondDistrict = new int[N - max];
			combination(0, 1, max);
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	private static void combination(int cnt, int start, int max) {
		if(cnt == max) {
			int firstIdx = 0;
			int secondIdx = 0;
			for(int i = 1; i<= N; i++) {
				if(firstIdx < max && firstDistrict[firstIdx] == i) {
					firstIdx++;
					continue;
				} else if(secondIdx < N - max){
					secondDistrict[secondIdx++] = i;
				}
			}
			
			if(checkValidation(firstDistrict, new boolean[firstDistrict.length]) && checkValidation(secondDistrict, new boolean[secondDistrict.length])) {
				int diff = getPopulationDiff();
				ans = Math.min(ans, diff);
				return;
			}
			
			return;
		}
		
		for(int i = start; i <= N; i++) {
			firstDistrict[cnt] = i;
			combination(cnt + 1, i + 1, max);
		}
	}

	private static int getPopulationDiff() {
		int firstPopulation = 0;
		int secondPopulation = 0;
		
		for(int i = 0; i < firstDistrict.length; i++) firstPopulation += population[firstDistrict[i]];
		for(int i = 0; i < secondDistrict.length; i++) secondPopulation += population[secondDistrict[i]];
		
		return Math.abs(firstPopulation - secondPopulation);
	}

	private static boolean checkValidation(int[] district, boolean[] isVisited) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(0);
		isVisited[0] = true;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i = 0; i < district.length; i++) {
				if(matrix[district[cur]][district[i]] != 0 && !isVisited[i]) {
					isVisited[i] = true;
					queue.offer(i);
				}
			}
		}
		
		for(boolean v : isVisited) if(!v) return false;

		return true;
	}

}
