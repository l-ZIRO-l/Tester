import java.net.*;
public class Main {
    public static void main(String[] args) throws Exception {
        try (Socket s = new Socket("example.com", 80)) { System.out.println("connected"); }
    }
} // expect failure: no network
