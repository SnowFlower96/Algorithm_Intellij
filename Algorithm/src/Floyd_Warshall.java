import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 플로이드 와샬 알고리즘
 * @Author : jhno96
 * @Date : 2022-06-30
 */
public class Floyd_Warshall {

    static final int INF = 99999999;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("Algorithm/res/Floyd_Warshall_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 수 입력
        int N = Integer.parseInt(br.readLine());

        // 인접 행렬 초기화
        int[][] D = new int[N][N];

        // 인접 행렬 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                D[i][j] = Integer.parseInt(st.nextToken());
                // 자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF 로 채우기
                if (i != j && D[i][j] == 0) D[i][j] = INF;
            }
        }

        // 입력 출력
        System.out.println("=============입력=============");
        for(int[] row : D) System.out.println(Arrays.toString(row));

        // 플로이드 와샬
        for (int k = 0; k < N; k++) {  // 경유 노드 확인
            for (int i = 0; i < N; i++) {  // 출발지
                if (i == k) continue;  // 출발지와 경유지가 같으면 다음 탐색
                for (int j = 0; j < N; j++) {  // 도착지
                    if (j == i || j == k) continue;  // 출발지와 도착지가 같거나 도착지가 경유지이면 다음 탐색
                    D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);  // 경유하거나 직접가거나 더 짧은 경로로 대체
                }
            }
        }

        // 결과 출력
        System.out.println("=============결과=============");
        for(int[] row : D) System.out.println(Arrays.toString(row));
    }

}
