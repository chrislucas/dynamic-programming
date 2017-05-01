package hackereath.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * Created by C.Lucas on 30/04/2017.
 */
public class WinTheGame {

    public static double solver1(int r, int g) {
        double [][] memo = new double[r+1][g+1];
        for(int i=0; i<=r; i++) {
            memo[i][0] = 1.0D;
        }
        for(int i=0; i<=g; i++) {
            memo[0][i] = 1.0D;
        }
        for(int i=1; i<=r; i++) {
            for (int j=1; j<=g ; j++) {
                /**
                 * Se nao tiver bolas vermelhas ou nao tiver bolas
                 * verdes, a vitoria eh 100% do player 'A'
                 * */
                //if(i==0 || j==0) {memo[i][j] = 1.0D;}
                double pR  = (double)i/(i+j);             // probabilidade de pegar um bola R
                double pG  = (double)j/(i+j);             // idem bola G
                double pmR = (double)(i-1)/(i+j-1);       // probabilidade de pegar uma bola R depois de retirar uma bola R
                double pmG = (double)(j-1)/(i+j-1);       // probabilidade de pegar uma bola G depois de retirar uma bola G
                memo[i][j] = pR + pG * pmG * memo[i][j-1];
            }
        }
        return memo[r][g];
    }

    public static double solver2(int r, int g) {
        if(r ==0 || g == 0)
            return 1.0D;
        double [] dp = new double[g+1];
        dp[0] = 1.0D;
        dp[1] = (double)r/(r+g);
        for (int qGreen=2; qGreen<=g ; qGreen++) {
            double pR  = (double)r/(r+qGreen);                  // probabilidade de pegar um bola R
            double pG  = (double)qGreen/(r+qGreen);             // idem bola G
            double pmG = (double)(qGreen-1)/(qGreen+r-1);       // probabilidade de pegar uma bola G depois de retirar uma bola G
            dp[qGreen] = pR + pG * pmG * dp[qGreen-2];
        }
        return dp[g];
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out), true);
            NumberFormat numberFormat = new DecimalFormat("0.000000");
            int cases = Integer.parseInt(reader.readLine());
            while(cases>0) {
                StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine(), " ");
                int r  = Integer.parseInt(stringTokenizer.nextToken());
                int g  = Integer.parseInt(stringTokenizer.nextToken()
                );
                //double ans1 = solver1(r, g);
                double ans2 = solver2(r, g);
                printWriter.printf("%s\n", numberFormat.format(ans2));
                cases--;
            }
        } catch (Exception ioex) {}
    }
}
