package d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SW_1228_암호문1 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_1228.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 암호문 저장 리스트
		List<Integer> list = new LinkedList<>();
		
		for(int tc = 1; tc <= 10; tc++) {
			// 초기 암호문 입력
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령어 입력
			int num = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "I");
			// 명령어 갯수만큼 수행
			for(int i = 0; i < num; i++) {
				StringTokenizer st2 = new StringTokenizer(st.nextToken());
				int idx = Integer.parseInt(st2.nextToken());  // 삽입 인덱스
				int cnt = Integer.parseInt(st2.nextToken());  // 삽입할 숫자의 갯수
				// 삽입할 숫자만큼 수행
				for(int j = 0; j < cnt; j++) {
					int val = Integer.parseInt(st2.nextToken());
					list.add(idx++, val);
				}
			}
			
			// 결과 저장
			sb.append("#").append(tc);
			for(int i = 0; i < 10; i++) {
				sb.append(" ").append(list.get(i));
			}
			sb.append("\n");
			
			// 리스트 초기화
			list.clear();
		}
		
		// 최종 출력
		System.out.println(sb.toString());
		
		br.close();
	}

}
