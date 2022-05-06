package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * WS
 * @author jhno96
 * @date 2022. 4. 6.
 */
public class Main_BJ_2239_스도쿠 {

	static class Point{
		
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
		
	}
	
	static int[][] arr;
	static List<Point> zeroList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 배열 입력
		arr = new int[9][9];
		zeroList = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j < 9; j++) {
				arr[i][j] = line[j] - '0';
				if(arr[i][j] == 0) {
					zeroList.add(new Point(i, j));
				}
			}
		}
		
		solution(0);
	}

	// 좌측 상단부터 오름차순으로 숫자 입력하며 스도쿠를 채워 나간다
	private static void solution(int idx) {
		// 종료 조건
		if(idx == zeroList.size()) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(arr[i][j]);
				}
				sb.append("\n");
			}
			sb.setLength(sb.length() - 1);
			System.out.println(sb);
			System.exit(0);
			return;
		}
		
		int r = zeroList.get(idx).r;
		int c = zeroList.get(idx).c;
		boolean[] usedNums = getUsedNums(zeroList.get(idx));
		
		// 숫자를 오름차순으로 대입
		for(int i = 1; i <= 9; i++) {
			if(!usedNums[i]) {  // 중복이 아니면
				arr[r][c] = i;
				solution(idx + 1);
				arr[r][c] = 0;
			}
		}
	}
	
	/**
	 * 행, 열, 구역별 중복 확인
	 * @param r 확인할 행 좌표
	 * @param c 확인할 열 좌표
	 * @param num 확인할 숫자
	 * @return 중복이 아니면 true, 중복이면 false
	 */
	private static boolean[] getUsedNums(Point p) {
		boolean[] numbers = new boolean[10];
		int r = p.r;
		int c = p.c;
		
		// 행 기준 확인
		for(int i = 0; i < 9; i++) {
			int val = arr[r][i];
			if(val > 0) numbers[val] = true;
		}

		// 열 기준 확인
		for(int i = 0; i < 9; i++) {
			int val = arr[i][c];
			if(val > 0) numbers[val] = true;
		}
		
		// 구역 기준 확인
		int sr = r - r % 3;
		int sc = c - c % 3;
		for(int i = sr; i < sr + 3; i++) {
			for(int j = sc; j < sc + 3; j++) {
				int val = arr[i][j];
				if(val > 0) numbers[val] = true;
			}
		}

		return numbers;
	}

}
