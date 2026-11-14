import java.util.ArrayDeque;
import java.util.Queue;
public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= 4; i++) q.add(i);          // enqueue 1..4
        int first = q.peek();
        q.poll();                                        // dequeue 1
        q.add(5);                                        // enqueue 5
        while (!q.isEmpty()) System.out.print(q.poll() + " ");
        System.out.println();
        System.out.println("first was: " + first);
    }
}
