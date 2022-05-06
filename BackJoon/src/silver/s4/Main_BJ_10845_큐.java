package silver.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author jhno96
 * @date 2022. 4. 4.
 */
public class Main_BJ_10845_큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		int back = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String work = st.nextToken();
			switch (work) {
			case "push":
				int X = Integer.parseInt(st.nextToken());
				back = X;
				queue.offer(X);
				break;
			case "pop":
				sb.append(queue.size() == 0 ? "-1" : queue.poll()).append("\n");
				break;
			case "size":
				sb.append(queue.size()).append("\n");
				break;
			case "empty":
				sb.append(queue.isEmpty() ? "1" : "0").append("\n");
				break;
			case "front":
				sb.append(queue.size() == 0 ? "-1" : queue.peek()).append("\n");
				break;
			case "back":
				sb.append(queue.size() == 0 ? "-1" : back).append("\n");
				break;
			}
		}

		System.out.println(sb);
	}

}
