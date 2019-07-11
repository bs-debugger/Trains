import java.util.*;

/**
 *
 */
public abstract class RouteStrategy {
    private List<Graph> graphs;

    public List<Graph> getGraphs() {
        return graphs;
    }

    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
    }

    abstract String getResult(String str);

}
