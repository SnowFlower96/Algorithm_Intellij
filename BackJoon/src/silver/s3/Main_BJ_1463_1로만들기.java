package silver.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Webex 실습
 * @author Home
 * @date 2022. 3. 31.
 */
public class Main_BJ_1463_1로만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] table = new int[N + 1];
		table[0] = 0;
		table[1] = 0;
		
		for(int i = 2; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			if(i % 3 == 0 && table[i / 3] + 1 < min) min = table[i / 3] + 1;
			if(i % 2 == 0 && table[i / 2] + 1 < min) min = table[i / 2] + 1;
			if(i >= 1 && table[i - 1] + 1 < min) min = table[i - 1] + 1;
			
			table[i] = min;
		}
		
		System.out.println(table[N]);
	}

}
