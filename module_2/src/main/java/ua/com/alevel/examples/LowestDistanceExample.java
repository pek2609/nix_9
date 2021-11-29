package ua.com.alevel.examples;

import ua.com.alevel.TaskRunner;
import ua.com.alevel.tasks.distance.Graph;
import ua.com.alevel.tasks.distance.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

public class LowestDistanceExample implements TaskRunner {

    private final String inputPath;
    private final String outputPath;

    public LowestDistanceExample(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputPath));
            Graph graph = inputGraphFromFile(bufferedReader);
            List<String> routes = inputRoutesFromFile(bufferedReader);
            List<Integer> distances = new ArrayList<>();
            routes.forEach(route -> {
                String[] splitRoute = route.split(" ");
                String departure = splitRoute[0];
                String destination = splitRoute[1];
                graph.calculatedGraph(departure);
                int distance = graph.getNodes().stream()
                        .filter(node -> node.getName().equals(destination))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Can't find node in graph with nodeName = " + destination))
                        .getDistance();
                distances.add(distance);
            });
            FileWriter fileWriter = new FileWriter(outputPath);
            for (Integer distance : distances) {
                fileWriter.write(String.valueOf(distance) + "\n");
            }
            fileWriter.flush();
            System.out.println("Routes:");
            routes.forEach(System.out::println);
            System.out.println("Minimal Distances:");
            distances.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private Graph inputGraphFromFile(BufferedReader reader) throws IOException {
        Graph graph = new Graph();
        int numbOfCities = Integer.parseInt(reader.readLine());
        if (numbOfCities > 100_000) {
            throw new RemoteException("Number of towns must be < 100000, actually number of towns =" + numbOfCities);
        }
        for (int i = 0; i < numbOfCities; i++) {
            String name = reader.readLine();
            int numbOfNeighbours = Integer.parseInt(reader.readLine());
            Map<Integer, Integer> adjacentNodes = new HashMap<>();
            for (int j = 0; j < numbOfNeighbours; j++) {
                String[] indexDistance = reader.readLine().split(" ");
                adjacentNodes.put(Integer.parseInt(indexDistance[0]), Integer.parseInt(indexDistance[1]));
            }
            Node node = new Node();
            node.setName(name);
            node.setIndex(i + 1);
            node.setAdjacentNodes(adjacentNodes);
            graph.addNode(node);
        }
        return graph;
    }

    private List<String> inputRoutesFromFile(BufferedReader reader) throws IOException {
        int numbOfRoads = Integer.parseInt(reader.readLine());
        if (numbOfRoads > 100) {
            throw new RemoteException("Number of roads must be < 100, actually number of roads =" + numbOfRoads);
        }
        List<String> routes = new ArrayList<>();
        for (int i = 0; i < numbOfRoads; i++) {
            routes.add(reader.readLine());
        }
        return routes;
    }
}
