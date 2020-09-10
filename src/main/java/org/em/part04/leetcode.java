package org.em.part04;

import java.util.*;

public class leetcode {

    public static void main(String[] args) {
        /*int[] arr = {1, 1, 1, 0};
        System.out.println(threeSumClosest(arr, 100));*/

//        System.out.println(letterCombinations("23"));

        /*int[] arr = {-1,0,-5,-2,-2,-4,0,1,-2};
        System.out.println(fourSum(arr, -9));*/
    }

    /*
    给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。

    示例：
    输入：nums = [-1,2,1,-4], target = 1
    输出：2
    解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

    提示：

    3 <= nums.length <= 10^3
    -10^3<= nums[i]<= 10^3
    -10^4<= target<= 10^4
     */
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int best = 10000000;
        for (int a = 0; a < n; a++) {
            // 保证和上一次枚举的元素不相等
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            int b = a + 1;
            int c = n - 1;
            while (b < c) {
                int sum = nums[a] + nums[b] + nums[c];
                if (sum == target) {
                    return target;
                }
                if (Math.abs(best - target) > Math.abs(sum - target)) {
                    best = sum;
                }
                if (sum > target) {
                    int c0 = c;
                    while (b < c0 && nums[c0] == nums[c]) {
                        c0--;
                    }
                    c = c0;
                } else {
                    int b0 = b;
                    while (b0 < c && nums[b0] == nums[b]) {
                        b0++;
                    }
                    b = b0;
                }
            }
        }
        return best;
    }

    /*
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    输入："23"
    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */
    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        int length = digits.length();
        if (length == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuilder());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /*
    给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。

    注意：
    答案中不可以包含重复的四元组。
    
    示例：
    给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
    满足要求的四元组集合为：
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a < n - 3; a++) {
            // 需要和上一次枚举的数不相同
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // d 对应的指针初始指向数组的最右端
            for (int b = a + 1; b < n - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int c = b + 1;
                int d = n - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target) {
                        c++;
                    } else if (sum > target) {
                        d--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[a]);
                        list.add(nums[b]);
                        list.add(nums[c]);
                        list.add(nums[d]);
                        ans.add(list);
                        c++;
                        d--;
                        while (c < d && nums[c] == nums[c - 1]) c++;
                        while (c < d && nums[d + 1] == nums[d]) d--;
                    }
                }
            }
        }
        return ans;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /*
    给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。

    示例：
    给定一个链表: 1->2->3->4->5, 和 n = 2.
    
    当删除了倒数第二个节点后，链表变为 1->2->3->5.
    说明：
    给定的 n保证是有效的。
    
    进阶：
    你能尝试使用一趟扫描实现吗？
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /*
    给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。

    有效字符串需满足：
    
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    注意空字符串可被认为是有效字符串。
    
    示例 1:
    输入: "()"
    输出: true
    
    示例2:
    输入: "()[]{}"
    输出: true

    示例3:
    输入: "(]"
    输出: false

    示例4:
    输入: "([)]"
    输出: false

    示例5:
    输入: "{[]}"
    输出: true
     */
    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(c))) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
