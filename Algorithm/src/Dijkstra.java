import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    static final int INF = 9999999;

    static class Node implements Comparable<Node>{
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) {
        int V = 5;  // 정점의 수
        int E = 6;  // 간선의 수

        // 인접리스트 선언 및 초기화
        ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        // 인접리스트 입력
        adjList.get(5).add(new Node(1, 1));
        adjList.get(1).add(new Node(5, 1));

        adjList.get(1).add(new Node(2, 2));
        adjList.get(2).add(new Node(1, 2));

        adjList.get(1).add(new Node(3, 3));
        adjList.get(3).add(new Node(1, 3));

        adjList.get(2).add(new Node(3, 4));
        adjList.get(3).add(new Node(2, 4));

        adjList.get(2).add(new Node(4, 5));
        adjList.get(4).add(new Node(2, 5));

        adjList.get(3).add(new Node(4, 6));
        adjList.get(4).add(new Node(3, 6));

        // 시작 정점
        int st = 1;

        int[] dist = new int[V + 1];           // 최소거리 배열
        Arrays.fill(dist, INF);                // 무한대로 초기화
        dist[st] = 0;                          // 시작 정점은 0으로
        boolean[] visit = new boolean[V + 1];  // 방문 체크 배열

        for (int i = 1; i <= V; i++) {
            int nodeValue = Integer.MAX_VALUE;
            int nodeIdx = 0;
            for (int j = 1; j < V + 1; j++) {
                if (!visit[j] && dist[j] < nodeValue) {
                    nodeValue = dist[j];
                    nodeIdx = j;
                }
            }
            visit[nodeIdx] = true;

            for (Node n : adjList.get(nodeIdx)) {
                if (dist[n.to] > dist[nodeIdx] + n.w) {
                    dist[n.to] = dist[nodeIdx] + n.w;
                }
            }
        }

        // dist 배열 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb);
    }

}
