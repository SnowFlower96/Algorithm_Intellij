package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-04-29
 */
public class Main_BJ_1032_명령프롬프트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        String[] input = new String[N];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();
        }

        boolean flag = true;
        for (int i = 0; i < input[0].length(); i++) {
            char c = input[0].charAt(i);

            flag = true;
            for (int j = 1; j < N; j++) {
                if(c != input[j].charAt(i)){
                    flag = false;
                    break;
                }
            }
            sb.append(flag ? c : '?');
        }
        
        System.out.println(sb.toString());
    }

}
