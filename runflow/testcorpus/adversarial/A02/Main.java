public class Main {
    public static void main(String[] args) {
        int x = 0;
        while (x >= 0) {   // multi-line infinite loop
            x = (x + 1) % 2 == 0 ? x + 1 : x + 1;
        }
        System.out.println(x); // unreachable
    }
} // expect STEP_LIMIT_EXCEEDED, partial trace playable
