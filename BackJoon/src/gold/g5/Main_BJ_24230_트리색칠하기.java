package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author : jhno96
 * @Date : 2022-07-04
 */
public class Main_BJ_24230_트리색칠하기 {

    static int N, ans;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> list;
    static int[] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visit = new boolean[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            colors[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }

        if(colors[1] == 0) {
            ans = 0;
        } else {
            ans = 1;
        }
        DFS(1);

        System.out.println(ans);
    }

    private static void DFS(int cur) {
        visit[cur] = true;
        if (list.get(cur).size() == 0) return;

        int curColor = colors[cur];

        for (int n : list.get(cur)) {
            if (visit[n]) continue;
            if (colors[n] != curColor) ans++;
            DFS(n);
        }
    }

}
