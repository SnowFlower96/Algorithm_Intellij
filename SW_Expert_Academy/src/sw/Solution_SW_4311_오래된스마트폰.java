package sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A형 문제 풀이
 * @Author jhno96
 * @Date 2022. 6. 2.
 */
public class Solution_SW_4311_오래된스마트폰 {

    static final char[] OP = { ' ', '+', '-', '*', '/' };

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SW_Expert_Academy/res/input/input_4311.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N, O, M, target;
        int[] num, op;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            O = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            num = new int[N];
            op = new int[O];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < O; i++) {
                op[i] = Integer.parseInt(st.nextToken());
            }

            target = Integer.parseInt(br.readLine());

            // 1. 그냥 만들 수 있는지 확인

            // 2. 사칙연산으로 만들기


            System.exit(0);
        }

        System.out.println(sb);
    }

}
