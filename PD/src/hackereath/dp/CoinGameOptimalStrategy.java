package hackereath.dp;

/**
 * Created by C.Lucas on 01/05/2017.
 * http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
 *
 * As explicacoes da minha compreensão estão no meu caderno
 */
public class CoinGameOptimalStrategy {


    /**
     *
     * 2) casos
     *
     * 1-
     * PLayer A escolhe a i-esima moeda, o player B pode
     * escolher a (i+1)-esima modea ou a j-esima moeada
     * Ti1 = (i+1, j-1), o player B escolheu a j-esima moeada
     * sombra escolher ou a (i+1) ou a (j-1)-esima moeda
     * Ti2 = (i+2, j), o player B escolher a i-esima modeda,
     * somra escolher a (i+2) ou j-esima moeda
     * Vi + MIN (Ti1, Ti2)
     *
     * 2-
     * Player A escolher a j-esima moeda (a da ponta direita), sobrando
     * a i-esima moeda ou (j-1)-esima para o player B, que vai escolher
     * a moeda de tal forma que minimize o valor para o player A escolher
     *
     * Assim
     * Tj1 = (i, j-2), caso o player B escolha a (j-1)-esima moeda
     * Tj2 = (i+1, j-1), caso o player B escolha a i-esima moeda
     * Vj + MIN (Tj1, Tj2)
     *
     * Formula de recorrencia
     *
     * F(i, j) = devolve o valor maximo que se pode coletar do conjunto de moedas
     * F(i, j) = MAX(Vi + MIN (Ti1, Ti2), Vj + MIN (Tj1, Tj2) )
     *
     * Caso Base
     *
     * */

    private static int max(int a, int b)  {    return a > b ? a : b;  }
    private static int min(int a, int b)  {    return a < b ? a : b;  }

    private static int solver(int [] coins) {
        int q = coins.length;
        int [][] memo = new int[q][q];
        int i, j, k;
        for(k=0; k<q; k++) {
            for(i=0, j=k; j<q; i++, j++) {
                // player B escolher a (i+1)th moeda
                int x = (i+2) <= j ? memo[(i+2)][j] : 0;
                // player B escolher a (j)-esima moeda
                int y =  (i+1) <= (j-1) ? memo[(i+1)][(j-1)] : 0;
                // player B escolheu a (j-1)-esima moeda
                int z = i <= (j-2) ? memo[i][(j-2)] : 0;
                memo[i][j] = max(coins[i] + min(x, y), coins[j] + min(y, z));
            }
        }
        return memo[0][q-1];
    }

    public static void main(String[] args) {

    }
}
