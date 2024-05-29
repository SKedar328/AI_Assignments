import java.util.*;
public class Aostar {
    public static Map<String, Integer> calculateCost(
            Map<String, Integer> heuristic,
            Map<String, List<String>> condition,
            int weight) {
        Map<String, Integer> costMap = new HashMap<>();

        if (condition.containsKey("AND")) {
            List<String> andNodes = condition.get("AND");
            String andPath = String.join(" AND ", andNodes);
            int totalCost = andNodes.stream()
                    .mapToInt(node -> heuristic.get(node) + weight)
                    .sum();
            costMap.put(andPath, totalCost);
        }

        if (condition.containsKey("OR")) {
            List<String> orNodes = condition.get("OR");
            String orPath = String.join(" OR ", orNodes);
            int minCost = orNodes.stream()
                    .mapToInt(node -> heuristic.get(node) + weight)
                    .min()
                    .getAsInt();
            costMap.put(orPath, minCost);
        }

        return costMap;
    }

    public static Map<String, Map<String, Integer>> updateHeuristicCosts(
            Map<String, Integer> heuristic,
            Map<String, Map<String, List<String>>> conditions,
            int weight) {
        List<String> nodes = new ArrayList<>(conditions.keySet());
        Collections.reverse(nodes);
        Map<String, Map<String, Integer>> minCostMap = new HashMap<>();

        for (String node : nodes) {
            Map<String, List<String>> condition = conditions.get(node);
            System.out.printf("%s: %s >>> %s%n", node, condition, calculateCost(heuristic, condition, weight));
            Map<String, Integer> cost = calculateCost(heuristic, condition, weight);
            heuristic.put(node, Collections.min(cost.values()));
            minCostMap.put(node, calculateCost(heuristic, condition, weight));
        }

        return minCostMap;
    }

    public static String findShortestPath(
            String startNode,
            Map<String, Map<String, Integer>> updatedCosts,
            Map<String, Integer> heuristic) {
        String path = startNode;

        if (updatedCosts.containsKey(startNode)) {
            int minCost = Collections.min(updatedCosts.get(startNode).values());
            List<String> keys = new ArrayList<>(updatedCosts.get(startNode).keySet());
            List<Integer> values = new ArrayList<>(updatedCosts.get(startNode).values());
            int index = values.indexOf(minCost);
            List<String> nextNodes = Arrays.asList(keys.get(index).split(" "));

            if (nextNodes.size() == 1) {
                startNode = nextNodes.get(0);
                path += "<--" + findShortestPath(startNode, updatedCosts, heuristic);
            } else {
                path += "<--(" + keys.get(index) + ") ";
                startNode = nextNodes.get(0);
                path += "[" + findShortestPath(startNode, updatedCosts, heuristic) + " + ";
                startNode = nextNodes.get(nextNodes.size() - 1);
                path += findShortestPath(startNode, updatedCosts, heuristic) + "]";
            }
        }

        return path;
    }

    public static void main(String[] args) {
        Map<String, Integer> heuristic = new HashMap<>();
        heuristic.put("A", -1);
        heuristic.put("B", 5);
        heuristic.put("C", 2);
        heuristic.put("D", 4);
        heuristic.put("E", 7);
        heuristic.put("F", 9);
        heuristic.put("G", 3);
        heuristic.put("H", 0);
        heuristic.put("I", 0);
        heuristic.put("J", 0);

        Map<String, Map<String, List<String>>> conditions = new HashMap<>();
        Map<String, List<String>> aConditions = new HashMap<>();
        aConditions.put("OR", Arrays.asList("B"));
        aConditions.put("AND", Arrays.asList("C", "D"));
        conditions.put("A", aConditions);

        Map<String, List<String>> bConditions = new HashMap<>();
        bConditions.put("OR", Arrays.asList("E", "F"));
        conditions.put("B", bConditions);

        Map<String, List<String>> cConditions = new HashMap<>();
        cConditions.put("OR", Arrays.asList("G"));
        cConditions.put("AND", Arrays.asList("H", "I"));
        conditions.put("C", cConditions);

        Map<String, List<String>> dConditions = new HashMap<>();
        dConditions.put("OR", Arrays.asList("J"));
        conditions.put("D", dConditions);

        int weight = 1;

        System.out.println("Updated Cost:");
        Map<String, Map<String, Integer>> updatedCostMap = updateHeuristicCosts(heuristic, conditions, weight);
        System.out.println("*".repeat(75));
        System.out.println("Shortest Path:");
        System.out.println(findShortestPath("A", updatedCostMap, heuristic));
    }
}
