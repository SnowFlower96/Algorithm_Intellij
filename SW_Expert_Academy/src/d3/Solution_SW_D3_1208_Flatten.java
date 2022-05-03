package d3;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D3_1208_Flatten {

	static int limit;
	static int diff = Integer.MAX_VALUE;
	static int[] box = new int[100];
	static int max_idx, min_idx;
	static int tc;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_1208.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(tc = 1; tc <= 10; tc++) {
			limit = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}
			dump(0);

		}
		
		br.close();

	}
	
	public static void dump(int cnt) {
		
		if(cnt == limit || diff <= 1) {
			System.out.println("#" + tc + " " + diff);
			return;
		}
		
		getMinMax();
		box[max_idx]--;
		box[min_idx]++;
		getMinMax();
		diff = box[max_idx] - box[min_idx];
		
		dump(cnt + 1);
		
	}
	
	public static void getMinMax() {
		
		for(int i = 0; i < 100; i++) {
			if(box[i] > box[max_idx]) {
				max_idx = i;
			}
			if(box[i] < box[min_idx]) {
				min_idx = i;
			}
		}
		
	}

}
