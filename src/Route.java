import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private List<Graph> graphs;

    public Route() {
        this.graphs = new ArrayList<>();
    }

    public List<Graph> getGraphs() {
        return graphs;
    }

    /**
     * 获取整个路线中要停几次
     * @return
     */
    public int getLength() {
        if (graphs == null) {
            return -1;
        }
        return graphs.size();
    }

    /**
     * 获取整个路线的距离长度
     * @return
     */
    public int getDistance() {
        if (graphs != null) {
            int distance = 0;
            for (Graph graph : graphs) {
                distance += graph.getDistance();
            }
            return distance;
        } else {
            return -1;
        }
    }

    /**
     * 获取路线中最后一段
     * @return
     */
    public Graph getLast() {
        Graph graph = null;
        if (graphs != null && graphs.size() > 0) {
            graph = graphs.get(graphs.size() - 1);
        }
        return graph;
    }

    @Override
    public String toString() {
        String result = "";
        for (Graph graph : graphs) {
            result += graph.getFrom() + graph.getTo();
        }
        return result;
    }
}
