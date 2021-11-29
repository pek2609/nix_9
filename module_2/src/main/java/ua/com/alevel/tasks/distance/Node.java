package ua.com.alevel.tasks.distance;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String name;
    private int index;
    private int distance = Integer.MAX_VALUE;
    private Map<Integer, Integer> adjacentNodes = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Map<Integer, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Integer, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", distance=" + distance +
                '}';
    }
}
