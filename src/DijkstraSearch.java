import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
    private Map<Vertex<V>, Double> distances = new HashMap<>();
    private Vertex<V> startVertex;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.startVertex = graph.getVertex(start);
        dijkstra();
    }

    private void dijkstra() {
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        distances.put(startVertex, 0.0);
        queue.add(startVertex);
        parentMap.put(startVertex, null);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double newDist = distances.get(current) + weight;

                if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distances.put(neighbor, newDist);
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
