package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1697_숨바꼭질 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N, K));
	}

	private static int bfs(int n, int k) {
		int ans = 0;
		
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		
		queue.offer(n);
		visited[n] = true;
		outer: while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				int cur = queue.poll();
				
				if(cur == k) break outer;
				
				// x - 1
				if((cur - 1) >= 0 && !visited[cur - 1]) {
					queue.offer(cur - 1);
					visited[cur - 1] = true;
				}
				
				// x + 1
				if((cur + 1) < visited.length && !visited[cur + 1]) {
					queue.offer(cur + 1);
					visited[cur + 1] = true;
				}
				
				// x * 2
				if((cur * 2) < visited.length && !visited[cur * 2]) {
					queue.offer(cur * 2);
					visited[cur * 2] = true;
				}
			}
			
			ans++;
		}
		
		return ans;
	}

}
