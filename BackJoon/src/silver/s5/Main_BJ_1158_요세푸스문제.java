package silver.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1158_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력값 저장
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		getWithQueue(N, K);
		getWithList(N, K);

		br.close();
	}

	public static void getWithQueue(int N, int K) {
		StringBuilder sb = new StringBuilder();

		// 사람수만큼의 큐
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			queue.offer(i + 1);
		}

		// 요세푸스 순열 구하기
		sb.append("<");
		while (!queue.isEmpty()) {
			// K 번째의 사람 전까지 queue 반복
			for (int i = 0; i < K - 1; i++) {
				int temp = queue.poll();
				queue.offer(temp);
			}

			// K 번째 사람
			sb.append(queue.poll());

			// 다음에도 가져올 값이 있으면(=큐가 비어있지 않으면) , 추가
			if (!queue.isEmpty())
				sb.append(", ");
		}
		sb.append(">");

		// 결과 출력
		System.out.println(sb.toString());
	}

	public static void getWithList(int N, int K) {
		StringBuilder sb = new StringBuilder();

		// 사람수만큼의 리스트
		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list.add(i + 1);
		}

		// 요세푸스 순열 구하기
		int idx = 0;
		sb.append("<");
		while (!list.isEmpty()) {
			idx = (idx + K - 1) % list.size();
			// K 번째의 사람을 결과에 추가
			sb.append(list.remove(idx));

			// 사람이 있으면 현재 인덱스를 기준으로 다음에 제거할 사람 인덱스 계산
			if(!list.isEmpty()) sb.append(", ");
		}
		sb.append(">");

		// 결과 출력
		System.out.println(sb.toString());
	}

}
