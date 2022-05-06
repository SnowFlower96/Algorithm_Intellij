package silver.s3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1244_스위치켜고끄기 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_1244.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// 스위치 수, 스위치 배열, 학생 수 입력
		int N = Integer.parseInt(br.readLine());
		boolean[] arr = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			if(st.nextToken().equals("1")) arr[i] = true;
			else arr[i] = false;
		}
		int num_student = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < num_student; i++) {
			st = new StringTokenizer(br.readLine());
			String token = st.nextToken();

			// 남자
			if (token.equals("1")) {
				int idx = Integer.parseInt(st.nextToken());
				for (int j = idx - 1; j < N; j += idx) {
					// 뒤집기
					arr[j] = !arr[j];
				}
			}
			// 여자
			else if (token.equals("2")) {
				int idx = Integer.parseInt(st.nextToken()) - 1;  // 시작 인덱스
				int n = 0;  // 인덱스 이동
				while(idx - n >= 0 && idx + n < N) {
					// 대칭인지 체크
					if(arr[idx - n] != arr[idx + n]) break;
					else {
						arr[idx - n] = arr[idx + n] = !arr[idx + n];
					}
					n++;
				}
			}
		}

		// 결과 출력
		for(int i = 0; i < arr.length; i++){
			if(i%20==0 && i != 0)
                sb.append("\n");
            if (arr[i]) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }
        bw.write(sb.toString());
        
		br.close();
		bw.close();
	}
	
}
