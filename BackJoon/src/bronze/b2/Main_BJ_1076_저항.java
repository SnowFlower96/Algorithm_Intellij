package bronze.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @Author : jhno96
 * @Date : 2022-05-03
 */
public class Main_BJ_1076_저항 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String r1 = br.readLine();
        String r2 = br.readLine();
        String r3 = br.readLine();

        HashMap<String, int[]> register = new HashMap<>();
        register.put("black", new int[] { 0, 1 });
        register.put("brown", new int[] { 1, 10 });
        register.put("red", new int[] { 2, 100 });
        register.put("orange", new int[] { 3, 1000 });
        register.put("yellow", new int[] { 4, 10000 });
        register.put("green", new int[] { 5, 100000 });
        register.put("blue", new int[] { 6, 1000000 });
        register.put("violet", new int[] { 7, 10000000 });
        register.put("grey", new int[] { 8, 100000000 });
        register.put("white", new int[] { 9, 1000000000 });

        sb.append(register.get(r1)[0]).append(register.get(r2)[0]);
        long answer = ((long) register.get(r1)[0] * 10 + register.get(r2)[0]) * register.get(r3)[1];

        System.out.println(answer);
    }

}
