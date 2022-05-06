package silver.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 월말평가 4차 1번
 * @author jhno96
 * @date 2022. 4. 25.
 */
public class Main_BJ_1755_숫자놀이 {

	// 숫자를 영어로 변환할 배열
		static final String[] toEng = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		
		// 숫자와 해당 숫자를 영어로 변환한 정보를 가지고 있는 클래스
		// 사전순으로 정렬하기 위해 Comparable 인터페이스로 compareTo 구현
		static class Number implements Comparable<Number>{
			int num;
			String eng;
			
			// 숫자만을 입력받는 생성자
			public Number(int num) {
				super();
				this.num = num;
				this.eng = convert(num);
			}

			// 두자릿수는 각 자리마다 영어로 변환해야 되기 때문에 함수로 만들어 변환
			private String convert(int input) {
				eng = "";
				if(input >= 10) {
					eng += toEng[input / 10] + " ";
					input %= 10;
				}
				eng += toEng[input];
				
				return eng;
			}

			@Override
			public String toString() {
				return "Number [" + num + ", " + eng + "]";
			}

			@Override
			public int compareTo(Number o) {
				// String의 compareTo
				return this.eng.compareTo(o.eng);
			}
			
		}
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			// 입력을 받을 배열, 크기는 M이상 N이하이므로 N - M + 1
			Number[] arr = new Number[N - M + 1];
			
			// Number 배열에 M이상 N이하의 수들을 Number 객체로 입력
			int idx = 0;
			for(int i = M; i <= N; i++) {
				arr[idx++] = new Number(i);
			}
			
			Arrays.sort(arr);  // 사전순으로 정렬
			
			// 정답으로 출력하기 위해 모든 Number 배열의 값을 StringBuilder에 추가
			for(int i = 1; i <= arr.length; i++) {
				sb.append(arr[i - 1].num);
				
				// 한 줄에 10개씩 출력하기 위해 10의 배수마다 줄바꿈 추가
				if(i % 10 == 0) sb.append("\n");
				else sb.append(" ");
			}
			
			System.out.println(sb);
		}

}
