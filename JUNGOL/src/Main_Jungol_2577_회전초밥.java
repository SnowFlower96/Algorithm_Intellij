import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * HW
 * 
 * @author jhno96
 * @date 2022. 4. 5.
 */
public class Main_Jungol_2577_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// 초밥 입력
		int[] sushi = new int[N];
		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(slidingWindow(N, sushi, d, k, c));
	}

	private static int slidingWindow(int N, int[] sushi, int d, int k, int c) {
		int count = 0;
		int[] isVisited = new int[d + 1];
		
		// 첫 번째 윈도우
		for(int i = 0; i < k; i++) {
			int val = sushi[i];
			if(isVisited[val] == 0) {
				count++;
			}
			isVisited[val]++;
		}
		// 다음 요소부터
		int max = count;
		for(int i = 1; i < N; i++) {
			if(max <= count) {
				if(isVisited[c] == 0) max = count + 1;
				else max = count;
			}
			
			isVisited[sushi[i - 1]]--;
			if(isVisited[sushi[i - 1]] == 0) count--;
			
			if(isVisited[sushi[(i + k - 1) % N]] == 0) count++;
			isVisited[sushi[(i + k - 1) % N]]++;
		}
		
		return max;
	}

}
