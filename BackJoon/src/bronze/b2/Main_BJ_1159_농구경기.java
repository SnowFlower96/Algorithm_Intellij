package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-05-13
 */
public class Main_BJ_1159_농구경기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] alphabet = new int[26];
        System.out.println((int)'z');
        for (int i = 0; i < N; i++) {
            alphabet[br.readLine().charAt(0) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if(alphabet[i] >= 5) sb.append((char)('a' + i));
        }

        System.out.println(sb.length() != 0 ? sb : "PREDAJA");
    }

}
