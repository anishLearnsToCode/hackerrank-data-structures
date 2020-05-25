package trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class CustomTree<T> {
    private Scanner s = new Scanner(System.in);

    T data;
    int frequency;
    CustomTree<T> left;
    CustomTree<T> right;

    CustomTree(){}
    CustomTree(T data){
        this.data = data;
    }

    public void input(){
        input(this);
    }

    private CustomTree input(CustomTree root){
        System.out.print("Enter Data : ");
        int data = s.nextInt();

        if(data != -1)
            root.data = data;
        else return null;

        root.left = input(new CustomTree());
        root.right = input(new CustomTree());

        return root;
    }

    public void print(){
        print(this);
    }

    private void print(CustomTree root){
        if(root == null)
            return;

        System.out.println(root.data + " : " +
                (root.left == null ? "NULL " : root.left.data) + " " + (root.right == null ? "NULL " : root.right.data) );

        print(root.left);
        print(root.right);
    }
}

class MultipleDataTree<A, B> {
    A data;
    B frequency;
    MultipleDataTree<A, B> left;
    MultipleDataTree<A, B> right;

    MultipleDataTree(){}
    MultipleDataTree(A data){
        this.data = data;
    }
    MultipleDataTree(A data, B frequency){
        this.frequency = frequency;
        this.data = data;
    }
}

class Vertex {
    ArrayList<Edge> edgeList;
    int data;

    Vertex(int data){
        this.data = data;
        edgeList = new ArrayList<>();
    }
    Vertex(){}

    public void addEdge(Edge edge){
        edgeList.add(edge);
    }
}

class Edge{
    Vertex to, from;
    int connectionData;

    Edge(){}
    Edge(int connectionData){
        this.connectionData = connectionData;
        to = new Vertex();
        from = new Vertex();
    }
    Edge(int connectionData, Vertex to, Vertex from){
        this.connectionData = connectionData;
        this.to = to;
        this.from = from;
    }
}

class graph{
    private HashMap<Integer, Vertex> vertexMap = new HashMap<>();
    private HashMap<Vertex, Object> vertexObjectHashMap;
    private int vertices;

    graph(){
        vertexMap = new HashMap<>();
        vertexObjectHashMap = new HashMap<>();
        vertices = 0;
    }

    public boolean addVertex(int data) {
        if(vertexMap.containsKey(data))
            return false;

        Vertex vertex = new Vertex(data);
        vertexMap.put(data, vertex);
        vertexObjectHashMap.put(vertex, new Object());
        vertices++;
        return true;
    }

    public boolean removeVertex(int data){
        if(vertexMap.containsKey(data)){
            vertexMap.remove(data);
            vertices--;
            return true;
        }
        return false;
    }

    public boolean removeVertex(Vertex vertex){
        if(vertexObjectHashMap.containsKey(vertex)){
            vertexObjectHashMap.remove(vertex);
            vertices--;
            return true;
        }
        return false;
    }

    public boolean addEdge(int connectionData, Vertex to, Vertex from){
        Edge edge = new Edge(connectionData, to, from);
        to.addEdge(edge);
        from.addEdge(edge);
        return true;
    }

    public boolean addEdge(int connectionData, int vertexData1, int vertexData2){
        Edge edge = new Edge(connectionData, vertexMap.get(vertexData1), vertexMap.get(vertexData2));

        if(vertexMap.containsKey(vertexData1)) vertexMap.get(vertexData1).addEdge(edge);
        else return false;

        if(vertexMap.containsKey(vertexData2)) vertexMap.get(vertexData2).addEdge(edge);
        else return false;

        return true;
    }

    public Vertex getVertex(int data){
        if(vertexMap.containsKey(data))
            return vertexMap.get(data);

        return new Vertex();
    }

    public void print(){
        print(getVertex(1));
    }

    private static void print(Vertex vertex){
        if(vertex == null)
            return;

        System.out.println(vertex.data + " : ");
        for(int index = 0 ; index<vertex.edgeList.size() ; index++){
            System.out.print(vertex.edgeList.get(index).to.data +" ");
        }

        for(int index =0 ; index<vertex.edgeList.size() ; index++){
            print(vertex.edgeList.get(index).to);
        }
    }

    public int vertices(){
        return vertices;
    }
}
