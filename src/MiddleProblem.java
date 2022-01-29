import java.util.LinkedList;
import java.util.List;

public class MiddleProblem {
    static public class NUM3_Solution {
        public int lengthOfLongestSubstring(String s) {
            int length = s.length();
            if (length == 0 || length == 1)
                return length;
            int start = 0;
            int maxLength = 1;
            int[] flag = new int[128];
            for (int i = 0; i < length; i++) {
                flag[s.charAt(i)]++;
                while (flag[s.charAt(i)] != 1) {
                    flag[s.charAt(start)]--;
                    start++;
                }
                maxLength = Math.max(maxLength, i - start + 1);
            }
            return maxLength;
        }
    }

    static public class NUM5_Solution {
        //用了笨办法，遍历字符串，分别以每一个字符串为中心，向两边发散，检验是否为回文字符串，注意考虑奇数和偶数的情况
        public String longestPalindrome(String s) {
            int length = s.length();
            if (length == 0 || length == 1)
                return s;
            int maxLength = 1;
            int index = 0;
            for (int i = 0; i < length; i++) {
                //先考虑回文字符串长度为奇数
                int left = i;
                int right = i;
                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                }
                if (maxLength < right - left - 1) {
                    maxLength = right - left - 1;
                    index = i;
                }
                //在考虑回文字符串为偶数
                left = i;
                right = i + 1;
                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                }
                if (maxLength < right - left - 1) {
                    maxLength = right - left - 1;
                    index = i;
                }
            }
            if (maxLength % 2 != 0) {
                return s.substring(index - (maxLength - 1) / 2, index + (maxLength - 1) / 2 + 1);
            } else return
                    s.substring(index - maxLength / 2 + 1, index + maxLength / 2 + 1);
        }
    }

    static public class NUM6_Solution {
        public String convert(String s, int numRows) {
            if (s.length() <= numRows || numRows == 1)
                return s;
            LinkedList[] temp = new LinkedList[numRows];
            StringBuilder result = new StringBuilder();
            boolean isDirectionDown = true;
            int flag = 0;
            for (int i = 0; i < s.length(); i++) {
                if (temp[flag] == null)
                    temp[flag] = new LinkedList();
                temp[flag].add(s.charAt(i));
                if (isDirectionDown) {
                    flag++;
                } else {
                    flag--;
                }
                if (flag == 0 || flag == numRows - 1) {
                    isDirectionDown = !isDirectionDown;
                }
            }
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < temp[i].size(); j++) {
                    result.append(temp[i].get(j));
                }
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        NUM6_Solution solution = new NUM6_Solution();
        String s = "P";
        System.out.println(solution.convert(s, 1));

    }
}
