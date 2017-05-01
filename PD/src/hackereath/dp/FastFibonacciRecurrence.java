package hackereath.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by C.Lucas on 28/04/2017.
 *
 * Referencia
 * http://codeforces.com/blog/entry/14516
 * http://codeforces.com/blog/entry/14385
 * http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
 * TODO
 * https://www.hackerearth.com/pt-br/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/angry-neighbours/description/
 */
public class FastFibonacciRecurrence {

    static HashMap<Long, Long> results;


    static {
        results = new HashMap<Long, Long> ();
        results.put(0L,0L);
        results.put(1L,1L);
    }

    public static final int MOD = 1000000007;

    private static long modularMultiply(long a, long b, long m) {
        return ((a % m) * (b % m)) % m;
    }

    private static long modularSum(long a, long b, long m) {
        return ((a % m) + (b % m)) % m;
    }

    /**
     * Fonte
     * http://codeforces.com/blog/entry/14516?mobile=true
     * An efficient way to solve some counting problems without matrix multiplication
     * http://codeforces.com/blog/entry/14385
     *
     * */
    public static long nth(long n) {
        if(/*n==1 || n==2*/ n < 2)
            return results.get(n);
        else {
            long k = n/2;
            if( (n&1) == 0) {
                // n = 2 * k
                // formula original
                // long result  = ( ( nth(n) * nth(n) ) + ( nth(n-1) * nth(n-1) ) ) % MOD;
                long a = nth(k);
                long b = nth(k);
                long c = nth(k-1);
                long d = nth(k-1);
                long result = (a * b + c * d) % MOD;
                results.put(n, result);
                return result;
            }
            else {
                // n = 2 * k + 1
                // formula original
                //long result  = ( ( nth(n) * nth(n+1) ) + ( nth(n-1) * nth(n) ) ) % MOD;
                long a = nth(k);
                long b = nth(k+1);
                long c = nth(k-1);
                long d = nth(k);
                long result = (a * b + c * d) % MOD;
                results.put(n, result);
                return result;
            }
        }
    }

    /**
     * http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
     * Uma outra abordagem seguindo a seguinte recorrencia
     * Se o N for par
     * K = n/2
     * F(n) = [2 * F(K-1) + F(K)] * F(K)
     *
     * Se o n-esimo numero for imçar
     * K = (n+1)/2
     * F(n) = F(K)*F(K) + F(K-1)*F(K-1)
     * */

    public static long nth2(long n) {
        if(n < 3) {
            return 1L;
        }
        else {
            long k = ((n&1)==1) ? (n+1)/2 : n/2;
            return ((n&1)==1) ? nth2(k) * nth2(k) + nth2(k-1) * nth2(k-1) : (2*nth2(k-1) + nth2(k)) * nth2(k);
        }
    }

    public static long nth3(long n) {
        if(n < 3) {
            return 1L;
        }

        else if(results.containsKey(n)) {
            return results.get(n);
        }

        else {
            long k = ((n&1)==1) ? (n+1)/2 : n/2;
            long a, b, c, d;
            if( ((n&1)==1) ) {
                a = nth3(k);
                b = nth3(k);
                c = nth3(k-1);
                d = nth3(k-1);
                long result = modularSum(modularMultiply(a,b,MOD), modularMultiply(c,d,MOD), MOD);
                results.put(n, result);
                return result;
            }
            else {
                a = 2*nth3(k-1);
                b = nth3(k);
                c = nth3(k);
                long result = modularMultiply(modularSum(a, b, MOD), c, MOD);
                results.put(n, result);
                return result;
            }
        }
    }

    public static void execCFApproach() {
        for(int i=2; i<31; i++) {
            nth(i);
        }
        if(results.size()>0) {
            Iterator<Map.Entry<Long, Long>> it = results.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair =  it.next();
                System.out.printf("%d %d\n", pair.getKey(), pair.getValue());
            }
        }
    }

    public static void execGFGApproach() {
        for(long i=1; i<282; i++) {
            System.out.printf("%d %d\n", i,  nth3(i));
        }
    }

    public static void main(String [] args) {
        execGFGApproach();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cases = Integer.parseInt(bufferedReader.readLine());
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
            while (cases>0) {
                long val =  Long.parseLong(bufferedReader.readLine());
                printWriter.printf("%d\n", nth(val+1));
                cases--;
            }
        } catch (Exception excp) {}
    }

}
