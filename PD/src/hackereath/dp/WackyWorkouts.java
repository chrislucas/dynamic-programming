package hackereath.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * Created by C.Lucas on 25/04/2017.
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/angry-neighbours/
 * DONE
 */
public class WackyWorkouts {

    public static final int MOD = 1000000007;
    public static final long FIBO_MATRICIAL [][] = {{1,1},{1,0}};

    /**
     * Recorrencia
     * T(N) = T(N-1) + (N - 1)
     * */
    public static long  fx(long n) {
        long acc = 2;
        if(n == 0) {
            return 1;
        }
        else if(n == 1) {
            return 2;
        }
        for (long i = 2; i <=n ; i++) {
            acc =  ((acc % MOD) + ((i - 1)  % MOD)) % MOD;
        }
        return  acc;
    }

    public static long modularMultiply(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    public static long fibonacci(long n) {
        long f[][] = {{1,1},{1,0}};
        long f0[][] = {{1,1},{1,0}};
        for(int i=0; i<=n; i++){
            long a = modularMultiply(f[0][0], f0[0][0]) + modularMultiply(f[0][1], f0[1][0]);
            long b = modularMultiply(f[0][0], f0[0][1]) + modularMultiply(f[0][1], f0[1][1]);
            long c = modularMultiply(f[1][0], f0[0][0]) + modularMultiply(f[1][1], f0[1][0]);
            long d = modularMultiply(f[1][0], f0[0][1]) + modularMultiply(f[1][1], f0[1][1]);
            f[0][0] = a;
            f[0][1] = b;
            f[1][0] = c;
            f[1][1] = d;
        }
        return f[0][1];
    }

    public static long modularSum(long a, long b, long m) {
        return ((a % m) + (b % m)) % m;
    }

    private static long multiplyMatrixFibonacci(long f[][], long g[][]) {
        long a = modularSum(modularMultiply(f[0][0], g[0][0]), modularMultiply(f[0][1], g[1][0]), MOD);
        //modularMultiply(f[0][0], g[0][0]) + modularMultiply(f[0][1], g[1][0]);
        long b = modularSum(modularMultiply(f[0][0], g[0][1]), modularMultiply(f[0][1], g[1][1]), MOD);
        //modularMultiply(f[0][0], g[0][1]) + modularMultiply(f[0][1], g[1][1]);
        long c = modularSum(modularMultiply(f[1][0], g[0][0]), modularMultiply(f[1][1], g[1][0]), MOD);
        //modularMultiply(f[1][0], g[0][0]) + modularMultiply(f[1][1], g[1][0]);
        long d = modularSum(modularMultiply(f[1][0], g[0][1]), modularMultiply(f[1][1], g[1][1]), MOD);
                //modularMultiply(f[1][0], g[0][1]) + modularMultiply(f[1][1], g[1][1]);
        f[0][0] = a;
        f[0][1] = b;
        f[1][0] = c;
        f[1][1] = d;
        return f[0][1];
    }

    private static long nthFibonacciLogn(long matA [][], long p) {
        Stack<Long> stack = new Stack<>();
        for(long i=p; i>1; i/=2) {
            stack.add(i);
        }
        while(!stack.empty()) {
            long i = stack.pop();
            multiplyMatrixFibonacci(matA, matA);
            if((i&1)==1) {
                multiplyMatrixFibonacci(matA, FIBO_MATRICIAL);
            }
        }
        return matA[0][0];
    }


    private static long nthRecFibonacciLogn(long matA [][], long p) {
        if(p == 0 || p==1)
            return matA[0][0];
        nthRecFibonacciLogn(matA, p/2);
        multiplyMatrixFibonacci(matA, matA);
        if((p&1)==1) {
            multiplyMatrixFibonacci(matA, FIBO_MATRICIAL);
        }
        return matA[0][0];
    }


    private static final void exec() {
        for(long i=6; i<1000; i++) {
            long f[][] = {{1,1},{1,0}};
            System.out.printf("%d %d ", i, nthRecFibonacciLogn(f, i));
            long g[][] = {{1,1},{1,0}};
            System.out.printf("%d %d\n", i, nthFibonacciLogn(g, i));
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cases = Integer.parseInt(reader.readLine());
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
            while (cases>0) {
                long val =  Long.parseLong(reader.readLine());
                long f [][] = {{1,1},{1,0}};
                printWriter.printf("%d\n", nthRecFibonacciLogn(f, val+1));
                cases--;
            }
        } catch (Exception excp) {}
    }
}
