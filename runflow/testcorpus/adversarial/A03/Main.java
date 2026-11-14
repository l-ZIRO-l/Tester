public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10_000_000; i++) System.out.println("flood " + i);
    }
} // expect OUTPUT_LIMIT_EXCEEDED
