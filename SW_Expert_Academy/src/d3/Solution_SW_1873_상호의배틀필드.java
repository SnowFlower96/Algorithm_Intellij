package d3;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_1873_상호의배틀필드 {

	// 맵의 높이, 폭
	static int height;
	static int width;

	// 맵
	static char[][] map;

	// 전차 정보
	static int[] tank;

	// 전차의 방향에 따른 delta 배열
	// 상, 하, 좌, 우
	final static int[] dr = { -1, 1, 0, 0 };
	final static int[] dc = { 0, 0, -1, 1 };

	// 전차의 방향에 따른 모양
	// 상, 하, 좌, 우
	static char[] shape = { '^', 'v', '<', '>' };

	public static void main(String args[]) throws Exception {

		System.setIn(new FileInputStream("res/input_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T;
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 높이, 폭 입력
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());

			// 전차의 좌표 및 방향
			// 상 : 0, 하 : 1, 좌 : 2, 우 : 3
			tank = new int[3];

			// 맵 정보 입력
			map = new char[height][width];
			for (int i = 0; i < height; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// 사용자의 입력 저장
			Integer.parseInt(br.readLine());
			char[] works = br.readLine().toCharArray();

			// 전차의 위치 탐색
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (map[i][j] == '^') {
						tank = new int[] { i, j, 0 };
					} else if (map[i][j] == 'v') {
						tank = new int[] { i, j, 1 };
					} else if (map[i][j] == '<') {
						tank = new int[] { i, j, 2 };
					} else if (map[i][j] == '>') {
						tank = new int[] { i, j, 3 };
					}
				}
			}

			// 입력 처리
			for (char work : works) {
				process(work);
			}

			// 결과 출력
			sb.append("#" + tc + " ");
			for (char[] r : map) {
				for (char c : r) {
					sb.append(c);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	// 사용자의 입력을 처리
	public static void process(char work) {
		if (work == 'S') {
			shoot();
		} else {
			move(work);
		}
	}

	// 탱크 이동
	public static void move(char dir) {
		// 회전
		if (dir == 'U')
			tank[2] = 0;
		else if (dir == 'D')
			tank[2] = 1;
		else if (dir == 'L')
			tank[2] = 2;
		else if (dir == 'R')
			tank[2] = 3;
		// 모양 변경
		map[tank[0]][tank[1]] = shape[tank[2]];

		// 이동할 장소 확인
		int next_r = tank[0] + dr[tank[2]];
		int next_c = tank[1] + dc[tank[2]];

		// 경계 안이고 평지이면 이동
		if (next_r >= 0 && next_r < height && next_c >= 0 && next_c < width && map[next_r][next_c] == '.') {
			// 전차가 있던 자리 평지화
			map[tank[0]][tank[1]] = '.';
			// 전차 좌표 변경
			tank[0] += dr[tank[2]];
			tank[1] += dc[tank[2]];
			// 전차가 이동한 곳 문자 변경
			map[tank[0]][tank[1]] = shape[tank[2]];
		}
	}

	// 포탄 발사
	public static void shoot() {
		// 포탄이 이동할 자리, 초기값은 탱크 위치
		int next_r = tank[0];
		int next_c = tank[1];
		while (true) {
			// 포탄 이동
			next_r += dr[tank[2]];
			next_c += dc[tank[2]];

			// 포탄이 경계 밖으로 나가면
			if (next_r < 0 || next_r >= height || next_c < 0 || next_c >= width)
				break;

			// 포탄이 강철 벽에 부딪히면
			if (map[next_r][next_c] == '#')
				break;

			// 포탄이 벽돌 벽에 부딪히면
			if (map[next_r][next_c] == '*') {
				map[next_r][next_c] = '.'; // 벽돌 벽 파괴
				break;
			}
		}
	}
}
