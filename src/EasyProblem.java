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

    static class NUM14_Solution{
        public String longestCommonPrefix(String[] strs){
            int size = strs.length;
            int MaxIndex = 0;
            int shortestLength = strs[0].length();
            for(int i=1;i<size;i++){
                if(shortestLength > strs[i].length())
                    shortestLength = strs[i].length();
            }
            for(;MaxIndex<shortestLength; MaxIndex++){
                if(!StringSamePre(strs,MaxIndex,size))
                    break;
            }
            if(MaxIndex ==0){
                return "";
            }
            else return strs[0].substring(0,MaxIndex);
        }
        public boolean StringSamePre(String[] strs,int index,int size){
            for(int i=1;i<size;i++){
                if(strs[i].charAt(index) != strs[0].charAt(index))
                    return false;
            }
            return true;
        }
    }
    static public class NUM20_Solution{
        public boolean isValid(String s) {
            Stack<Character> myStack = new Stack<>();
            int length = s.length();
            for(int i=0;i<length;i++){
                if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                    myStack.push(s.charAt(i));
                }
                else {
                    if(myStack.isEmpty()){
                        return false;
                    }
                    Character temp = myStack.pop();
                    if(!isMatch(temp,s.charAt(i))){
                        return false;
                    }
                }
            }
            if(myStack.empty())
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
    public static void main(String args[]) {
        NUM20_Solution solution = new NUM20_Solution();
        String s= "]";
        System.out.println(solution.isValid(s));
    }
}
