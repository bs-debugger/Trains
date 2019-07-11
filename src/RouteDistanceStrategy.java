import java.util.List;

public class RouteDistanceStrategy extends RouteStrategy {

    /**
     * 获取指定路线图的运行路径
     *
     * @param str 输入路线图
     *            例如：A-B-C
     * @return
     */
    @Override
    public String getResult(String str) {
        if (!str.contains("-")) {
            return "INPUT ERROR:" + str;
        }
        String[] stations = str.split("-");
        int totalDistance = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            String from = stations[i];
            String to = stations[i + 1];
            Graph g = pickUpGraph(getGraphs(), from, to);
            if (g == null) {
                return "NO SUCH ROUTE";
            }
            totalDistance += g.getDistance();
        }
        return "" + totalDistance;
    }

    private Graph pickUpGraph(List<Graph> list, String from, String to) {
        for (Graph g : list) {
            if (g.getFrom().equals(from) && g.getTo().equals(to)) {
                return g;
            }
        }
        return null;
    }
}
