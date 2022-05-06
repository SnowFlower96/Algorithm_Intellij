package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1753_최단경로 {

	static class Node {
		int to, weight;
		Node next;
		
		public Node(int to, int weight, Node next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
		
	}
	
	static int V, E, K;
	static Node[] adjList;
	static int[] distance;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		adjList = new Node[V + 1];
		distance = new int[V + 1];
		visited = new boolean[V + 1];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		dijkstra();
		
		for(int i = 1; i <= V; i++) {
			sb.append(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]).append("\n");
		}
		System.out.println(sb);
		
		br.close();
	}

	private static void dijkstra() {
		
		// 01. 최단거리 정보를 무한대로 채워 넣기
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 02. 최단거리를 구할 기준 정점의 최단 거리를 0으로 설정
		distance [K] = 0;
		
		for(int i = 0; i < V; i++) {
			// 03. 방문하지 않은 정점 중 distance 값이 가장 작은 정점 정보 꺼내오기
			int cur = 0;  // 정점 번호를 담을 변수
			int min = Integer.MAX_VALUE;  // distance의 값 중 최솟값이 무엇인지 비교
			
			for(int j = 1; j <= V; j++) {
				if(!visited[j] && distance[j] < min) {
					cur = j;
					min = distance[j];
				}
			}
			
			// 더이상 방문 가능한 정점이 없는 경우 종료
			if(cur == 0) break;
			
			// 04. 방문체크 = K => 해당 정점(cur)의 최단거리 확정
			visited[cur] = true;
			
			// 05. 현재 정점(cur)에서 이동가능한 정점들의 최단거리 정보 갱신
			for(Node n = adjList[cur]; n != null; n = n.next) {
				// to 정점이 방문처리 되지 않았고, 기존 to 정점의 최단거리보다 cur 정점의 최단거리 + cur=>to 까지의 가중치
				if(!visited[n.to] && distance[n.to] > distance[cur] + n.weight) {
					distance[n.to] = distance[cur] + n.weight;  // to 정점까지의 최단거리 갱신
				}
			}
		}
	}
	
}
