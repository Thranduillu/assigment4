public class Main {
    public static void main(String[] args) {

        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 1);

        System.out.println("BFS from A to D:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, "A");
        bfs.getPath("D").forEach(v -> System.out.print(v + " "));

        System.out.println("\n\nDijkstra from A to D:");
        Search<String> dijkstra = new DijkstraSearch<>(graph, "A");
        dijkstra.getPath("D").forEach(v -> System.out.print(v + " "));
    }
}
