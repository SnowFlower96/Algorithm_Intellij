package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_BJ_2309_일곱난쟁이 {

	static StringBuilder sb = new StringBuilder();
	static int[] heights = new int[9];
	static boolean[] isUsed = new boolean[9];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(heights);
		
		solution(0, 0);
		
		br.close();

	}

	public static void solution(int cnt, int sumHeight) {

		if (cnt == 7) {
			if(sumHeight != 100) return;
			
			List<Integer> result = new ArrayList<Integer>(7);
			for (int i = 0; i < 9; i++) {
				if (isUsed[i])
					result.add(heights[i]);
			}
			
			for(int i = 0; i < result.size(); i++) {
				sb.append(result.get(i) + "\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}

		for (int i = 0; i < 9; i++) {
			if (isUsed[i])
				continue;
			isUsed[i] = true;
			solution(cnt + 1, sumHeight + heights[i]);
			isUsed[i] = false;
		}
	}

}
