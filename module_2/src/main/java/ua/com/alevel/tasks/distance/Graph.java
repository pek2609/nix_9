package ua.com.alevel.tasks.distance;

import java.util.*;

public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void calculatedGraph(String nodeName) {
        Node source = nodes.stream()
                .filter(node -> node.getName().equals(nodeName))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Can't find node in graph with nodeName = " + nodeName));
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Node cur = getNodeWithLowestDistance(unsettledNodes);
            unsettledNodes.remove(cur);
            for (Map.Entry<Integer, Integer> entry : cur.getAdjacentNodes().entrySet()) {
                Node adjacentNode = nodes.stream()
                        .filter(node -> node.getIndex() == entry.getKey())
                        .findFirst()
                        .orElseThrow(()->new NoSuchElementException("Can't find node in grapgh with index = " + entry.getKey()));
                int distance = entry.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, distance, cur);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(cur);
        }
        nodes = new ArrayList<>(settledNodes);
    }

    private static void calculateMinimumDistance(Node adjacentNode, int distanceFromSrcToAdjNode, Node sourceNode) {
        int newAdjDistance = sourceNode.getDistance() + distanceFromSrcToAdjNode;
        if (newAdjDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newAdjDistance);
        }
    }

    private static Node getNodeWithLowestDistance(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int curNodeDistance = node.getDistance();
            if (curNodeDistance < lowestDistance) {
                lowestDistance = curNodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
