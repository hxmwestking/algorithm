package org.em.part03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode {

    public static void main(String[] args) {
        /*int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));*/

//        System.out.println(romanToInt("III"));

        /*String[] arr = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(arr));*/

        int[] arr = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(arr));
    }

    /*
    给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0)。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。

    说明：你不能倾斜容器，且n的值至少为 2。

    输入：[1,8,6,2,5,4,8,3,7]
    输出：49
     */
    public static int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }

    /*
    罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
    字符          数值
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。

    通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：

    I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
    X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
    C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
    给定一个整数，将其转为罗马数字。输入确保在 1到 3999 的范围内。

    示例1:
    输入:3
    输出: "III"

    示例2:
    输入:4
    输出: "IV"

    示例3:
    输入:9
    输出: "IX"

    示例4:
    输入:58
    输出: "LVIII"
    解释: L = 50, V = 5, III = 3.

    示例5:
    输入:1994
    输出: "MCMXCIV"
    解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    /*
    罗马数字包含以下七种字符:I，V，X，L，C，D和M。

    字符          数值
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。

    通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：

    I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
    X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
    C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
    给定一个罗马数字，将其转换成整数。输入确保在 1到 3999 的范围内。

    示例1:
    输入:"III"
    输出: 3

    示例2:
    输入:"IV"
    输出: 4

    示例3:
    输入:"IX"
    输出: 9

    示例4:
    输入:"LVIII"
    输出: 58
    解释: L = 50, V= 5, III = 3.

    示例5:
    输入:"MCMXCIV"
    输出: 1994
    解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    public static int romanToInt(String s) {
        int length = s.length();
        int sum = 0;
        int pre = getValue(s.charAt(0));
        for (int i = 1; i < length; i++) {
            int num = getValue(s.charAt(i));
            if (num > pre) {
                sum += -pre;
            } else {
                sum += pre;
            }
            pre = num;
        }
        sum += pre;
        return sum;
    }

    private static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /*
    编写一个函数来查找字符串数组中的最长公共前缀。

    如果不存在公共前缀，返回空字符串""。
    
    示例1:
    输入: ["flower","flow","flight"]
    输出: "fl"

    示例2:
    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。

    说明:
    所有输入只包含小写字母a-z。
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    /*
    给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

    注意：答案中不可以包含重复的三元组。

    示例：

    给定数组 nums = [-1, 0, 1, 2, -1, -4]，

    满足要求的三元组集合为：
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a < n; a++) {
            // 需要和上一次枚举的数不相同
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int c = n - 1;
            int target = -nums[a];
            // 枚举 b
            for (int b = a + 1; b < n; ++b) {
                // 需要和上一次枚举的数不相同
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (b < c && nums[b] + nums[c] > target) {
                    --c;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (b == c) {
                    break;
                }
                if (nums[b] + nums[c] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[a]);
                    list.add(nums[b]);
                    list.add(nums[c]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
