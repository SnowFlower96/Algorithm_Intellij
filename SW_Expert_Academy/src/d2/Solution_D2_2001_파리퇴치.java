package d2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author jhno96
 * @date 2022. 2. 6.
 */
public class Solution_D2_2001_파리퇴치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트 케이스
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// N, M 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 배열 입력
			int[][] arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
						
			// 최대값 저장
			int answer = 0;
			for(int i = 0; i < N - M + 1; i++) {
				for(int j = 0; j < N - M + 1; j++) {
					int sum = 0;

					// M * M 크기 안의 합 계산 
					for(int r = i; r < i + M; r++) {
						for(int c = j; c < j + M; c++) {
							sum += arr[r][c];
						}
					}
					if(answer < sum)
						answer = sum;
				}
			}

			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}

}
