package Graph;

import java.util.Collections;
import java.util.LinkedList;

public class VertexDeletion {
    private Graph graph ;
    private boolean isConnected ;
    private int numberOfComponents ;
    private int maximumComponentSize ;
    int numberOfVerticesToBeDeleted = 0 ;

    public VertexDeletion(Graph graph , int componentSize) throws NullPointerException, IndexOutOfBoundsException{
        if(componentSize<1){
            throw new IndexOutOfBoundsException();
        }

        if(graph==null){ throw new NullPointerException() ; }
        //this.solutionExists = false ; // by default
        this.maximumComponentSize = componentSize ;
        this.graph = graph ;
        graph.initialiseDFS();
        this.isConnected = graph.isConnected() ;
        this.numberOfComponents = graph.getCurrentNumberOfComponents() ;
    }


    public void trivialCheck(){

    }
    public int solution(Graph graph , int c) throws NullPointerException{
        if(graph == null){
            throw new NullPointerException("The graph is empty");
        }

        /*
        run a trivial check
        * the maximumComponentSize > the number of vertices, the solution already exists
        *
        */
        if(this.graph.getV()<= c){ return 0 ; }
        if(c<=0){
            return Integer.MIN_VALUE ;
        }

        if(!graph.isConnected()) {
            int sum = 0 ;
            for (int counter = 1; counter <= this.numberOfComponents; counter++) {
                // compute for different components
                int min = Integer.MAX_VALUE , max = Integer.MIN_VALUE ;
                for(Node n : graph.nodes){
                    if(n.getComponentNumber()==counter){
                        if(n.getVertexNumber()<min){
                            min = n.getVertexNumber() ;
                        }
                        if(n.getComponentNumber()>max){
                            max = n.getVertexNumber() ;
                        }
                    }

                }
                Graph subGraph = new Graph(graph.getV() , min , max , counter) ;
                if(subGraph.nodes.size()<=c){
                    continue;
                }
                Collections.sort(subGraph.nodes , new SortByDegree());
                boolean notFound = true ;
                int nodesDeleted  = 0 ;
                while(notFound){
                    for(Node n: subGraph.nodes){
                        subGraph.removeVertex(n.getVertexNumber());
                        nodesDeleted+=1 ;
                        int maximum_componentSize = Integer.MIN_VALUE ;
                        for(LinkedList<Node>connectedNodeList: subGraph.getAdjecencyList()){
                            if(connectedNodeList!=null){
                                if(connectedNodeList.size()>maximum_componentSize){
                                    maximum_componentSize =connectedNodeList.size() ;
                                }
                            }
                        }
                        if(maximum_componentSize<=c){
                            sum+=nodesDeleted ;
                            notFound=false ;
                            break ;
                        }
                    }
                }
            }
            return sum ;
        }
        else{
            // component for only one connected component
            Collections.sort(graph.nodes , new SortByDegree());

            boolean notFound = true ;
            int nodesDeleted  = 0 ;
            while(notFound){
                for(Node n: graph.nodes){
                    graph.removeVertex(n.getVertexNumber());
                    nodesDeleted+=1 ;
                    int maximum_componentSize = Integer.MIN_VALUE ;
                    for(LinkedList<Node>connectedNodeList: graph.getAdjecencyList()){
                        if(connectedNodeList!=null){
                            if(connectedNodeList.size()>maximum_componentSize){
                                maximum_componentSize =connectedNodeList.size() ;
                            }
                        }
                    }
                    if(maximum_componentSize<=c){
                        notFound=false ;
                        break ;
                    }
                }
            }
            return nodesDeleted ;
        }

    }

    public Graph getGraph() { return graph; }
    public void setGraph(Graph graph) { this.graph = graph; }
    public boolean isConnected() {return isConnected; }
    public void setConnected(boolean connected) { isConnected = connected; }
    public int getNumberOfComponents() { return numberOfComponents; }
    public void setNumberOfComponents(int numberOfComponents) { this.numberOfComponents = numberOfComponents; }
    public int getComponentSize() { return maximumComponentSize; }
    public void setComponentSize(int componentSize) { this.maximumComponentSize = componentSize; }

    public static void main(String args[]){

        Graph graph = new Graph(5) ;
        VertexDeletion instance = new VertexDeletion(graph , 1) ;
        graph.addEdge(1,  0 , 0);
        //graph.addEdge(1 ,2 , 0);
        graph.addEdge(2, 3 , 0);
        graph.addEdge(3, 4 , 0);
        graph.initialiseDFS();
        System.out.println(instance.solution(graph , 2));
    }
}
