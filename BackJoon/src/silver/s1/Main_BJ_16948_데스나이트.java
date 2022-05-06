package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16948_데스나이트 {

	static class Point {
		int r, c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	final static int[] dr = { -2, -2, 0, 0, 2, 2 };
	final static int[] dc = { -1, 1, -2, 2, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		System.out.println(bfs(N, new Point(r1, c1), new Point(r2, c2)));
	}

	private static int bfs(int N, Point start, Point end) {
		int ans = 0;
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(start);
		visited[start.r][start.c] = true;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Point cur = queue.poll();

				if ((cur.r == end.r) && (cur.c == end.c))
					return ans;

				for (int j = 0; j < 6; j++) {
					int nr = cur.r + dr[j];
					int nc = cur.c + dc[j];

					if (isInBound(N, nr, nc) && !visited[nr][nc]) {
						queue.offer(new Point(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
			ans++;
		}

		return -1;
	}

	private static boolean isInBound(int N, int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N) ? true : false;
	}
}
