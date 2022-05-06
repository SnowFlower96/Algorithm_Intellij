package gold.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1759_암호만들기 {

	// 모음
	static final char[] vowel = { 'a', 'e', 'i', 'o', 'u' };
	static int L, C;
	static char[] chars;
	static char[] pwd;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		chars = new char[C];
		pwd = new char[L];
		chars = br.readLine().replace(" ", "").toCharArray();

		// 문자 정렬
		Arrays.sort(chars);
		
		solution(0, 0, 0, 0);

		// 결과 출력
		System.out.println(sb.toString());
		br.close();
	}

	private static void solution(int cnt, int start, int vowelCnt, int consonantCnt) {
		
		if (cnt == L) {
			if(vowelCnt >= 1 && consonantCnt >= 2) sb.append(String.valueOf(pwd)).append("\n");
			return;
		}

		for (int i = start; i < C; i++) {
			pwd[cnt] = chars[i];
			
			if(isVowel(chars[i])) solution(cnt + 1, i + 1, vowelCnt + 1, consonantCnt);
			else solution(cnt + 1, i + 1, vowelCnt, consonantCnt + 1);
		}

		return;
	}

	private static boolean isVowel(char in) {
		for (char c : vowel) {
			if (in == c)
				return true;
		}
		return false;
	}

}
