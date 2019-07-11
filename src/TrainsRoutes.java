import java.io.IOException;
import java.util.*;

public class TrainsRoutes {
    public static Map<String, String> neighborMap = new HashMap<>();


    public static void main(String[] args) {
        //初始化路线列表
        List<Graph> graphs = new ArrayList<>();
        Graph g1 = new Graph("A", "B", 5);
        Graph g2 = new Graph("B", "C", 4);
        Graph g3 = new Graph("C", "D", 8);
        Graph g4 = new Graph("D", "C", 8);
        Graph g5 = new Graph("D", "E", 6);
        Graph g6 = new Graph("A", "D", 5);
        Graph g7 = new Graph("C", "E", 2);
        Graph g8 = new Graph("E", "B", 3);
        Graph g9 = new Graph("A", "E", 7);
        graphs.add(g1);
        graphs.add(g2);
        graphs.add(g3);
        graphs.add(g4);
        graphs.add(g5);
        graphs.add(g6);
        graphs.add(g7);
        graphs.add(g8);
        graphs.add(g9);

//        RouteStrategy strategy = null;
//        strategy = new RouteDistanceStrategy();
//        strategy.setGraphs(graphs);
//        System.out.println(strategy.getResult("A-B-C"));
//        System.out.println(strategy.getResult("A-D"));
//        System.out.println(strategy.getResult("A-D-C"));
//        System.out.println(strategy.getResult("A-E-B-C-D"));
//        System.out.println(strategy.getResult("A-E-D"));
//
//        strategy = new RoutesWithMaxStopsStrategy();
//        strategy.setGraphs(graphs);
//        System.out.println(strategy.getResult("C,C,3"));
//
//        strategy = new RoutesWithTotalStopsStrategy();
//        strategy.setGraphs(graphs);
//        System.out.println(strategy.getResult("A,C,4"));
//
//        strategy = new ShortestLengthStrategy();
//        strategy.setGraphs(graphs);
//        System.out.println(strategy.getResult("A,C"));
//        System.out.println(strategy.getResult("B,B"));
//
//        strategy = new RoutesWithDistanceStrategy();
//        strategy.setGraphs(graphs);
//        System.out.println(strategy.getResult("C,C,30"));
        Scanner in = new Scanner(System.in);
        if(in.hasNext()) {
            Integer number = new Integer(in.nextLine());
            String s = Integer.toBinaryString(number);
            char[] array = s.toCharArray();
            int count = 0;
            for(int i = 0; i < array.length; i++) {
                int j = (int)array[i];
                if(j == 1) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }


}