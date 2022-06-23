package d2;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @Author jhno96
 * @Date 2022. 2. 6.
 */
public class Solution_SW_1954_달팽이숫자 {

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("res/input_1954.txt"));

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T;
		T = sc.nextInt();

		// 우향, 하향, 좌향, 상향
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		for (int tc = 1; tc <= T; tc++) {
			// 배열의 크기 입력 및 배열 선언
			int N = sc.nextInt();
			int[][] arr = new int[N][N];

			int val = 1;  // 입력 원소값
			int i = 0;  // 행 인덱스
			int j = 0;  // 열 인덱스
			int idx = 0;  // dr, dc의 인덱스
			while (val <= N * N) {
				arr[i][j] = val;

				// 이동했을 때, 경계확인
				int next_r = i + dr[idx];
				int next_c = j + dc[idx];
				if (next_r < 0 || next_r >= N || next_c < 0 || next_c >= N || arr[next_r][next_c] != 0) {
					idx = ++idx % 4;
				} 
				
				i = i + dr[idx];
				j = j + dc[idx];
				val++;
			}
			
			/*
			// 결과 출력 - 1
			System.out.println("#" + tc);
			for (int[] r : arr) {
				for (int c : r) {
					System.out.printf("%d\t", c);
				}
				System.out.println();
			}
			*/
			
			// 결과출력 - 2
			sb.append("#" + tc + "\n");
			for (int[] r : arr) {
				for (int c : r) {
					sb.append(c + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			sb.setLength(0);
		}
		sc.close();
		
	}

}
