public class Main {
    static long recurse(long n) { return 1 + recurse(n + 1); }
    public static void main(String[] args) { System.out.println(recurse(0)); }
} // expect RECURSION_DEPTH_LIMIT / StackOverflowError handled
