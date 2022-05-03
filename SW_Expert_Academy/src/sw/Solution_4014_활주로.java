package sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * WS
 * @author jhno96
 * @date 2022. 4. 7.
 */
public class Solution_4014_활주로 {
	
	static int N, X;
	static int[][] rowMap, colMap;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_4014.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			rowMap = new int[N][N];
			colMap = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					rowMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 열탐색 용 배열
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					colMap[i][j] = rowMap[j][i];
				}
			}

			sb.append("#").append(tc).append(" ").append(solution()).append("\n");
		}
		System.out.println(sb);
		System.exit(0);
		
	}

	private static int solution() {
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			// 가로줄 확인
			if(check(i, true)) {
//				System.out.println(i + " 번째  행");
				result++;
			}
			// 세로줄 확인
			if(check(i, false)) {
//				System.out.println(i + " 번째  열");
				result++;
			}
		}

		return result;
	}

	private static boolean check(int idx, boolean flag) {
		int[] map = flag ? rowMap[idx] : colMap[idx];
		boolean[] check = new boolean[N];
		
		for(int i = 0; i <= N - 2; i++) {
			int left = map[i];
			int right = map[i + 1];
			
			int diff = left - right;
			
			if(Math.abs(diff) >= 2) return false;
			// 왼쪽이 높은 경우
			else if(diff == 1) {
				for(int j = i + 1; j <= i + X; j++) {
					if(j >= N || right != map[j] || check[j]) return false;
					check[j] = true;
				}
			}
			// 왼쪽이 낮은 경우
			else if(diff == -1) {
				for(int j = i; j >= i + 1 - X; j--) {
					if(j < 0 || left != map[j] || check[j]) return false;
					check[j] = true;
				}
			}
		}
//		int stIdx = 0;
//		
//		// curIdx가 끝까지 도달할 떄까지
//		for(int i = 1; i < N; i++) {
////			System.out.println(stIdx + "," + curIdx + " : " + map[rowIdx][stIdx] + ","+ map[rowIdx][curIdx]);
//			
//			// 시작 지형과 현재 지형간의 높이 차이
//			int diff = map[stIdx] - map[i];
//
//			// 높이의 차가 1보다 크면 경사로 불가능
//			if(Math.abs(diff) > 1) return false;
//
//			// 높이의 차가 1이면 하강 경사 => curIdx부터 경사로 시도
//			else if(diff == 1) {
//				// 바로 다음 지형부터 경사로의 크기만큼 놓을 수 있는지 확인
//				for(int j = 0; j < X; j++) {
//					// 경사로가 지형 밖을 벗어나거나 높이가 달라지면 경사로를 놓을 수 없음
//					if(i + j >= N || map[i] != map[i + j]) return false;
//				}
//				
//				// 경사로를 놓을 수 있으면
//				i = i + X - 1;
//				stIdx = i;
//			}
//			
//			// 높이의 차가 -1이면 상승 경사 => 바로 직전 지형부터 경사로 시도
//			else if(diff == -1) {
//				// 경사로의 크기만큼 놓을 수 있는지 확인
////				for(int j = 1; j <= X; j++) {
////					// 경사로가 지형 밖을 벗어나거나 높이가 달라지면 경사로를 놓을 수 없음
////					if(i - j < 0 || map[i] - 1 != map[i - j]) return false;
////				}
//				if(stIdx + X - 1 >= i) return false;
//								
//				// 경사로를 놓을 수 있으면
//				stIdx = i;
//			}
//		}
		
		// 무사히 도달하면 경사로 설정 가능
		return true;
	}

}
