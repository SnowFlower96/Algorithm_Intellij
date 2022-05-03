package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_SW_D4_1223_계산기2 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_1223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			int sum = 0;

			Integer.parseInt(br.readLine());
			// 중위표기식 입력
			String input = br.readLine();
			
			// 후위표기식으로 변경
			String output = convert(input);
			
			// 후위표기식 계산
			sum = calc(output);
			
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}

		System.out.println(sb.toString());

		br.close();
	}
	
	public static String convert(String input){
		StringBuilder result = new StringBuilder();  // 결과 출력용
		Stack<Character> symbol = new Stack<>();  // 연산기호 저장 스택
		
		for(int idx = 0; idx < input.length(); idx++) {
			char c = input.charAt(idx);
			
			// 숫자일 때
			if(Character.isDigit(c)) {
				result.append(c);
			}
			// 사칙연산일 때
			else {
				switch (c) {
					case '+':
						while(!symbol.isEmpty()) {
							result.append(symbol.pop());
						}
					case '*':
						while(!symbol.isEmpty()) {
							if(symbol.peek() == '*') {
								result.append(symbol.pop());
							}
							else break;
						}
					default:
						symbol.push(c);
						break;
				}
			}
		}

		while(!symbol.empty()) {
			result.append(symbol.pop());
		}
		
		return result.toString();
	}

	// 후위표기식 계산
	public static int calc(String output) {
		Stack<Integer> buffer = new Stack<>();
		
		// 후위표기식의 앞에서부터 진행
		for(int i = 0; i < output.length(); i++) {
			char c = output.charAt(i);

			// 피연산자이면
			if(Character.isDigit(c)) {
				buffer.push(c - '0');
				continue;
			}
			
			// 연산자이면
			int x = buffer.pop();
			int y = buffer.pop();
			
			if(c == '+') {
				buffer.push(x + y);
			}
			else if(c == '*') {
				buffer.push(x * y);
			}
		}
		
		return buffer.pop();
	}
}
