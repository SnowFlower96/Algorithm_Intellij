import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_Jungol_1828_냉장고 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());  // 화학물질의 수 
		int[][] material = new int[N][2];  // 화학물질별 최저 보관온도 및 최고 보관온도 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			material[i][0] = Integer.parseInt(st.nextToken());
			material[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 최고온도 오름차순 정렬(최고온도가 같으면 최저온도 오름차순)
		Arrays.sort(material, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 최고 온도가 같으면
				if(o1[1] == o2[1]) return o1[0] - o2[0];
				
				// 최고 온도 오름차순
				return o1[1] - o2[1];
			}
		});
		
		// 결과 출력
		System.out.println(solution(material));
		
		br.close();
	}
	
	public static int solution(int[][] material) {
		int result = 1;  // 기본 1개
		
		int max = material[0][1];  // 첫 번째 재료의 최대 온도
		// 두 번째 재료부터
		for(int i = 1; i < material.length; i++) {
			// 최저온도가 이전 최고 온도보다 높으면 냉장고 사용 불가능
			if(material[i][0] > max) {
				max = material[i][1];
				result++;
			}
		}
		
		return result;
	}

}
