package gold.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * WS
 * @author jhno96
 * @date 2022. 4. 11.
 */
public class Main_BJ_11401_이항계수3 {

	static final long p = 1000000007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long N = Integer.parseInt(st.nextToken());
		long K = Integer.parseInt(st.nextToken());
		
		System.out.println(solution(N, K));
	}

	private static long solution(long n, long k) {
		long numerator = factorialModular(n);
		long denominator = (factorialModular(k) * factorialModular(n - k)) % p;
		
		return (numerator * powerModular(denominator, p - 2)) % p;
	}
	
	private static long factorialModular(long n) {
		long result = 1;
		
		for(long i = n; i > 0; i--) {
			result = result * i % p;
		}
		
		return result % p;
	}
	
	private static long powerModular(long n, long r) {
		if(r == 1) return n % p;
		
		long temp = powerModular(n, r / 2);
		
		if(r % 2 == 1) return (temp * temp % p) * n % p;
		
		return temp * temp % p;
	}

}
