package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9205_맥주마시면서걸어가기 {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			Node start = null;
			List<Node> nodeList = new ArrayList<>();
			Node dest = null;
			for(int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(i == 0) start = new Node(x, y);
				else if(i == n + 1) dest = new Node(x, y);
				else nodeList.add(new Node(x, y));
			}
			
			System.out.println(isAvailable(n, start, nodeList, dest) ? "happy" : "sad");
		}
	}
	
	private static boolean isAvailable(int n, Node start, List<Node> nodeList, Node dest) {		
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[n];
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(getDistance(cur, dest) <= 1000) return true;
			
			for(int i = 0; i < n; i++) {
				if(visited[i]) continue;
				
				Node nn = nodeList.get(i);
				if(getDistance(cur, nn) <= 1000) {
					visited[i] = true;
					queue.offer(nn);
				}
			}
		}
		
		return false;
	}

	private static int getDistance(Node n1, Node n2) {
		return Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y);
	}

}
