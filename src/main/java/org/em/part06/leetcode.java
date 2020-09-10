package org.em.part06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class leetcode {

    public static void main(String[] args) {

    }

    /*
    给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

    不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

    示例1:
    给定数组 nums = [1,1,2],
    
    函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
    
    你不需要考虑数组中超出新长度后面的元素。

    示例2:
    给定 nums = [0,0,1,1,1,2,2,3,3,4],
    
    函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
    
    你不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        int n = nums.length;
        for (int j = 1; j < n; j++) {
            if (nums[i]!=nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
    
    /*
    给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。

    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
    
    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
    
    示例 1:
    给定 nums = [3,2,2,3], val = 3,
    
    函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
    
    你不需要考虑数组中超出新长度后面的元素。

    示例2:
    给定 nums = [0,1,2,2,3,0,4,2], val = 2,
    
    函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
    
    注意这五个元素可为任意顺序。
    
    你不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    
    /*
    实现strStr()函数。

    给定一个haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。

    示例 1:
    输入: haystack = "hello", needle = "ll"
    输出: 2

    示例 2:
    输入: haystack = "aaaaa", needle = "bba"
    输出: -1
    说明:

    当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    对于本题而言，当needle是空字符串时我们应当返回 0 。这与C语言的strstr()以及 Java的indexOf()定义相符。
     */
    public int strStr(String haystack, String needle) {
        int length = needle.length();
        if (length == 0) {
            return 0;
        }
        int n = haystack.length();
        for (int i = 0; i <= n - length; i++) {
            String str = haystack.substring(i, i + length);
            if (needle.equals(str)) {
                return i;
            }
        }
        return -1;
    }
    
    /*
    给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

    返回被除数dividend除以除数divisor得到的商。
    
    整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2

    示例1:
    输入: dividend = 10, divisor = 3
    输出: 3
    解释: 10/3 = truncate(3.33333..) = truncate(3) = 3

    示例2:
    输入: dividend = 7, divisor = -3
    输出: -2
    解释: 7/-3 = truncate(-2.33333..) = -2
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        boolean a = dividend < 0;
        boolean b = divisor < 0;
        if (!a)
            dividend = -dividend;
        if (!b)
            divisor = -divisor;
        int i = 0;
        while (dividend <= divisor) {
            int temp = divisor;
            int c = 1;
            while (dividend - temp <= temp) {
                temp = temp << 1;
                c = c << 1;
            }
            dividend -= temp;
            i += c;
        }
        if (a != b) {
            return -i;
        } else {
            return i;
        }
    }
    
    /*
    给定一个字符串s和一些长度相同的单词words。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。

    注意子串要与words 中的单词完全匹配，中间不能有其他字符，但不需要考虑words中单词串联的顺序。

    示例 1：
    输入：
      s = "barfoothefoobarman",
      words = ["foo","bar"]
    输出：[0,9]
    解释：
    从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
    输出的顺序不重要, [9,0] 也是有效答案。

    示例 2：
    输入：
      s = "wordgoodgoodgoodbestword",
      words = ["word","good","best","word"]
    输出：[]
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);

                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }
            }
        }
        return res;
    }
}
