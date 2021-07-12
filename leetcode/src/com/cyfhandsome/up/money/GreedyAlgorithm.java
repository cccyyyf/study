package com.cyfhandsome.up.money;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * @author cyf
 * @date 2021/7/12 13:44
 */
public class GreedyAlgorithm {

    private static final Logger log = Logger.getLogger("GreedyAlgorithm");

    /**
     * 455 分发饼干
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * <p>
     * 对每个孩子 i，都有一个胃口值：g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
     * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/assign-cookies
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 贪心算法
     */
    static class DistributeBiscuits {
        //贪心算法，先满足饭量小的，再满足饭量大的
        public static int findContentChildren(int[] g, int[] s) {
            int eatChildNum = 0;
            int cookieNum = 0;
            Arrays.sort(g);
            Arrays.sort(s);
            while (eatChildNum < g.length && cookieNum < s.length) {
                if (g[eatChildNum] <= s[cookieNum]) {
                    eatChildNum++;
                }
                cookieNum++;
            }
            return eatChildNum;
        }

        public static void main(String[] args) {
            int[] g = {1, 2};
            int[] s = {1, 2, 3};
            log.info(String.valueOf(findContentChildren(g, s)));
        }
    }

    /**
     * 135 分糖果
     * 老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * <p>
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * <p>
     * 每个孩子至少分配到 1 个糖果。
     * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/candy
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class Candy {
        //先假设每个人分数都一样，所以每个人都分1个糖果，然后从左向右遍历判断，再从右到左遍历判断
        public static int candy(int[] ratings) {
            //如果只有一个人，直接返回
            if (ratings.length < 2) {
                return ratings.length;
            }

            int[] candyNum = new int[ratings.length];

            //给每个人分1个糖果
            for (int i = 0; i < ratings.length; i++) {
                candyNum[i] = 1;
            }

            //从左向右遍历
            for (int i = 1; i < ratings.length; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    candyNum[i] = candyNum[i - 1] + 1;
                }
            }
            //从右向左遍历
            for (int i = ratings.length - 1; i > 0; i--) {
                if (ratings[i] < ratings[i - 1]) {
                    candyNum[i - 1] = Math.max(candyNum[i] + 1, candyNum[i - 1]);
                }
            }
            //计算总数
            int result = 0;
            for (int i : candyNum) {
                result += i;
            }
            return result;
        }

        public static void main(String[] args) {
            int[] ratings = {1, 0, 2};
            log.info(String.valueOf(candy(ratings)));
        }
    }

    /**
     * 122 买卖股票的最佳时机Ⅱ
     * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class BuyAndSellStocks {
        //假设都是前一天买，第二天卖，有收益时就卖，没收益的时候不买不卖
        public static int maxProfit(int[] prices) {
            int maxProfit = 0;

            for (int i = 1; i < prices.length; i++) {
                if (prices[i] - prices[i - 1] > 0) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        public static void main(String[] args) {
            int[] stockPrice = {7, 1, 5, 3, 6, 4};
            log.info(String.valueOf(maxProfit(stockPrice)));
        }
    }

    /**
     * 435 无重叠区间
     * <p>
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * 注意:
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     */
    static class NonOverlappingInterval {
        /*按照起点排序：选择结尾最短的，后面才可能连接更多的区间
        （如果两个区间有重叠，应该保留结尾小的） 把问题转化为最多能保留多少个区间，使他们互不重复，
        则按照终点排序，每个区间的结尾很重要，结尾越小，则后面越有可能容纳更多的区间*/
        public static int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length < 2) {
                return 0;
            }
            //尾节点排序
            Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[1]));
            int prev = intervals[0][1];
            int removeNum = 0;
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < prev) {
                    removeNum++;
                } else {
                    prev = intervals[i][1];
                }
            }

            return removeNum;

        }

        public static void main(String[] args) {
            int[][] array = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
            log.info(String.valueOf(eraseOverlapIntervals(array)));
        }
    }
}
