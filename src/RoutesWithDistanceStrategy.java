import java.util.*;

public class RoutesWithDistanceStrategy extends RouteStrategy {
    private Set<String> routeSet = new HashSet<>();

    /**
     * @param str 输入参数
     *            例如：C,C,30表示从C到C，距离不超过30的路线有多少条
     * @return
     */
    @Override
    String getResult(String str) {
        if (!str.contains(",")) {
            return "INPUT ERROR:" + str;
        }
        String[] params = str.split(",");
        if (params.length != 3) {
            return "INPUT ERROR:" + Arrays.toString(params);
        }
        String start = params[0];
        String end = params[1];
        int distance = 0;
        try {
            distance = new Integer(params[2]);
        } catch (Exception e) {
            return "INPUT ERROR:" + Arrays.toString(params);
        }
        return getRoutesCount(start, end, distance);
    }

    private String getRoutesCount(String start, String end, int distance) {
        List<String> routes = getRoutes(start, end, 0, distance, new ArrayList<>());
        return "" + routes.size();
    }

    /**
     * 不超过给定运行距离的路线
     *
     * @param route         火车运行路径
     * @param end           终点
     * @param totalDistance 运行总距离
     * @param maxDistance   最大运行距离
     * @param list          满足条件的路线
     * @return
     */
    List<String> getRoutes(String route, String end, int totalDistance, int maxDistance, List<String> routes) {
        if (totalDistance >= maxDistance) {
            return routes;
        }
        if (totalDistance > 0 && route.endsWith(end)) {
            routes.add(route);
        }
        char[] charList = route.toCharArray();
        String lastStation = "" + charList[charList.length - 1];
        for (Graph g : getGraphs()) {
            int distance = g.getDistance();
            if (g.getFrom().equals(lastStation)) {
                getRoutes(route + g.getTo(), end, totalDistance + distance, maxDistance, routes);
            }
        }
        return routes;
    }
}
