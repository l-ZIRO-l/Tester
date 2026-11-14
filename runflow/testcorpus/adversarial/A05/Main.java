import java.util.*;
public class Main {
    static List<byte[]> keep = new ArrayList<>();
    public static void main(String[] args) {
        while (true) keep.add(new byte[10_000_000]); // allocate until OOM
    }
} // expect MEMORY_LIMIT_EXCEEDED, container killed cleanly
