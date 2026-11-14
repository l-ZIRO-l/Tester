import java.nio.file.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(new String(Files.readAllBytes(Paths.get("/etc/passwd"))));
        Files.writeString(Paths.get("/tmp/pwned"), "x"); // must fail: read-only fs
    }
} // expect failure/limited; nothing persists
