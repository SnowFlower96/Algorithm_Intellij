package gold.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 월말평가 4차 2번
 * @author jhno96
 * @date 2022. 4. 25.
 */
public class Main_BJ_2629_양팔저울 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// N개의 추 입력
		int N = Integer.parseInt(br.readLine());
		int[] weights = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		// M개의 구슬 입력
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			// 각 구슬의 입력에 따라 무게 확인이 가능한지 계산
			int input = Integer.parseInt(st.nextToken());

			sb.append(solution(N, weights, input, 0, 0, false) ? "Y" : "N").append(" ");
		}
		
		sb.setLength(sb.length() - 1);  // 마지막의 " "제거
		System.out.println(sb);
	}

	/**
	 * 각 추를 고려할 때, 왼쪽에 추 놓기, 오른쪽에 추 놓기, 추를 놓지 않기 세 가지 상황을 재귀문을 이용하여 해결
	 * @param N 추의 수
	 * @param weights 각 추의 무게
	 * @param left 왼쪽 저울의 무게
	 * @param right 오른쪽 저울의 무게
	 * @param cnt 고려한 추의 수
	 * @param flag 구슬의 무게를 확인 가능한지
	 * @return 구슬의 무게를 확인 가능한지를 나타내는 flag 변수
	 */
	private static boolean solution(int N, int[] weights, int left, int right, int cnt, boolean flag) {		
		// 왼쪽과 오른쪽 저울이 같거나 구슬의 무게를 확인 가능할 때
		if(left == right) flag = true;
		
		// 모든 추를 고려했을 때 혹은 추의 무게를 확인 할 수 있을 때
		if(cnt == N || flag) return flag;

		// 왼쪽에 놓기
		flag = solution(N, weights, left + weights[cnt], right, cnt + 1, flag);
		
		// 오른쪽에 놓기
		flag = solution(N, weights, left, right + weights[cnt], cnt + 1, flag);
		
		// 놓지 않기
		flag = solution(N, weights, left, right, cnt + 1, flag);
		
		return flag;
	}

}
