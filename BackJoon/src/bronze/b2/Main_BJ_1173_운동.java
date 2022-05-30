package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author : jhno96
 * @Date : 2022-05-16
 */
public class Main_BJ_1173_운동 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int ans = 0;
        int time = 0;
        int cur = m;
        while (time != N) {
            ans++;
            if (cur + T <= M) {
                cur += T;
                time++;
            } else {
                cur -= R;
                if (cur < m) cur = m;
            }

            if((cur + T > M) && (cur == m)) break;
        }

        System.out.println(time == N ? ans : -1);
    }

}
