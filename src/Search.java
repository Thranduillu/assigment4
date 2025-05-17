import java.util.List;

public interface Search<V> {
    List<Vertex<V>> getPath(V destination);
}
