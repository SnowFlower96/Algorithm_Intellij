package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * Study
 * @author jhno96
 * @Date 22. 05. 05
 */
public class Main_BJ_20058_마법사상어와파이어스톰 {

    static int N, Q, size;
    static int[][] arr;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            work(Integer.parseInt(st.nextToken()));
        }

        getAns();
    }

    private static void work(int l) {
        int space = (int) Math.pow(2, l);
        for (int i = 0; i < size; i += space) {
            for (int j = 0; j < size; j += space) {
                rotate(i, j, space);
            }
        }

        // 얼음 제거
        // 한번에 얼음을 제거하기 위한 변수
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 얼음이 없으면 pass
                if (arr[i][j] == 0) continue;

                // 얼음이 있는 해당 칸을 기준으로 얼음이 있는 칸을 찾기 위해 사방 탐색
                int cnt = 0;  // 얼음이 있는 칸의 개수
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    // 경계 체크
                    if (nr < 0 || nr >= size || nc < 0 || nc >= size) continue;

                    if (arr[nr][nc] != 0) cnt++;
                }

                // 얼음이 있는 근접한 칸이 3칸 미만이면 얼음수치 -1
                if (cnt < 3) list.add(new int[] { i, j });
            }
        }
        // 한번에 얼음 제거
        for (int[] point : list) arr[point[0]][point[1]] -= 1;
    }

    private static void rotate(int r, int c, int space) {
        for (int i = 0; i < space / 2; i++) {
            for (int j = 0; j < space - 1 - (i * 2); j++) {
                int nr = r + i;
                int nc = c + i;
                int ns = space - 1;
                int[] lt = {nr, nc + j};
                int[] rt = {nr + j, nc + ns - (i * 2)};
                int[] rb = {nr + ns - (i * 2), nc + ns - j - (i * 2)};
                int[] lb = {nr + ns - j - (i * 2), nc};

                int temp = arr[lt[0]][lt[1]];
                arr[lt[0]][lt[1]] = arr[lb[0]][lb[1]];
                arr[lb[0]][lb[1]] = arr[rb[0]][rb[1]];
                arr[rb[0]][rb[1]] = arr[rt[0]][rt[1]];
                arr[rt[0]][rt[1]] = temp;
            }
        }
    }

    private static void getAns() {
        int sum = 0;
        int maxCnt = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum += arr[i][j];

                if (visited[i][j]) continue;
                maxCnt = Math.max(maxCnt, dfs(i, j, visited, 0));
            }
        }

        System.out.println(sum);
        System.out.println(maxCnt);
    }

    private static int dfs(int r, int c, boolean[][] visited, int cnt) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= size || nc < 0 || nc >= size || visited[nr][nc] || arr[nr][nc] == 0) continue;

            visited[nr][nc] = true;
            cnt = dfs(nr, nc, visited, cnt + 1);
        }

        return cnt;
    }

    private static void show(int r, int c, int space) {
        for (int i = r; i < r + space; i++) {
            for (int j = c; j < c + space; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
