package d4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_SW_D4_3124_최소스패닝트리 {

	static class Node implements Comparable<Node> {
		int v;
		long weight;

		public Node(int v, long weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
		
	}
	
	static int V, E;
	static ArrayList<Node>[] list;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_3124.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			list = new ArrayList[V+1];
			for(int i = 0; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				list[from].add(new Node(to, weight));
				list[to].add(new Node(from, weight));
			}
			
			sb.append("#").append(tc).append(" ").append(Kruskal()).append("\n");
		}
		
		// 결과 출력
		System.out.println(sb);
	}

	// Kruskal 알고리즘
	private static long Kruskal() {
		boolean[] isVisited = new boolean[V + 1];
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>(); 
				
		pQueue.offer(new Node(1, 0));
		
		int cnt = 0;
		long result = 0;
		while(!pQueue.isEmpty()) {
			Node cur = pQueue.poll();
			
			// 방문했으면
			if(isVisited[cur.v]) continue;
			
			// 신장트리에 가중치 추가
			result += cur.weight;
			// 신장트리에 추가
			isVisited[cur.v] = true;
			
			// 모든 정점 처리시 종료
			if(++cnt == V) break;
			
			for(int i = 0; i < list[cur.v].size(); i++) {
				Node next = list[cur.v].get(i);
				
				if(isVisited[next.v]) continue;
				pQueue.offer(next);
			}
			
		}
		
		return result;
	}

}
