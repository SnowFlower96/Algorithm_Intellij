package silver.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_1992_쿼드트리 {

	static String[] image;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 이미지 크기 입력
		int N = Integer.parseInt(br.readLine());
		image = new String[N];
		
		// 이미지 입력
		for(int i = 0; i < N; i++) {
			image[i] = br.readLine();
		}
		
		System.out.println(quadTree(0, 0, N));
	}

	private static String quadTree(int r, int c, int N) {
		// 분할정복 출력용 StringBuilder
		StringBuilder sb = new StringBuilder();
		
		// 크기가 2일 때
		if(N == 2) {
			String temp = zip(r, c);  // 4개의 화소를 압축
			if(temp.length() == 1)  // 1개의 숫자로 압축이 될 때
				return temp;
			else  // 1개의 숫자로 압축이 안될 때
				return sb.append("(").append(temp).append(")").toString();
		}
		
		int half = N / 2;  // 중앙값
		// 좌상
		sb.append(quadTree(r, c, half));
		// 우상
		sb.append(quadTree(r, c + half, half));
		// 좌하
		sb.append(quadTree(r + half, c, half));
		// 우하
		sb.append(quadTree(r + half, c + half, half));
		
		// 우측 하단까지 사분면을 다 보고나서
		// 사분면이 4개의 같은 숫자로 이루어져 있으면, 맨 앞만 반환
		if(check(sb.toString())) {
			sb.setLength(1);
		}
		// 사분면이 다른 숫자로 이루어져 있으면, 괄호 입력
		else {
			sb.insert(0, "(").append(")");
		}
		
		return sb.toString();
	}
	
	// 4개의 숫자를 압축
	private static String zip(int r, int c) {
		StringBuilder str = new StringBuilder();
		
		// 4개의 숫자 연결
		for(int i = r; i < r + 2; i++) {
			for(int j = c; j < c + 2; j++) {
				str.append(image[i].charAt(j));
			}
		}
		
		// 같은 숫자로 이루어져 있으면 맨 처음 숫자만
		if(check(str.toString())) str.setLength(1);
		
		return str.toString();
	}
	
	// 화소가 같은 값으면 true 반환
	private static boolean check(String str) {
		char temp = str.charAt(0);
		for(int i = 1; i < str.length() ; i++) {
			if(temp != str.charAt(i)) return false;
		}
		
		return true;
	}

}
