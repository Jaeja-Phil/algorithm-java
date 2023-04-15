package educative.grokking_coding_interview_patterns_in_java.sliding_window;

/**
 * Given an array where the element at the index i represents the price of a stock on day i,
 * find the maximum profit that you can gain by buying the stock once and then selling it.
 *
 * constraints:
 * - stock can only be purchased on a single day and sold on a different day
 * - if no profit can be made, return 0
 * - 1 <= prices.length <= 10^5
 * - 0 <= prices[i] <= 10^5
 *
 * ex:
 * prices = [7, 1, 5, 3, 6, 4]
 * output = 5
 * explanation: buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5
 */
public class $07_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {7, 1, 5, 3, 6, 4})); // 5
    }

    public static int solution(int[] prices) {
        // initialize minPrice to the first element of the prices array
        int minPrice = prices[0];
        // initialize maxProfit to 0
        int maxProfit = 0;
        // iterate through the prices array
        for (int currentPrice : prices) {
            // update minPrice to the minimum value between the current minPrice and the current price
            minPrice = Math.min(minPrice, currentPrice);
            // update maxProfit to the maximum value between the current maxProfit and the current price - minPrice
            maxProfit = Math.max(maxProfit, currentPrice - minPrice);
        }
        // return maxProfit
        return maxProfit;
    }
}
