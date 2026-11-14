import java.util.Stack;
public class Main {
    static boolean balanced(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') st.push(c);
            else if (c == ')' || c == ']' || c == '}') {
                if (st.isEmpty()) return false;
                char open = st.pop();
                if (c == ')' && open != '(') return false;
                if (c == ']' && open != '[') return false;
                if (c == '}' && open != '{') return false;
            }
        }
        return st.isEmpty();
    }
    public static void main(String[] args) {
        System.out.println(balanced("([]{})"));
        System.out.println(balanced("([)]"));
    }
}
