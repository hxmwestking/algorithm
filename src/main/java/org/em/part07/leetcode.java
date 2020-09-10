package org.em.part07;

import java.util.Arrays;

public class leetcode {
    public static void main(String[] args) {
        leetcode leetcode = new leetcode();
        /*int[] arr = {1, 2, 3, 8, 5, 7, 6, 4};
        leetcode.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = {4,5,6,7,0,1,2};
        System.out.println(leetcode.search(arr, 0));
    }

    /*
    实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

    如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

    必须原地修改，只允许使用额外常数空间。

    以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1


    算法过程
    标准的“下一个排列”算法可以描述为：

    1.从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
    2.在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
    3.将 A[i] 与 A[k] 交换
    4.可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
    5.如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
    该方法支持数据重复，且在 C++ STL 中被采用。

     */
    public void nextPermutation(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return;
        }
        int i = length - 2;
        int j = length - 1;
        int k = length - 1;
        // find: A[i]<A[j]
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }
        if (i >= 0) {// 不是最后一个排列
            // find: A[i]<A[k]
            while (nums[i] >= nums[k]) {
                k--;
            }
            // swap A[i], A[k]
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
        // reverse A[j:end]
        swap(nums, j, length - 1);
    }

    public void swap(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }

    }

    /*
    给定一个只包含 '('和 ')'的字符串，找出最长的包含有效括号的子串的长度。

    示例1:
    输入: "(()"
    输出: 2
    解释: 最长有效括号子串为 "()"

    示例 2:
    输入: ")()())"
    输出: 4
    解释: 最长有效括号子串为 "()()"

    1.s[i]=‘)’ 且 s[i−1]=‘(’，也就是字符串形如 “……()”，我们可以推出：
    dp[i]=dp[i−2]+2
    我们可以进行这样的转移，是因为结束部分的 "()" 是一个有效子字符串，并且将之前有效子字符串的长度增加了2。

    2.s[i]=‘)’ 且 s[i−1]=‘)’，也就是字符串形如 “……))”，我们可以推出：
    如果 s[i−dp[i−1]−1]=‘(’，那么
        dp[i]=dp[i−1]+dp[i−dp[i−1]−2]+2

    我们考虑如果倒数第二个‘)’是一个有效子字符串的一部分（记作 subs），对于最后一个‘)’，如果它是一个更长子字符串的一部分，
    那么它一定有一个对应的‘(’，且它的位置在倒数第二个‘)’所在的有效子字符串的前面（也就是subs的前面）。因此，如果子字符串subs
    的前面恰好是‘(’，那么我们就用2加上subs的长度（dp[i−1]）去更新dp[i]。同时，我们也会把有效子串 “(subs)”之前的有效子串的长度也加上，
    也就是再加上dp[i−dp[i−1]−2]。

    最后的答案即为dp 数组中的最大值。


     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /*
    假设按照升序排序的数组在预先未知的某个点上进行了旋转。

    ( 例如，数组[0,1,2,4,5,6,7]可能变为[4,5,6,7,0,1,2])。

    搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1。

    你可以假设数组中不存在重复的元素。

    你的算法时间复杂度必须是O(logn) 级别。

    示例 1:
    输入: nums = [4,5,6,7,0,1,2], target = 0
    输出: 4

    示例2:
    输入: nums = [4,5,6,7,0,1,2], target = 3
    输出: -1
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0;
        int r = length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            // 有序
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 无序
                if (nums[mid] < target && target <= nums[length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
