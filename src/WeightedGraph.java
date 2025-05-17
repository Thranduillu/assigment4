import java.util.*;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();

    public Vertex<V> addVertex(V data) {
        return vertices.computeIfAbsent(data, Vertex::new);
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> srcVertex = addVertex(source);
        Vertex<V> destVertex = addVertex(dest);
        srcVertex.addAdjacentVertex(destVertex, weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}
