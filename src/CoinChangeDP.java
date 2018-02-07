public class CoinChangeDP {
	public static void main(String[] args) {
		int[] coins = {1, 2, 3};
		int amt = 4;
		
		int numWays = coinChange(coins, amt);
		System.out.println(numWays);
	}

	public static int coinChange(int[] coins, int amt) {
		// create two dimentional array 
		// # of rows = number of coins + 1
		// # of cols = 1 to amt + 1 extra col => amt + 1
		int[][] ways = new int[coins.length + 1][amt + 1];

		// col 1 of every row == 1
		for (int i = 0; i < ways.length; i++) {
			ways[i][0] = 1;
		}

		// fill the 2 dimentional array
		for (int r = 1; r < ways.length; r++) {
			for (int c = 1; c < ways[0].length; c++) {
				if (coins[r-1] > c) {
					// get value from top
					ways[r][c] = ways[r-1][c];
				} else {
					// add value from top + value from col - coins[r-1]. 
					// we are indexing coins using r-1 and not r since we have taken 1 more row than the # of coins
					ways[r][c] = ways[r-1][c] + ways[r][c - coins[r-1]];
				}
			}
		}
		// System.out.println(ways.length);
		// System.out.println(ways[0].length);
		return ways[ways.length - 1][ways[0].length - 1];
	}
}
