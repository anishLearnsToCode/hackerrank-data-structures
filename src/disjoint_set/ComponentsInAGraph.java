// https://www.hackerrank.com/challenges/components-in-graph

package disjoint_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ComponentsInAGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        Graph graph = new Graph(2 * length);
        for (int index = 0 ; index < length ; index++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            graph.addEdge(from, to);
        }
        printDisjointSetsMinMax(graph);
    }

    private static void printDisjointSetsMinMax(Graph graph) {
        List<Set<Graph.Vertex>> disjointSets = graph.disjointSets();
        int minimum = min(disjointSets);
        int maximum = max(disjointSets);
        System.out.println(minimum + " " + maximum);
    }

    private static int min(List<Set<Graph.Vertex>> sets) {
        int result = Integer.MAX_VALUE;
        for (Set<Graph.Vertex> set : sets) {
            if (set.size() == 1) {
                continue;
            }
            result = Math.min(result, set.size());
        }
        return result;
    }

    private static int max(List<Set<Graph.Vertex>> sets) {
        int result = Integer.MIN_VALUE;
        for (Set<Graph.Vertex> set : sets) {
            if (set.size() == 1) {
                continue;
            }
            result = Math.max(result, set.size());
        }
        return result;
    }

    private static class Graph {
        private final Map<Integer, Vertex> vertices;

        private static class Vertex {
            int data;
            Set<Vertex> edges = new HashSet<>();

            Vertex(int data) {
                this.data = data;
            }

            void addEdge(Vertex to) {
                this.edges.add(to);
            }
        }

        Graph(int size) {
            vertices = new HashMap<>(size);
            for (int number = 1 ; number <= size ; number++) {
                addVertex(number);
            }
        }

        private void addVertex(int data) {
            this.vertices.put(data, new Vertex(data));
        }

        private void addEdge(int from, int to) {
            Vertex fromVertex = vertices.get(from);
            Vertex toVertex = vertices.get(to);
            fromVertex.addEdge(toVertex);
            toVertex.addEdge(fromVertex);
        }

        private List<Set<Vertex>> disjointSets() {
            List<Set<Vertex>> disjointSets = new ArrayList<>();
            Set<Vertex> visited = new HashSet<>();
            for (Vertex vertex : this.vertices.values()) {
                if (visited.contains(vertex)) {
                    continue;
                }

                Set<Vertex> disjointSet = new HashSet<>();
                Queue<Vertex> queue = new LinkedList<>();
                queue.add(vertex);

                while (!queue.isEmpty()) {
                    Vertex top = queue.poll();
                    if (visited.contains(top)) {
                        continue;
                    }
                    visited.add(top);
                    disjointSet.add(top);

                    queue.addAll(top.edges);
                }
                disjointSets.add(disjointSet);
            }
            return disjointSets;
        }
    }
}
