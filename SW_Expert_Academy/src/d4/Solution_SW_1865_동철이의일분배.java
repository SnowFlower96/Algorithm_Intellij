package d4;

import java.io.*;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 * A형 문제 풀이
 * @Author jhno96
 * @Date 2022. 6. 2.
 */
public class Solution_SW_1865_동철이의일분배 {

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("SW_Expert_Academy/res/input/input_1865.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        DecimalFormat df = new DecimalFormat("0.000000");

        int N;
        double[][] arr;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new double[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Double.parseDouble(st.nextToken()) / 100;
                }
            }

            boolean[] visit = new boolean[N];
            double ans = solution(N, arr, visit, 0, 1, 0);

            sb.append("#").append(tc).append(" ").append(df.format(ans * 100)).append("\n");
        }

        System.out.println(sb);
    }

    private static double solution(int N, double[][] arr, boolean[] visit, int cnt, double cur, double max) {
        if (cur < max) return max;

        if(cnt == N) {
            return Math.max(cur, max);
        }

        for (int i = 0; i < N; i++) {
            if (visit[i] || arr[cnt][i] == 0) continue;

            double nextCur = cur * arr[cnt][i];
            visit[i] = true;
            max = solution(N, arr, visit, cnt + 1, nextCur, max);
            visit[i] = false;
        }

        return max;
    }

}
