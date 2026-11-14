import java.util.*;
public class Main {
    static List<Integer> bfs(List<List<Integer>> adj, int start) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        return order;
    }
    public static void main(String[] args) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) adj.add(new ArrayList<>());
        adj.get(0).addAll(Arrays.asList(1, 2));
        adj.get(1).add(3);
        adj.get(2).add(4);
        adj.get(3).add(4);
        System.out.println(bfs(adj, 0));
    }
}
