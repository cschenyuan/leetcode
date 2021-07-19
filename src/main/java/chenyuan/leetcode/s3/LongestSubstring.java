package chenyuan.leetcode.s3;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * Given a string, find the length of the longest substring without
 * repeating characters.
 *
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
 * "pwke" is a subsequence and not a substring.
 */
public class LongestSubstring {

    /**
     * Sliding window optimized
     * Time complexity : O(n)
     * Space complexity : O(m), m is the size of the charset
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0)
            return 0;

        int mlen = 0;
        int[] index = new int[128]; // 存储字符的最大下标
        for (int i=0, j=0; j < len; j++) { // i表示最大无重复子字符串的开始位置
            char c = s.charAt(j);
            i = Math.max(index[c], i);  // 如果字符已经出现重复, 则取i为该字符所在下标j加1
            mlen = Math.max(mlen, j - i + 1); // 如果s[i,j]的长度大于当前最大长度mlen, 则更新mlen的值
            index[c] = j + 1; // 记录字符的下标
        }

        return mlen;
    }

    public static void main(String[] args) {
//        String s = "dvdf";
//        String s = "ppppp";
//        String s = "bpfbhmipx";
//        String s = "abcabcbb";
//        String s = "pwwkew";
        String s = "abba";
//        String s = "abcb";

        System.out.println(lengthOfLongestSubstring(s));
    }
}
