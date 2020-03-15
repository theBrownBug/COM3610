package Graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class Node implements Cloneable {
    private int vertexNumber ;
    LinkedList<Edge> edges ;
    private Set<Integer> neighbours = new TreeSet<>() ;
    private int degree ;
    private Graph graph ;
    // the component number is >1
    private int componentNumber ;


    public Node(){}
    public Node(int vertexNumber, int number , Graph graph){
        try{
            if(graph == null){
                throw new NullPointerException() ;
            }
            this.vertexNumber = vertexNumber ;
            this.componentNumber = number ;
            this.graph = graph ;
        }catch (NullPointerException e){}
    }

    public Node(int vertexNumber , int number){
        this.vertexNumber = vertexNumber ;
        this.componentNumber = number ;
        this.edges = new LinkedList<Edge>() ;
        this.neighbours = new TreeSet<Integer>() ;

    }

    public Node(int vertexNumber , int componentNumber , LinkedList<Edge> edges){
        this.vertexNumber = vertexNumber ;
        this.componentNumber = componentNumber ;
        this.edges = edges ;
        this.neighbours = new TreeSet<>();
    }

    public void addEdge(int destination){
        // dont include self loops
        if(destination!=this.vertexNumber) {
            Edge newEdge = new Edge(this.vertexNumber, destination);
            this.edges.add(newEdge);
            this.neighbours.add(destination);
            setDegree(this.edges.size());
        }
    }

    public LinkedList<Edge> getEdges() { return edges; }
    public void setEdges(LinkedList<Edge> edges) { this.edges = edges; }

    public void setComponentNumber(int componentNumber) {
        this.componentNumber = componentNumber;
    }

    public int getComponentNumber() {
        return componentNumber;
    }

    public int getVertexNumber() { return vertexNumber; }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Node clone() throws CloneNotSupportedException{
        return (Node)super.clone() ;
    }
    public void computeDegree(){
        this.degree = this.edges.size();
    }




}

class SortByDegree implements Comparator<Node>{
    public int compare(Node node1 , Node node2){
        return node2.getDegree() - node1.getDegree() ;
    }
}


