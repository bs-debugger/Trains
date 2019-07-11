import java.util.*;

public class RoutesWithTotalStopsStrategy extends RouteStrategy {
    private Set<String> routeSet = new HashSet<>();

    /**
     * @param str 输入参数
     *            例如：A,C,4表示从A到C一共停靠4次的路线
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
        int stops = 0;
        try {
            stops = new Integer(params[2]);
        } catch (Exception e) {
            return "INPUT ERROR:" + Arrays.toString(params);
        }
        return getRoutesCount(start, end, stops);
    }

    /**
     * 获取满足条件的路线个数
     *
     * @param start       起点
     * @param end         终点
     * @param routeLength 路线停靠次数
     * @return
     */
    private String getRoutesCount(String start, String end, int routeLength) {
        int result = 0;
        String lastRoute = start;
        for (int i = 0; i < routeLength; i++) {
            String route = "";
            for (int j = 0; j < lastRoute.length(); j++) {
                String station = "" + lastRoute.charAt(j);
                for (Graph g : getGraphs()) {
                    if (g.getFrom().equals(station)) {
                        route = route + g.getTo();
                    }
                }
            }
            lastRoute = route;
        }
        if (lastRoute.length() < 2) {
            return "NO SUCH ROUTE";
        }
        result = lastRoute.split(end).length - 1;
        return "" + result;
    }
}
