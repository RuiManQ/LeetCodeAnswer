import java.util.ArrayList;
import java.util.Arrays;
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

    static public class NUM7_Solution {
        public int reverse(int x) {
            if (x == 0)
                return x;
            boolean isPositive = true;
            if (x < 0) {
                isPositive = false;
                x = -x;
            }
            if (isPositive)
                return reversePositive(x);
            else
                return -reversePositive(x);
        }

        public int reversePositive(int x) {
            int result = 0;
            while (x % 10 == 0) {
                x = x / 10;
            }
            while (x != 0) {
                if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                result = result * 10 + x % 10;
                x = x / 10;
            }
            return result;
        }
    }

    static public class NUM11_Solution {
        //没有注意时间复杂度，超出时间限制，2 <= n <= 105，所以时间复杂度不能为O(n^2)
        public int wrongmaxArea(int[] height) {
            int max = 0;
            for (int i = 0; i < height.length; i++) {
                int area = 0;
                for (int j = i + 1; j < height.length; j++) {
                    area = Math.min(height[i], height[j]) * (j - i);
                    if (max < area) {
                        max = area;
                    }

                }
            }
            return max;
        }

        //双指针法，移动较小的那个指针，直到两个指针重合
        public int maxArea(int[] height) {
            int max = 0;
            int length = height.length;
            int left = 0;
            int right = length - 1;
            while (left != right) {
                int area = Math.min(height[left], height[right]) * (right - left);
                if (max < area) {
                    max = area;
                }
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return max;
        }
    }

    static public class NUM12_Solution {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        public String intToRoman(int num) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                int value = values[i];
                String symbol = symbols[i];
                while (num >= value) {
                    num -= value;
                    result.append(symbol);
                }
                if (num == 0)
                    break;
            }
            return result.toString();
        }
    }

    static public class NUM15_Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums.length < 3) {
                return result;
            }
            sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                while (i > 0 && nums[i] == nums[i - 1] && i < nums.length - 2) {
                    i++;
                }
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[i] + nums[left] + nums[right] < 0) {
                        left++;
                    } else if (nums[i] + nums[left] + nums[right] > 0) {
                        right--;
                    } else {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        result.add(temp);
                        left++;
                        right--;
                        int templeft = left - 1;
                        while (nums[left] == nums[templeft] && left < right) {
                            left++;
                        }
                    }
                }
            }
            return result;
        }

        public static void sort(int[] nums) {
            int temp;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] < nums[j - 1]) {
                        temp = nums[j];
                        nums[j] = nums[j - 1];
                        nums[j - 1] = temp;
                    }
                }
            }
        }
    }

    static public class NUM16_Solution {
        public int threeSumClosest(int[] nums, int target) {
            NUM15_Solution.sort(nums);
            int result = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int temp = nums[i] + nums[left] + nums[right];
                    if (temp <= target) {
                        left++;
                        if (Math.abs(temp - target) < Math.abs(result - target)) {
                            result = temp;
                        }
                    } else {
                        right--;
                        if (Math.abs(temp - target) < Math.abs(result - target)) {
                            result = temp;
                        }
                    }
                }
            }
            return result;
        }
    }

    static public class NUM17_Solution {
        //最笨的办法：枚举传入字符串的长度，分别做处理
        public List<String> letterCombinations(String digits) {
            int length = digits.length();
            String[] chars = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            List<String> result = new ArrayList<>();
            int[] temp = new int[length];
            for (int i = 0; i < length; i++) {
                temp[i] = digits.charAt(i) - '2';
            }
            switch (length) {
                case 1:
                    for (int i = 0; i < chars[temp[0]].length(); i++) {
                        result.add(chars[temp[0]].substring(i, i + 1));
                    }
                    return result;
                case 2:
                    for (int i = 0; i < chars[temp[0]].length(); i++) {
                        for (int j = 0; j < chars[temp[1]].length(); j++) {
                            String stringBuilder = String.valueOf(chars[temp[0]].charAt(i)) +
                                    chars[temp[1]].charAt(j);
                            result.add(stringBuilder);
                        }
                    }
                    return result;
                case 3:
                    for (int i = 0; i < chars[temp[0]].length(); i++) {
                        for (int j = 0; j < chars[temp[1]].length(); j++) {
                            for (int k = 0; k < chars[temp[2]].length(); k++) {
                                String stringBuilder = String.valueOf(chars[temp[0]].charAt(i)) +
                                        chars[temp[1]].charAt(j) + chars[temp[2]].charAt(k);
                                result.add(stringBuilder);
                            }
                        }
                    }
                    return result;
                case 4:
                    for (int i = 0; i < chars[temp[0]].length(); i++) {
                        for (int j = 0; j < chars[temp[1]].length(); j++) {
                            for (int k = 0; k < chars[temp[2]].length(); k++) {
                                for (int p = 0; p < chars[temp[3]].length(); p++) {
                                    String stringBuilder = String.valueOf(chars[temp[0]].charAt(i)) +
                                            chars[temp[1]].charAt(j) + chars[temp[2]].charAt(k) + chars[temp[3]].charAt(p);
                                    result.add(stringBuilder);
                                }
                            }
                        }
                    }
                    return result;
                default:
                    return result;
            }
        }

    }

    static public class NumX_Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            int length = nums.length;
            if (length == 0) {
                return result;
            }
            boolean[] used = new boolean[length];
            List<Integer> path = new ArrayList<>();
            dfs(nums, length, 0, path, used, result);
            return result;
        }

        private void dfs(int[] nums, int length, int depth, List<Integer> path, boolean[] used, List<List<Integer>> res) {
            //在叶子节点处，将路径存入结果
            if (depth == length) {
                res.add(new ArrayList<>(path) {
                });
                return;
            }
            //在非叶子节点：
            for (int i = 0; i < length; i++) {
                if (used[i]) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                System.out.println("  递归之前 => " + path);
                dfs(nums, length, depth + 1, path, used, res);
                System.out.println("递归之后 => " + path);
                used[i] = false;


                path.remove(path.size() - 1);

            }
        }
    }

    static public class Num79_Solution {
        public boolean exist(char[][] board, String word) {
            int h = board.length;
            int w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    boolean flag = mycheck(board, visited, i, j, word, 0);
                    if (flag) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean mycheck(char[][] board, boolean[][] visited, int i, int j, String s, int k) {

            if (board[i][j] != s.charAt(k)) {
                return false;
            } else if (k == (s.length() - 1)) {
                return true;
            }
            visited[i][j] = true;
            int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int[] dir : direction) {
                int newi = i + dir[0];
                int newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        if (mycheck(board, visited, newi, newj, s, k + 1)) {
                            return true;
                        }
                    }
                }
            }
            visited[i][j] = false;
            return false;
        }

        public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
            if (board[i][j] != s.charAt(k)) {
                return false;
            } else if (k == s.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean result = false;
            for (int[] dir : directions) {
                int newi = i + dir[0], newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        boolean flag = check(board, visited, newi, newj, s, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            visited[i][j] = false;
            return result;
        }

    }


    static public class Num130_Solution {
        public void solve(char[][] board) {
            int h = board.length, w = board[0].length;
            if (h <= 1 || w <= 1) {
                return;
            }
            int[][] checkResult = new int[h][w];
            for (int i = 0; i < h; i++) {
                if (board[i][0] == 'O' && checkResult[i][0] == 0) {
                    dfs(board, i, 0, checkResult);
                }
                if (board[i][w - 1] == 'O' && checkResult[i][w - 1] == 0) {
                    dfs(board, i, w - 1, checkResult);
                }
            }
            for (int i = 0; i < w; i++) {
                if (board[0][i] == 'O' && checkResult[0][i] == 0) {
                    dfs(board, 0, i, checkResult);
                }
                if (board[h - 1][i] == 'O' && checkResult[h - 1][i] == 0) {
                    dfs(board, h - 1, i, checkResult);
                }
            }

            for (int i = 0; i < h; i++) {
                System.out.println(Arrays.toString(checkResult[i]));

            }
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == 'O' && checkResult[i][j] == 0) {
                        board[i][j] = 'X';
                    }
                }
            }

        }

        public void dfs(char[][] board, int i, int j, int[][] checkResult) {
            if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || checkResult[i][j] == 1) {
                return;
            }
            if (board[i][j] == 'O') {
                checkResult[i][j] = 1;
            } else {
                return;
            }
            dfs(board, i + 1, j, checkResult);
            dfs(board, i - 1, j, checkResult);
            dfs(board, i, j + 1, checkResult);
            dfs(board, i, j - 1, checkResult);
        }
    }

    class Num130_Solution_TimeLimitted {
        public void solve(char[][] board) {
            int h = board.length, w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            boolean[][] checkResult = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == 'O') {
                        if (!checkGoOut(board, i, j, visited, checkResult)) {
                            board[i][j] = 'X';
                        }
                    }
                }
            }
        }

        public boolean checkGoOut(char[][] board, int i, int j, boolean[][] visited, boolean[][] checkResult) {

            //返回值为true表示能到达外面
            if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                return true;
            }
            visited[i][j] = true;
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean result = false;
            for (int[] dir : directions) {
                int newi = i + dir[0], newj = j + dir[1];
                if (board[newi][newj] == 'O' && !visited[newi][newj]) {
                    if (!checkResult[newi][newj]) {
                        if (checkGoOut(board, newi, newj, visited, checkResult)) {
                            checkResult[newi][newj] = true;
                            result = true;
                            break;
                        }
                    } else {
                        result = true;
                        break;
                    }
                }
            }
            visited[i][j] = false;
            return result;
        }
    }


    static public class Num200_Solution {
        public int numIslands(char[][] grid) {
            int h = grid.length, w = grid[0].length;
            boolean[][] visited = new boolean[h][w];
            int result = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        dfs(grid, i, j, visited);
                        result++;
                    }
                }
            }
            return result;
        }

        public void dfs(char[][] grid, int i, int j, boolean[][] visited) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] == '0') {
                return;
            }
            visited[i][j] = true;
            dfs(grid, i + 1, j, visited);
            dfs(grid, i - 1, j, visited);
            dfs(grid, i, j + 1, visited);
            dfs(grid, i, j - 1, visited);
        }
    }

    public static void main(String[] args) {
        Num200_Solution solution = new Num200_Solution();

//        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(solution.numIslands(grid));

//        String test = "123456";
//        for(int i=0;i<test.length();i++) {
//            int flag = test.charAt(i) - '1';
//            System.out.println(flag);
//        }
    }
}