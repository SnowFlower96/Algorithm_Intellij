package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS
 * @Author : jhno96
 * @Date : 2022-06-16
 */
public class Main_BJ_2178_미로탐색 {

    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) == '1';
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        queue.offer(new int[] { 0, 0 });
        visit[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();

                if ((cur[0] == N - 1) && (cur[1] == M - 1)) {
                    System.out.println(ans);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || visit[nr][nc] || !map[nr][nc]) continue;

                    queue.offer(new int[] { nr, nc });
                    visit[nr][nc] = true;
                }
            }
            ans++;

        }
    }

}
