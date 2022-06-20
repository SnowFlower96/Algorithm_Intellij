package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Study
 * Kruskal 알고리즘
 * @Author : jhno96
 * @Date : 2022-06-20
 */
public class Main_BJ_14621_나만안되는연애_Kruskal {

    static class Edge implements Comparable<Edge>{
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> edgeList = new ArrayList<>();
        boolean[] isMan = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            isMan[i] = st.nextToken().equals("M");
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(isMan[from] != isMan[to]) {
                edgeList.add(new Edge(from, to, w));
            }
        }

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        int ans = 0;
        int cnt = 0;
        for (Edge e : edgeList) {
            if (findParent(parent, e.from) != findParent(parent, e.to)) {
                union(parent, e.from, e.to);
                ans += e.w;
                cnt++;
            }
        }

        System.out.println(cnt == N - 1 ? ans : -1);
    }

    private static int findParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return findParent(parent, parent[x]);
    }

    private static void union(int[] parent, int x, int y) {
        x = findParent(parent, x);
        y = findParent(parent, y);

        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

}
