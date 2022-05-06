package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_2493_탑 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] answer = new int[N + 1];
		Stack<int[]> stack = new Stack<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			// 타워 높이 입력
			int height = Integer.parseInt(st.nextToken());

			// 스택이 비어있으면, 현재 타워 높이와 번호 입력 후 continue
			if (stack.isEmpty()) {
				stack.push(new int[] { height, i });
				continue;
			} 
			
			while (true) {
				// 스택이 비어있으면, 현재 타워 높이와 번호 입력
				if (stack.isEmpty()) {
					stack.push(new int[] { height, i });
					break;
				}

				// 스택의 top의 높이가 현재 타워의 높이보다 높으면 -> 신호 수신 가능
				if (stack.peek()[0] > height) {
					answer[i] = stack.peek()[1];
					// 다음 타워를 위해 stack에 push
					stack.push(new int[] { height, i });
					break;
				}
				
				// 현재 타워의 높이가 스택의 top의 높이보다 높으면 -> 신호 수신 불가능
				// 신호 수신 불가능한 타워 pop
				stack.pop();
			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(answer[i] + " ");
		}
		System.out.println(sb.toString().trim());

		br.close();

	}

}
