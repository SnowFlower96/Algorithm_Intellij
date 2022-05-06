package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2562_최댓값 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = 0;
		int idx = 0;
		
		for(int i = 0; i < 9; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input > max) {
				max = input;
				idx = i + 1;
			}
		}
		
		System.out.println(max + "\n" + idx);
	}

}
