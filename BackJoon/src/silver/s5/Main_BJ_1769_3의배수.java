package silver.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1769_3의배수 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String num = br.readLine();
		int cnt = 0;
		while(num.length() > 1) {
			cnt++;
			Long sum = 0L;
			for(int i = 0; i < num.length(); i++) {
				sum += Integer.parseInt(String.valueOf(num.charAt(i)));
			}
			num = String.valueOf(sum);
		}

		System.out.println(cnt);
		if((Integer.parseInt(num) % 3) == 0)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}

}
