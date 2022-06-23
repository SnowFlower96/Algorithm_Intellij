package d3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SW_1289_원재의메모리복구 {

	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream(new File("res/input_1289.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= N; tc++) {
			char buffer = '0';
			int cnt = 0;
			String input = br.readLine();
			for(int i = 0; i < input.length(); i++) {
				char bit = input.charAt(i);
				if(buffer != bit) {
					buffer = bit;
					cnt++;
				}
			}
			System.out.printf("#%d %d\n", tc, cnt);
		}
	}

}
