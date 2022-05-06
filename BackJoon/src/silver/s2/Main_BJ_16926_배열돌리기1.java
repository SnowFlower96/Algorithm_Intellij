package silver.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16926_배열돌리기1 {

	static int[][] arr;
	static int N;
	static int M;
	static int R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 배열의 N, M과 회전수 R 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		// 배열 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 회전 및 결과를 StringBuilder에 추가
		int[][] result = rotate();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}

		// 결과 출력
		System.out.println(sb.toString());

		br.close();
	}

	public static int[][] rotate() {
		// 짧은 축의 절반까지가 한 묶음
		int half = Math.min(N, M) / 2;
		while (R-- > 0) {
			for (int idx = 0; idx < half; idx++) {
				// 행과 열의 최대값
				int max_r = N - idx - 1;
				int max_c = M - idx - 1;
				
				// 좌측 상단 값 기억
				int first = arr[idx][idx];
				
				// 상단 가로 행 이동
				for (int i = idx; i < max_c; i++) {
					arr[idx][i] = arr[idx][i + 1];
				}

				// 우측 세로 열 이동
				for (int i = idx; i < max_r; i++) {
					arr[i][max_c] = arr[i + 1][max_c];
				}

				// 하단 가로 행 이동
				for (int i = max_c; i > idx; i--) {
					arr[max_r][i] = arr[max_r][i - 1];
				}

				// 좌측 세로 열 이동
				for (int i = max_r; i > idx; i--) {
					if (i == idx + 1) {
						arr[i][idx] = first;
						continue;
					}
					arr[i][idx] = arr[i - 1][idx];
				}
			}
		}

		return arr;
	}
}
