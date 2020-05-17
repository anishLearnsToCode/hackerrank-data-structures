import java.util.Scanner;

public class KittysCalculationsOnTree {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        graph undirectedGraph = new graph();

        int nodes = s.nextInt();
        int sets = s.nextInt();

        prepareGraph(undirectedGraph, nodes);
        undirectedGraph.print();
    }

    private static void prepareGraph(graph undirectedGraph, int nodes){
        undirectedGraph.addVertex(1);

        for(int index=0 ; index<nodes-1 ; index++){
            int vertexFromData = s.nextInt();
            int vertexToData = s.nextInt();

            System.out.println(undirectedGraph.addVertex(vertexToData));
            undirectedGraph.addEdge(0, undirectedGraph.getVertex(vertexToData),
                    undirectedGraph.getVertex(vertexFromData));
        }
    }
}
