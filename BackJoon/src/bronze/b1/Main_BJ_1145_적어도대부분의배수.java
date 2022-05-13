package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author : jhno96
 * @Date : 2022-05-11
 */
public class Main_BJ_1145_적어도대부분의배수 {

    static final int SIZE = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[SIZE];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < SIZE; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int val = 0;
        while(val++ >= 0) {
            int cnt = 0;
            for (int i = 0; i < SIZE; i++) {
                if(val % arr[i] == 0) cnt++;
            }
            if(cnt >= 3) {
                System.out.println(val);
                break;
            }
        }
    }

}
