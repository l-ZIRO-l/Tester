public class Main {
    public static void main(String[] args) {
        System.out.println("before exit");
        System.exit(0);
        System.out.println("after exit"); // never runs
    }
} // expect graceful end, partial trace + note
