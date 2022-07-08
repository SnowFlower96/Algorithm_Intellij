package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Study
 * @Author jhno96
 * @Date 2022-07-07
 */
public class Main_BJ_10159_저울 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N + 1][N + 1];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int heavier = Integer.parseInt(st.nextToken());
            int lighter = Integer.parseInt(st.nextToken());
            map[heavier][lighter] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (i == k) continue;
                for (int j = 1; j <= N; j++) {
                    if (i == j || j == k) continue;
                    if (!map[i][j] && (map[i][k] && map[k][j])) map[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (!map[i][j]) cnt++;
            }
//            System.out.println(Arrays.toString(map[i]));
            System.out.println(cnt);
        }
    }

}
