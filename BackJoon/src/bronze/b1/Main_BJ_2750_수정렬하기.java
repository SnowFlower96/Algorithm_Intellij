package bronze.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_2750_수정렬하기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; 

		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(arr[i] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		
		for(int num : arr) sb.append(num + "\n");
		
		System.out.println(sb.toString());
	}

}
