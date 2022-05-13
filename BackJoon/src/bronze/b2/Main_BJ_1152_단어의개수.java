package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-05-12
 */
public class Main_BJ_1152_단어의개수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int cnt = 0;
        boolean flag = false;
        for (int i = 0; i < input.length(); i++) {
            if(!flag && (Character.isAlphabetic(input.charAt(i)))) {
                cnt++;
                flag = true;
            }
            if (flag && input.charAt(i) == ' ') {
                flag = false;
            }
        }

        System.out.println(cnt);
    }

}
