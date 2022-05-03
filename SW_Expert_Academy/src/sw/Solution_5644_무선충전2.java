package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_무선충전2 {

	static class AP {
		
		int x, y;  // 좌표
		int c;  // 충전 범위
		int p;  // 성능
		
		public AP(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public String toString() {
			return "AP [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
		}
				
	}

	static int M;
	static int A;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	// 정지, 상, 우, 하, 좌
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	// AP 입력 배열
	static AP[] ap;
	// 결과
	static int sum;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_5644.txt"));;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());  // 이동시간
			A = Integer.parseInt(st.nextToken());  // 충전기의 개수
			
			map = new int[10][10];  // 지도 초기화
			sum = 0;  // 초기화
			
			// A의 이동정보 입력
			int[] move_A = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) move_A[i] = Integer.parseInt(st.nextToken());
			// A의 현재 위치
			int[] pos_A = {0, 0};
					
			// B의 이동정보 입력
			int[] move_B = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) move_B[i] = Integer.parseInt(st.nextToken());
			// B의 현재 위치
			int[] pos_B = {9, 9};
			
			// AP 정보 입력
			ap = new AP[A];
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				ap[i] = new AP(x, y, c, p);
			}
			
			// AP 정보에 따른 커버리지를 map에 입력
			for(int i = 0; i < A; i++) {
				getCoverage(ap[i], i);
			}
			
			for(AP a : ap) System.out.println(a);
			for(int[] r : map)
				System.out.println(Arrays.toString(r));
			System.out.println();
			// 시작에서 확인
			sum += getCurP(pos_A, pos_B);
//			System.out.println(sum);
			for(int i = 0; i < M; i++) {
				// A 이동
				pos_A[0] += dx[move_A[i]];
				pos_A[1] += dy[move_A[i]];

				// B 이동
				pos_B[0] += dx[move_B[i]];
				pos_B[1] += dy[move_B[i]];
				
				sum += getCurP(pos_A, pos_B);
//				System.out.println(i+1 + " : " + getCurP(pos_A, pos_B) + "=" + sum);
			}
//			System.exit(0);
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		
		System.out.println(sb.toString());
		
		br.close();
	}

	private static void getCoverage(AP ap, int i) {
		int size = ap.c * 2 + 1;
		int sx = ap.x - ap.c - 1;
		int sy = ap.y - ap.c - 1;
		
		int blank = size / 2;
		for(int r = 0; r < size; r++) {
			if(sy + r < 0 || sy + r >= 10) break;  // 경계 확인
			
			for(int c = blank; c < size - blank; c++) {
				if(sx + c < 0 || sx + c >= 10) break;  // 경계 확인
				map[sy + r][sx + c] = map[sy + r][sx + c] | 1 << i;
			}
			blank = r < size / 2 ? blank - 1 : blank + 1;
		}
	}
	
	private static int getCurP(int[] pos_A, int[] pos_B) {
		int result = 0;
		
		int cur_A = map[pos_A[1]][pos_A[0]];
		int cur_B = map[pos_B[1]][pos_B[0]];
		
		List<Integer> ap_A = new LinkedList<>(); 
		List<Integer> ap_B = new LinkedList<>(); 
		
		// 무슨 AP의 범위 위에 있는지
		for(int i = 0; i < A; i++) {
			if((cur_A & 1 << i) != 0) ap_A.add(i);
			if((cur_B & 1 << i) != 0) ap_B.add(i);
		}
		
		// A와 B가 아무것도 밟지 않으면
		if(ap_A.size() == 0 && ap_B.size() == 0) return result;
		
		// A와 B가 같은 AP를 밟고 있을 때
		if(isSame(ap_A, ap_B)) {
			int max = 0;
			for(int i = 0; i < ap_A.size(); i++) {
				for(int j = 0; j < ap_B.size(); j++) {
					if(ap_A.get(i) == ap_B.get(j)) {
						max = Math.max(max, ap[ap_A.get(i)].p);
					}
					else {
						max = Math.max(max, ap[ap_A.get(i)].p + ap[ap_B.get(j)].p);
					}
				}
			}
			result += max;
		}
		// 그 외
		else {
			int max_a = 0;
			for(int i = 0; i < ap_A.size(); i++) {
				max_a = Math.max(max_a, ap[ap_A.get(i)].p);
			}
			
			int max_b = 0;
			for(int i = 0; i < ap_B.size(); i++) {
				max_b = Math.max(max_b, ap[ap_B.get(i)].p);
			}
			
			result += max_a + max_b;
		}
		
//		System.out.println(Arrays.toString(ap_A.toArray()) + "/" + Arrays.toString(ap_B.toArray()));
		
		return result;
	}

	private static boolean isSame(List<Integer> ap_A, List<Integer> ap_B) {
		for(int i = 0; i < ap_A.size(); i++) {
			for(int j = 0; j < ap_B.size(); j++) {
				if(ap_A.get(i) == ap_B.get(j)) return true;
			}
		}
		return false;
	}
	
}
