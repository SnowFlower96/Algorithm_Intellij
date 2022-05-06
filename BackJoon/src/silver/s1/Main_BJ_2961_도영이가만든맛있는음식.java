package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2961_도영이가만든맛있는음식 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 재료 개수 입력
		int N = Integer.parseInt(br.readLine());
		int[][] ingredient = new int[N][2];
		
		// 재료 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ingredient[i][0] = Integer.parseInt(st.nextToken());
			ingredient[i][1] = Integer.parseInt(st.nextToken());			
		}
		
		// 결과 출력
		System.out.println(generate(ingredient));
		
		boolean[] isVisited = new boolean[N];
		System.out.println(generate2(ingredient, isVisited, 0, Integer.MAX_VALUE));
		
		br.close();
	}

	private static int generate(int[][] ingredient) {
		int N = ingredient.length;
		int caseCount = 1 << N;
		int result = Integer.MAX_VALUE;
		
		for(int flag = 0 ; flag < caseCount; flag++) {
			// 재료가 하나도 없을 때
			if(flag == 0) continue;
			
			int sour = 1;  // 신맛
			int bitter = 0;  // 쓴맛
			for (int i = 0; i < N; i++) {  // 각 비트열의 상태를 확인
				if((flag & 1 << i) != 0) {
					sour *= ingredient[i][0];
					bitter += ingredient[i][1];
				}
			}
			// 최소값
			result = Math.min(result, Math.abs(sour - bitter));
		}
		
		return result;
	}
	
	private static int generate2(int[][] ingredient, boolean[] isVisited, int cnt, int result) {
		if(cnt == ingredient.length) {
			int sour = 1;  // 신맛
			int bitter = 0;  // 쓴맛
			for (int i = 0; i < isVisited.length; i++) {
				if(isVisited[i]) {
					sour *= ingredient[i][0];
					bitter += ingredient[i][1];					
				}
			}
			if(sour != 1 && bitter != 0)
				result = Math.min(result, Math.abs(sour - bitter));
			
			return result;
		}
		
		isVisited[cnt] = true;
		result = generate2(ingredient, isVisited, cnt + 1, result);
		
		isVisited[cnt] = false;
		result = generate2(ingredient, isVisited, cnt + 1, result);			
		
		return result;
	}

}
