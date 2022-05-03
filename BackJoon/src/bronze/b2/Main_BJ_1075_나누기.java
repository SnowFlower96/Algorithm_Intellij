package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-05-01
 */
public class Main_BJ_1075_나누기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int F = Integer.parseInt(br.readLine());

        int ans = 0;
        N = N / 100 * 100;
        while (true) {
            if((N + ans) % F == 0) break;
            else ans++;
        }

        System.out.printf("%02d", ans);
    }

}
