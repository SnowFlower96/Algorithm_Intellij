package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_BJ_1157_단어공부 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Character, Integer> map = new HashMap<>();
		
		char[] input = br.readLine().toCharArray();
		for(char c : input) {
			char key = Character.toUpperCase(c);
			map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
		}
		
		int max = 0;
		char c = ' ';
		for(Character key : map.keySet()) {
			int cnt = map.get(key);
			if (max < cnt) {
				max = cnt;
				c = key;
			}
			else if(max == cnt) {
				max = cnt;
				c = '?';
			}
		}
		System.out.println(c);
		br.close();

	}

}
