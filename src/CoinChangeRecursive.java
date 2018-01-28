import java.util.Stack;

public class CoinChangeRecursive {
    public static void main(String[] args) {
        int[] coins = {1, 3, 2};
        System.out.println(makeChange(coins, 4, 0));
          //int[] coins = {2, 5, 3, 6};
          //System.out.println(makeChange(coins, 10, 0));
    }

    private static int makeChange(int[] coins, int sum, int index) {
        if (sum < 0) {
            return 0;
        }
        if (sum == 0) { // also takes care of the case when index == coins.length
            return 1;
        }
        if (index == coins.length && sum > 0) {
            return 0;
        }
        // we either choose the coin @ index once or we don't choose coin @ index and move onto the next coin
        return makeChange(coins, sum - coins[index], index) + makeChange(coins, sum, index + 1);
    }

}

