package d3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * WorkShop
 * @author jhno96
 * @date 2022. 4. 4.
 */
public class Solution_SW_3307_최장증가부분수열 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("res/input_3307.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int ans = solution(N, arr);
			ans = solution2(N, arr);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}


	private static int solution(int N, int[] arr) {
		int[] LIS = new int[N];  // LIS의 동적 테이블
		int max = 0;  // 해당 수열의 최대 길이
		
		for(int i = 0; i < N; i++) {  // 해당 원소를 끝으로 하는 모든 경우 확인   
			LIS[i] = 1;  // 자기 자신만 있는 경우 먼저 입력
			for(int j = 0; j < i; j++) {  // 처음부터 i번째 까지
				if(arr[j] < arr[i]) {  // 증가수열인지 확인
					LIS[i] = Math.max(LIS[i], LIS[j] + 1);  // LIS 동적 테이블 갱신
				}
			}
			
			// 최대값 갱신
			max = Math.max(max, LIS[i]);
		}
		
		return max;
	}

	private static int solution2(int N, int[] arr) {
		int[] C = new int[N];  // 가장 작은 값을 저장할 배열
		int size = 0;  // C의 크기
		for(int i = 0; i < N; i++) {
			int temp = Arrays.binarySearch(C, 0, size, arr[i]);  // C에서 0부터 size 전까지 arr[i]를 기준으로 탐색
			temp = Math.abs(temp) - 1;  // 삽입 위치
			C[temp] = arr[i];  // C의 원소와 LIS의 요소와는 관계 없음
			
			// 삽입 위치의 인덱스와 크기가 같으면 크기 1증가 => 삽입위치가 마지막 위치와 같다
			if(size == temp) size++;
		}
		
		return size;
	}
	
}
