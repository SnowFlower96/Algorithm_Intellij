import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Jungol_1681_해밀턴순환회로 {

	static int N, ans;
	static int[][] map;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		isVisited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		isVisited[0] = true;
		dfs(0, 0, 0);
		
		System.out.println(ans);
		
		br.close();
	}

	public static void dfs(int cur, int cnt, int cost) {
		// 지금까지의 비용이 정답보다 많으면 가지치기
		if(cost >= ans) return;
		
		// 모든 점을 방문하면
		if(cnt == N - 1) {
			// 회사로 돌아올 수 없는 경우
			if(map[cur][0] == 0) return;
			
			cost += map[cur][0];  // 회사 복귀로의 비용
			ans = Math.min(ans, cost);
			return;
		}
		
		
		for(int i = 0; i < N; i++) {
			// 다음 정점에 방문하지 않았고 다음 저점으로 갈 수 있을때 
			if(!isVisited[i] && map[cur][i] != 0) {				
				isVisited[i] = true;  // 방문표시
				dfs(i, cnt + 1, cost + map[cur][i]);
				isVisited[i] = false;  // 방문해제
			}
		}
	}
}
