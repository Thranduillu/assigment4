import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
    private Vertex<V> startVertex;

    public BreadthFirstSearch(WeightedGraph<V> graph, V start) {
        this.startVertex = graph.getVertex(start);
        bfs(graph);
    }

    private void bfs(WeightedGraph<V> graph) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();
        queue.add(startVertex);
        visited.add(startVertex);
        parentMap.put(startVertex, null);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }

    public List<Vertex<V>> getPath(V destination) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> end = parentMap.keySet().stream()
                .filter(v -> v.getData().equals(destination)).findFirst().orElse(null);

        for (Vertex<V> at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
