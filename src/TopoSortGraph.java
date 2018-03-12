import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopoSortGraph {


    // class for directed Graph - each vertex is labelled with an integer
    class DiGraph {

        // graph has vertices - V is the # of vertices in graph - 0, 1, 2, ... till V
        int V;

        // graph has edges - E is the # of edges in graph
        int E;

        // adjacency list for each vertex
        Map<Integer, List<Integer>> adjacencyListForAllVertices;

        // constructor
        DiGraph(int V) {
            this.V = V;
            this.E = 0;
            adjacencyListForAllVertices = new HashMap<>();

            for (int v = 0; v < V; v++) {
                adjacencyListForAllVertices.put(v, new ArrayList<>());
            }
        }

        int getNumberOfVertices() {
            return V;
        }

        int getNumberOfEdges() {
            return E;
        }

        void addEdge(int v1, int v2) {
            List<Integer> neighborsForv1 = adjacencyListForAllVertices.get(v1);
            neighborsForv1.add(v1);
            E += 1;
        }

    }
}
