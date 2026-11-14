public class Main {
    public static void main(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec("id"); // or ProcessBuilder
        System.out.println(p.getInputStream().read());
    }
} // expect REJECTED pre-check AND blocked if bypassed (no host effect)
