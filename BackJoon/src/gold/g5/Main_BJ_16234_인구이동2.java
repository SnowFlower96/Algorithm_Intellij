package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Study
 * @Author : jhno96
 * @Date : 2022-06-30
 */
public class Main_BJ_16234_인구이동2 {

    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, L, R;
    static int[][] map;
    static Queue<Point> queue = new LinkedList<>();
    static List<Point> buffer = new ArrayList<>();
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문제 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 지도 입력
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        int ans = 0;

        while (true) {
            ans++;
            visit = new boolean[N][N];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        boolean isSolo = setGroupAndDivide(new Point(i, j));
                        if (isSolo) cnt++;
                    }
                }
            }

            if (cnt == N * N) break;
        }

        return ans - 1;
    }

    private static boolean setGroupAndDivide(Point st) {
        int sum = 0;

        queue.offer(st);
        buffer.clear();
        buffer.add(st);
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            visit[cur.r][cur.c] = true;
            sum += map[cur.r][cur.c];

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visit[nr][nc]) continue;

                int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);
                if (diff >= L && diff <= R) {
                    queue.offer(new Point(nr, nc));
                    buffer.add(new Point(nr, nc));
                }
            }
        }

        if (buffer.size() == 1) return true;

        int avg = sum / buffer.size();
        for (Point p : buffer) {
            map[p.r][p.c] = avg;
        }

        return false;
    }

}
