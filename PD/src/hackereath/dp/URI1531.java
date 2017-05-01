package hackereath.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by C.Lucas on 30/04/2017.
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1531
 * https://en.wikipedia.org/wiki/Pisano_period
 * http://crbonilha.com/pt/contest-delta-editorial-pt2/#more-97
 */
public class URI1531 {


    public static long modularMultiply(long a, long b, long m) {
        return ((a % m) * (b % m)) % m;
    }

    public static long modularSum(long a, long b, long m) {
        return ((a % m) + (b % m)) % m;
    }

    public static long multiply(long [][] f, long [][] g, long m) {
        long a = modularSum(modularMultiply(f[0][0], g[0][0], m), modularMultiply(f[0][1], g[1][0], m), m);
        long b = modularSum(modularMultiply(f[0][0], g[0][1], m), modularMultiply(f[0][1], g[1][1], m), m);
        long c = modularSum(modularMultiply(f[1][0], g[0][0], m), modularMultiply(f[1][1], g[1][0], m), m);
        long d = modularSum(modularMultiply(f[1][0], g[0][1], m), modularMultiply(f[1][1], g[1][1], m), m);
        f[0][0] = a;
        f[0][1] = b;
        f[1][0] = c;
        f[1][1] = d;
        return f[0][1];
    }

    public static final long g[][] = {{1,1},{1,0}};

    public static Map<Long, Long> fib;

    public static long nthFibonacci(long [][] matrix, long n, long mod) {
        if(n < 2)
            return matrix[0][1];
        long k = n/2;
        nthFibonacci(matrix, k, mod);
        multiply(matrix, matrix, mod);
        if( (n&1) == 1) {
            multiply(matrix, g, mod);
        }
        return matrix[0][1];

    }

    public static void exec() {
        for(long i=3; i<10000; i++) {
            long matrix [][] = { {1,1}, {1,0}};
            System.out.printf("%d %d\n", i, nthFibonacci(matrix, i, 1000000007L));
        }
    }

    public static void main(String[] args) {
        //exec();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String in = "";
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
            while( (in = reader.readLine()) != null ) {
                StringTokenizer stringTokenizer = new StringTokenizer(in, " ");
                long value  = Long.parseLong(stringTokenizer.nextToken());
                long mod    = Long.parseLong(stringTokenizer.nextToken());
                long matrix [][] = {{1,1},{1,0}};
                long result = nthFibonacci(matrix, value, mod);
                matrix = new long[][]{{1,1},{1,0}};
                result = nthFibonacci(matrix, result, mod);
                printWriter.printf("%d\n", result);
                fib.put(value, result);
            }
        } catch (Exception ioex) {

        }
    }
}
