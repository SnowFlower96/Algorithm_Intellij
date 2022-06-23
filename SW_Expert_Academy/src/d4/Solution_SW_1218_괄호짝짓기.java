package d4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_SW_1218_괄호짝짓기 {

	//  문자 종류
	public static final char[] inChar = {'(', '[', '{', '<'};
	public static final char[] outChar = {')', ']', '}', '>'};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input_1218.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			Integer.parseInt(br.readLine());
			String input = br.readLine();
			System.out.println("#" + tc + " " + (check(input) ? "1" : "0"));
		}
		
	}
	
	public static boolean check(String input) {
		Stack<Character> stack = new Stack<Character>();
		
		// 입력 문자 하나마다 진행
		for(int i = 0; i < input.length(); i++) {
			char element = input.charAt(i);
			
			// 문자가 (,[,{,< 중 하나일 때
			if(isIn(element, inChar)) {
				stack.push(element);
			}
			// 문자가 ),],},> 중 하나일 때 
			else if(isIn(element, outChar)) {
				// 스택이 비어있다면
				if(stack.isEmpty()) return false;
				
				char top  = stack.pop();
				if(top == '(' && element == ')') continue;
				else if(top == '[' && element == ']') continue;
				else if(top == '{' && element == '}') continue;
				else if(top == '<' && element == '>') continue;
				return false;
			}
		}
		
		return true;
	}

	public static boolean isIn(char c, char[] chars) {
		for(char i : chars) {
			if(i == c) return true;
		}
		return false;
	}
}
