package sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * WS
 * @author Home
 * @date 2022. 4. 13.
 */
public class Solution_4013_특이한자석 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_4013.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			
			int[][] magnets = new int[4][8];
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] order = new int[K][2];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				order[i][0] = Integer.parseInt(st.nextToken());
				order[i][1] = Integer.parseInt(st.nextToken());
			}
			
			System.out.printf("#%d %d\n", tc, solution(magnets, order));
		}
	}

	private static int solution(int[][] magnets, int[][] order) {
		for(int i = 0; i < order.length; i++) {
			int idx = order[i][0] - 1;
			int dir = order[i][1];
			rotateAll(magnets, idx, dir);
		}
		
		int sum = 0;
		sum += magnets[0][0] == 0 ? 0 : 1;
		sum += magnets[1][0] == 0 ? 0 : 2;
		sum += magnets[2][0] == 0 ? 0 : 4;
		sum += magnets[3][0] == 0 ? 0 : 8;
		
		return sum;
	}

	private static void rotateAll(int[][] magnets, int idx, int dir) {
		int[] dirs = new int[4];
		dirs[idx] = dir;

		for(int i = idx - 1; i >= 0; i--) {			

			if(magnets[i][2] != magnets[i + 1][6]) {  
				dirs[i] = dirs[i + 1] * -1;
			}
		}

		for(int i = idx; i < 3; i++) {

			if(magnets[i][2] != magnets[i + 1][6]) { 
				dirs[i + 1] = dirs[i] * -1;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			rotate(magnets[i], dirs[i]);
		}
	}

	private static void rotate(int[] magnet, int dir) {
		// 시계 방향일 떄
		if(dir == 1) {
			int temp = magnet[7];
			for(int i = 7; i > 0; i--) {
				magnet[i] = magnet[i - 1];
			}
			magnet[0] = temp;
		}
		// 반시계 방향일 떄
		else if(dir == -1) {
			int temp = magnet[0];
			for(int i = 1; i < 8; i++) {
				magnet[i - 1] = magnet[i];
			}
			magnet[7] = temp;
		}
	}
}
