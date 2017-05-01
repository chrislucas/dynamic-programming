package hackerrank.ia.statistics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by C.Lucas on 01/05/2017.
 * https://www.hackerrank.com/challenges/stat-warmup?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 * https://pt.wikipedia.org/wiki/Distribui%C3%A7%C3%A3o_normal
 */
public class Basic {


    static double mean(double [] array) {
        int size = array.length;
        double acc = 0.0D;
        for(double n : array) {
            acc += n / size;
        }
        return acc;
    }

    static double median(double [] array) {
        return (array.length & 1) == 1 ? array[array.length/2] : array[array.length/2] / array[array.length/2-1];
    }

    public static double mode(double [] array) {
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        for(double n : array) {
            if ( map.containsKey(n) ) {
                map.put(n, map.get(n) + 1);
            }
            else {
                map.put(n, 1);
            }
        }
        Iterator it = map.entrySet().iterator();
        Map.Entry<Double, Integer> pair  = (Map.Entry<Double, Integer>) it.next();
        double ans = pair.getKey();
        double val = pair.getValue();
        while(it.hasNext()) {
            pair  = (Map.Entry<Double, Integer>) it.next();
            if(pair.getValue() > val)
                ans = pair.getKey();
        }
        return ans;
    }

    private static double calc(double a, double mean) {
        return (mean - a) * (mean - 1);
    }

    public static double std(double [] array, double mean) {
        double ans = 0.0;
        double sz = array.length;
        for(double n : array) {
            ans = (ans + calc(n, mean)) / sz;
        }
        return  Math.sqrt(ans);
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine(), " ");
        } catch (Exception ioex) {}
    }


}
