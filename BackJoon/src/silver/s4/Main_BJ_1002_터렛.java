package silver.s4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1002_터렛 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());

			double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

			// 무한히 만날 때
			if (distance == 0 && r1 == r2) {
				bw.write("-1");
			}
			// 두 점에서 만날 때
			else if (distance > Math.abs(r2 - r1) && distance < r1 + r2) {
				bw.write("2");
			}
			// 한 점에서 만날 때
			else if (distance == r1 + r2 || distance == Math.abs(r2 - r1)) {
				bw.write("1");
			}
			// 만나지 않을 때
			else if (distance > r1 + r2 || distance < Math.abs(r1 - r2) || distance == 0) {
				bw.write("0");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
