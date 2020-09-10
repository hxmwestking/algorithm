package org.em.part02;

public class leetcode {

    public static void main(String[] args) {

//        System.out.println(convert("LEETCODEISHIRING", 4));

//        System.out.println(reverse(120));

//        System.out.println(myAtoi("-2147483647"));

//        System.out.println(isPalindrome(123321));

        System.out.println(isMatch("aab", "c*a*b"));
    }

    /*
    将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

    比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

    L   C   I   R
    E T O E S I I G
    E   D   H   N

    之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

    示例 2:
    输入: s = "LEETCODEISHIRING", numRows =4
    输出:"LDREOEIIECIHNTSG"
    解释:

    L     D     R
    E   O E   I I
    E C   I H   N
    T     S     G

     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    /*
    给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

    示例 1:
    输入: 123
    输出: 321

    示例 2:
    输入: -123
    输出: -321

    示例 3:
    输入: 120
    输出: 21
     */
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /*
    请你来实现一个atoi函数，使其能将字符串转换成整数。

    首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

    如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
    假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
    该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
    注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

    在任何情况下，若函数不能进行有效的转换时，请返回 0 。

    提示：

    本题中的空白字符只包括空格字符 ' ' 。
    假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−231, 231− 1]。如果数值超过这个范围，请返回 INT_MAX (2^31− 1) 或INT_MIN (−2^31) 。

    输入: "   -42"
    输出: -42
    解释: 第一个非空白字符为 '-', 它是一个负号。
        我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。

    输入: "-91283472332"
    输出: -2147483648
    解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
        因此返回 INT_MIN (−231) 。
     */
    public static int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }
        int state = 0;
        long sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (state == 0) {
                if (c == '+') {
                    state = 1;
                } else if (c == '-') {
                    state = -1;
                } else if (c == ' ') {
                } else if (Character.isDigit(c)) {
                    sum = c - '0';
                    state = 2;
                } else {
                    return 0;
                }
            } else if (Character.isDigit(c)) {
                int temp = c - '0';
                sum = sum * 10 + temp;
                if (state > 0 && sum >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (state < 0 && -sum <= Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }
        if (state == 2) {
            return (int) sum;
        }
        return (int) (state * sum);
    }

    /*
    判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    输入: 121
    输出: true

    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     */
    public static boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    /*
    给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。

    说明:
    
    s可能为空，且只包含从a-z的小写字母。
    p可能为空，且只包含从a-z的小写字母，以及字符.和*。
    示例 1:
    
    输入:
    s = "aa"
    p = "a"
    输出: false
    解释: "a" 无法匹配 "aa" 整个字符串。
    示例 2:
    
    输入:
    s = "aa"
    p = "a*"
    输出: true
    解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
    示例3:
    
    输入:
    s = "ab"
    p = ".*"
    输出: true
    解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
    示例 4:
    
    输入:
    s = "aab"
    p = "c*a*b"
    输出: true
    解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
    示例 5:
    
    输入:
    s = "mississippi"
    p = "mis*is*p*."
    输出: false
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
