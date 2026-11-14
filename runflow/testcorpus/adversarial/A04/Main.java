public class Main {
    public static void main(String[] args) {
        int[] big = new int[Integer.MAX_VALUE]; // OOM
        System.out.println(big.length);
    }
} // expect RUNTIME_EXCEPTION (OutOfMemoryError), host unaffected
