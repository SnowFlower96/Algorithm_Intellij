package bronze.b2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2577_숫자의개수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		N *= Integer.parseInt(br.readLine());
		N *= Integer.parseInt(br.readLine());
		String sum = Integer.toString(N);
		
		int[] cnt = new int[10];
		for(int i = 0; i < sum.length(); i++) {
			cnt[sum.charAt(i) - '0']++;
		}
		
		for(int i = 0; i < cnt.length; i++)
			System.out.println(cnt[i]);
		
		br.close();
	}

}
