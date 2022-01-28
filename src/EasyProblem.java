import java.util.Stack;

public class EasyProblem {
    static class NUM13_Solution {
        public int romanToInt(String s) {
            int length = s.length();
            int result = 0;
            for (int i = 0; i < length - 1; i++) {
                if (calculateOne(s.charAt(i)) > calculateOne(s.charAt(i + 1))) {
                    result += calculateOne(s.charAt(i));
                } else
                    result -= calculateOne(s.charAt(i));
            }
            return result + calculateOne(s.charAt(length - 1));
        }

        public int calculateOne(Character a) {
            return switch (a) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };
        }
    }

    static class NUM14_Solution {
        public String longestCommonPrefix(String[] strs) {
            int size = strs.length;
            int MaxIndex = 0;
            int shortestLength = strs[0].length();
            for (int i = 1; i < size; i++) {
                if (shortestLength > strs[i].length())
                    shortestLength = strs[i].length();
            }
            for (; MaxIndex < shortestLength; MaxIndex++) {
                if (!StringSamePre(strs, MaxIndex, size))
                    break;
            }
            if (MaxIndex == 0) {
                return "";
            } else return strs[0].substring(0, MaxIndex);
        }

        public boolean StringSamePre(String[] strs, int index, int size) {
            for (int i = 1; i < size; i++) {
                if (strs[i].charAt(index) != strs[0].charAt(index))
                    return false;
            }
            return true;
        }
    }

    static public class NUM20_Solution {
        public boolean isValid(String s) {
            Stack<Character> myStack = new Stack<>();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                    myStack.push(s.charAt(i));
                } else {
                    if (myStack.isEmpty()) {
                        return false;
                    }
                    Character temp = myStack.pop();
                    if (!isMatch(temp, s.charAt(i))) {
                        return false;
                    }
                }
            }
            if (myStack.isEmpty())
                return true;
            else
                return false;
        }

        public boolean isMatch(Character A, Character B) {
            if ((A == '(' && B == ')') || (A == '[' && B == ']') || (A == '{' && B == '}')) {
                return
                        true;
            } else
                return false;
        }
    }

    static public class NUM21_Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null)
                return list2;
            if (list2 == null)
                return list1;
            ListNode result;
            ListNode pre;
            if(list1.val<list2.val){
                result = list1;
                list1 = list1.next;
            }
            else{
                result =list2;
                list2 = list2.next;
            }
            pre = result;
            while(list1!=null&&list2!=null){
                if(list1.val<list2.val){
                    pre.next =list1;
                    list1 = list1.next;
                    pre = pre.next;
                }
                else{
                    pre.next =list2;
                    list2 = list2.next;
                    pre = pre.next;
                }
            }
            if(list1!=null){
                pre.next = list1;
            }
            if(list2!=null){
                pre.next = list2;
            }
            return result;
        }
        public void printListNode(ListNode head){
            while(head!=null){
                System.out.println(head.val);
                head = head.next;
            }
        }
        public ListNode creatListNode(int[] nums){
            int length = nums.length;
            if(length == 0)
                return null;
            ListNode listNode = new ListNode(nums[0]);
            ListNode pre = listNode;
            for(int i=1;i<length;i++){
                ListNode temp = new ListNode(nums[i]);
                pre.next = temp;
                pre = pre.next;
            }
            return listNode;
        }

    }

    static public class NUM26_Solution {
        public int removeDuplicates(int[] nums) {
            int length = nums.length;
            if (length == 0 || length == 1)
                return length;
            int temp = nums[0];
            int tempIndex = 0;
            boolean flag = false;
            for (int i = 1; i < length; i++) {
                while (nums[i] == temp) {
                    i++;
                    if (i == length) {
                        flag = true;
                        break;
                    }
                }
                    if (!flag) {
                        nums[++tempIndex] = nums[i];
                        temp = nums[i];
                    }
                }
                return tempIndex+1;
            }

    }
    public static void main(String args[]) {
        NUM26_Solution solution = new NUM26_Solution();
        int[] nums = {0,0};
        int len = solution.removeDuplicates(nums);
//        System.out.println(len);
        for(int i=0;i<len;i++){
            System.out.println(nums[i]);
        }

    }
}
