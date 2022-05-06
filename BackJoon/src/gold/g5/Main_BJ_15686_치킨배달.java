package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_15686_치킨배달 {

	static class Point {
		
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
	
	static int N, M, ans;  // 배열 크기, 치킨집 수, 정답
	static int[][] map;  // 지도
	static Point[] selected;  // 선택된 치킨집의 좌표 배열
	static List<Point> houses, chickens;  // 집, 치킨집 저장 리스트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N, M 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 초기화
		map = new int[N][N];
		houses = new LinkedList<>();
		chickens = new LinkedList<>();
		
		// 지도 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				
				// 집 또는 치킨집일때 해당 리스트에 입력
				if(val == 1) houses.add(new Point(i, j));
				else if(val == 2) chickens.add(new Point(i, j));
			}
		}
		
		ans = Integer.MAX_VALUE;  // 정답 초기화
		selected = new Point[M];  // 선택된 치킨집 초기화
		getCombination(chickens.size(), 0, 0);
		
		// 정답 출력
		System.out.println(ans);
		
		br.close();
	}

	// 좌표간의 거리 계산
	private static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
	}
	
	// 도시의 치킨거리 계싼
	private static int getCityDistance() {
		int total = 0;
		
		// 가정집별로 치킨집과의 최소 거리 계산
		int houseSize = houses.size();
		for(int i = 0; i < houseSize; i++) {
			int distance = Integer.MAX_VALUE;  // 집 기준 치킨거리
			for(int j = 0; j < M; j++) {
				distance = Math.min(distance, getDistance(houses.get(i), selected[j]));
			}
			total += distance;  // 도시 치킨 거리에 누적
		}
		
		return total;
	}
	
	// 조합으로 전체 치킨집중에서 M개의 치킨집 선택
	private static void getCombination(int size, int cnt, int start) {
		// 기저조건
		if(cnt == M) {
			// 해당 조합에서의 도시 치킨거리와 정답 비교
			ans = Math.min(ans, getCityDistance());
			return;
		}
		
		for(int i = start; i < size; i++) {
			selected[cnt] = chickens.get(i);
			getCombination(size, cnt + 1, i + 1);
		}
	}
}
