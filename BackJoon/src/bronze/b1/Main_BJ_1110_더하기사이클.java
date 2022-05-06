package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1110_더하기사이클 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int input = Integer.parseInt(br.readLine());
		int cnt = 0;
		int temp = input;

		while (true) {
			int sum = temp / 10 + temp % 10;
			temp = temp % 10 * 10 + sum % 10;
			cnt++;
			if(temp == input)
				break;
		}

		System.out.println(cnt);
		br.close();

	}

}
