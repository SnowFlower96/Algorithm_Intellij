package bronze.b1;

import java.io.IOException;
import java.util.Scanner;

public class Main_BJ_2839_설탕배달 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		int sugar = sc.nextInt();
		int cnt = -1;
		int x = sugar / 5;

		while(x >= 0) {
			int remain = sugar - 5 * x;
			if((remain % 3) == 0) {
				cnt = x + remain / 3;
				break;
			}
			x--;
		}
		System.out.println(cnt);

		sc.close();
	}

}
