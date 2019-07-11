import java.util.*;

public class RoutesWithMaxStopsStrategy extends RouteStrategy {
    private Set<String> routeSet = new HashSet<>();

    /**
     * @param str 输入参数
     *            例如：C,C,3表示从C到C，最多停靠3次的路线有几条
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
        int maxStops = 0;
        try {
            maxStops = new Integer(params[2]);
        } catch (Exception e) {
            return "INPUT ERROR:" + Arrays.toString(params);
        }
        return getRoutesCount(start, end, maxStops);
    }

    private String getRoutesCount(String start, String end, int maxStops) {
        List<String> totalRoutes = getRoutes(start, end, maxStops, new ArrayList<>());

        if (totalRoutes.size() == 0) {
            return "NO SUCH ROUTE";
        }
        return "" + totalRoutes.size();
    }

    /**
     * 获取满足条件的路线
     *
     * @param start    起点
     * @param path     路线
     * @param maxStops 最多停靠次数
     * @param list     路线图
     * @return
     */
    private List<String> getRoutes(String start, String path, int maxStops, List<String> list) {
        if (path.length() - 1 > maxStops) {
            return list;
        }
        if (path.length() > 1 && path.endsWith(start)) {
            list.add(path);
        }
        char[] charList = path.toCharArray();
        String lastStation = "" + charList[charList.length - 1];
        for (Graph g : getGraphs()) {
            if (g.getFrom().equals(lastStation)) {
                getRoutes(start, path + g.getTo(), maxStops, list);
            }
        }
        return list;
    }

}
