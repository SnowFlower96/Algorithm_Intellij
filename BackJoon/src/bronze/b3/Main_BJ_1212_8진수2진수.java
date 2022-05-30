package bronze.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author : jhno96
 * @Date : 2022-05-18
 */
public class Main_BJ_1212_8진수2진수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            String temp = Integer.toBinaryString((input.charAt(i) - '0'));
            if(temp.length() == 2 && i != 0) temp = "0" + temp;
            else if(temp.length() == 1 && i != 0) temp = "00" + temp;
            sb.append(temp);
        }
        System.out.println(sb);
    }

}
