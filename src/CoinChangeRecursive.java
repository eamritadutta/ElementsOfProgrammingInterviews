import java.util.Stack;

public class CoinChangeRecursive {
    public static void main(String[] args) {
        int[] availableCoinValues = {25, 10, 5, 1};
        System.out.println(makeChange(10, availableCoinValues, 0, 0, new Stack<>()));
    }

    private static int makeChange(int valToChange, int[] availableCoinValues, int index, int depth, Stack<Integer> usedCoins) {
        // calculate available options

        // base case - # of ways to make change for any amount using only availablecoinValues[3] == 1 viz., use only 1 cents
        if (index >= availableCoinValues.length -1) { // index >= 4 - 1;   index >= 3
            for (int i=0; i < depth; i++) {
                System.out.print("~");
            }
            System.out.println("Remaining to be changed: " + valToChange + " using coin(s): " + usedCoins.toString());
            usedCoins.empty();

            // 2 cases when we encounter the following return 1 line viz., base case
            // 1. when value remaining to be changed is > 0 - meaning we will use all 1 cents to make up value remaining to be changed. the value remaining does not matter
            // since we can make any number say 13 cents, using 13 1 cents - and that would be only 1 way to make 13 cents using only 1 cents
            // 2. other scenario is we have already made up value to be changed using 25 cents, 10 cents and 5 cents. Hence we will be using 0 # of 1 cent coins.
            return 1;
        }

        int valueOfCoinWeAreUsing = availableCoinValues[index];
        int ways = 0;

        for (int i=0; i * valueOfCoinWeAreUsing <= valToChange; i++) {  // for 25 cents case for changing 5 cents: i = 0; 0 * 25 <= 5;
                                                                        // for 25 cents case for changing 5 cents: i = 1; 1 * 25 > 5 - loop terminates
            int amountRemaining = valToChange - i * valueOfCoinWeAreUsing; // for 25 cents case for changing 5 cents: amtRem = 5 - 0 = 5

            usedCoins.push(i);
            ways += makeChange(amountRemaining, availableCoinValues, index +1, depth + 10, usedCoins);
        }

        return ways;
    }

}

