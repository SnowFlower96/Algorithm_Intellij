package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-05-17
 */
public class Main_BJ_1193_분수찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        int cnt = 0;
        int n = 0;

        while (true) {
            n++;
            cnt += n;
            if (cnt >= X) {
                if (n % 2 == 0) System.out.println(X - cnt + n + "/" + (cnt - X + 1));
                else System.out.println((cnt - X + 1) + "/" + (X - cnt + n));
                break;
            }
        }
    }

}
