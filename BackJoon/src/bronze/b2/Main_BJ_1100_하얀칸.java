package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-05-09
 */
public class Main_BJ_1100_하얀칸 {

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        for (int i = 1; i <= 8; i++) {
            String input = br.readLine();
            int st = i % 2 == 0 ? 1 : 0;
            for (int j = st; j < 8; j+=2) {
                if(input.charAt(j) == 'F') cnt++;
            }
        }

        System.out.println(cnt);
    }

}
