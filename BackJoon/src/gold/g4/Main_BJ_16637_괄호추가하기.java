package gold.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : jhno96
 * @Date : 2022-07-03
 */
public class Main_BJ_16637_괄호추가하기 {

    static int ans;
    static List<Integer> num;
    static List<Character> op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        num = new ArrayList<>();
        op = new ArrayList<>();

        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') op.add(c);
            else num.add(c - '0');
        }

        ans = Integer.MIN_VALUE;
        DFS(num.get(0), 0);
        System.out.println(ans);
    }

    private static void DFS(int sum, int idx) {
        if (idx >= op.size()) {
            ans = Math.max(ans, sum);
            return;
        }

        // 괄호 미적용
        int s1 = calc(sum, num.get(idx + 1), op.get(idx));
        DFS(s1, idx + 1);

        // 괄호 적용
        if (idx + 1 < op.size()) {
            int s2 = calc(num.get(idx + 1), num.get(idx + 2), op.get(idx + 1));
            DFS(calc(sum, s2, op.get(idx)), idx + 2);
        }
    }

    private static int calc(int n1, int n2, char op) {
        if (op == '+') return n1 + n2;
        else if (op == '-') return n1 - n2;
        else if (op == '*') return n1 * n2;
        else return -1;
    }

}
