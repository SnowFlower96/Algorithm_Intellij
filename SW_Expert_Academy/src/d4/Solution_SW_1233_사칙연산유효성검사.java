package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D4_1233_사칙연산유효성검사 {

	static String[] nodes;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			nodes = new String[N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				// 노드 인덱스에 연산기호 혹은 숫자 입력
				nodes[Integer.parseInt(st.nextToken())] = st.nextToken();
			}

			// 결과 연결
			sb.append("#").append(tc).append(" ").append(check(N)).append("\n");
		}
		
		// 결과 출력
		System.out.println(sb.toString());
		
		br.close();
	}

	// 완전 이진 트리는 인덱스가 N / 2보다 크면 리프노드
	private static int check(int N) {
		int middle = N / 2; 
		
		for(int i = 1; i <= N; i++) {
			// 리프노드가 아닐 떄
			if(i <= middle) {
				// 숫자이면 계산 불가능
				if(nodes[i].charAt(0) <= '9' && nodes[i].charAt(0) >= '0') {
					return 0;
				}
			}
			// 리프노드일 때
			else {
				// 숫자가 아니면 계산 불가능
				if(!(nodes[i].charAt(0) <= '9' && nodes[i].charAt(0) >= '0')) {
					return 0;
				}
			}
		}

		return 1;
	}

}
