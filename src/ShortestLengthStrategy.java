import java.util.*;

public class ShortestLengthStrategy extends RouteStrategy {
    private Set<String> routeSet = new HashSet<>();
    private int shortedVal = Integer.MAX_VALUE;
    private String shortedPath = "";

    @Override
    public String getResult(String str) {
        if (!str.contains(",")) {
            return "INPUT ERROR:" + str;
        }
        String[] stations = str.split(",");
        if (stations.length != 2) {
            return "INPUT ERROR:" + Arrays.toString(stations);
        }
        String start = stations[0];
        String end = stations[1];
        return "" + getShortestLength(start, end, 0);
    }

    /**
     * 获取两点之间的最短路线距离
     *
     * @param route    火车运行路线
     * @param end      终点
     * @param distance 运行距离
     * @return
     */
    private int getShortestLength(String route, String end, int distance) {
        if (route.endsWith(end) && distance < shortedVal && distance > 0) {
            shortedPath = route;
            shortedVal = distance;
            return distance;
        }
        char[] charList = route.toCharArray();
        String lastStation = "" + charList[charList.length - 1];
        for (Graph g : getGraphs()) {
            if (g.getFrom().equals(lastStation)) {
                if (route.indexOf(g.getTo()) > 0) {
                    continue;
                } else {
                    getShortestLength(route + g.getTo(), end, distance + g.getDistance());
                }

            }
        }
        return shortedVal;
    }
}
