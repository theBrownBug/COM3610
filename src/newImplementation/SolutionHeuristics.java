package newImplementation;
import org.jgrapht.*;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.util.VertexDegreeComparator;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.*;
import java.net.*;
import java.util.*;

public class SolutionHeuristics {

    private static int DEFAULT_NUMBER_OF_NODES = 8;
    /**
     * Look at methods to duplicate graphs ()
     * */

    public static int computeSolution(Graph<Integer, DefaultEdge> graph, int c) {
        if (isConnected(graph)) {
            if (graph.vertexSet().size() <= c) {
                //return sum;
                return 0;
            }
            List<Integer> vertexList = returnConnectedVertices(graph , c +1) ;
            int min = Integer.MAX_VALUE;
            if(vertexList!=null) {
                for (Integer i : vertexList) {
                    Graph<Integer, DefaultEdge> duplicate = createDuplicate(graph);
                    if (duplicate.removeVertex(i)) {
                        int sol = 1 + computeSolution(duplicate, c);
                        if (sol < min) {
                            min = sol;
                        }
                    }
                }
            }else{
                System.out.println("Vertex set is null");
            }

            return min;

        } else {



            BiconnectivityInspector<Integer, DefaultEdge> inspector = new BiconnectivityInspector<>(graph);
            int sum = 0;
            for (Graph<Integer, DefaultEdge> component : inspector.getConnectedComponents()) {

                if (component.vertexSet().size() > c) {
                    sum += computeSolution(component, c);
                }
            }
            return sum;


        }
    }


    public static Graph<Integer , DefaultEdge> createDuplicate(Graph<Integer , DefaultEdge> graph){
        Graph<Integer , DefaultEdge> duplicate  = new SimpleGraph<>(DefaultEdge.class);
        for(Integer i: graph.vertexSet()){
            duplicate.addVertex(i) ;

        }


        for(int i : duplicate.vertexSet()) {
            for (int counter = 0; counter < duplicate.vertexSet().size() + i; counter++) {
                if (graph.containsEdge(i, counter)) {
                    duplicate.addEdge(i, counter);
                }
            }
        }
        return duplicate ;
    }


    /**
     *
     * checks whether graph is connected or not
     *
     *
     * */
    public static boolean isConnected(Graph<Integer, DefaultEdge> graph) {
        BiconnectivityInspector inspector = new BiconnectivityInspector(graph);
        return inspector.isConnected();
    }


    /**
     * c = c+1 ;
     *
     * */
    public static ArrayList<Integer> returnConnectedVertices(Graph<Integer , DefaultEdge> graph, int c){
        if(isConnected(graph)){
            if(graph.vertexSet().size()<c){
                return null ;
            }else{
                List<Integer> toReturn = getConnectedVertices(graph, c) ;
                // mod
                if(toReturn.size()<c){return null; }
                return (ArrayList<Integer>) toReturn ;
            }
        }
        else{

            BiconnectivityInspector<Integer , DefaultEdge> inspector = new BiconnectivityInspector(graph);
            for(Graph<Integer , DefaultEdge> g : inspector.getConnectedComponents()){
                if(g.vertexSet().size()>c){
                    return SolutionHeuristics.getConnectedVertices(graph , c) ;
                }
                // why??
                else{ return null ; // the size of array is small than c , trivial solution exists
                     }
            }
        }
        return null ;
    }



    public static void main(String args[]){

        Graph<Integer , DefaultEdge> sampleGraph = generateGraph();
        try {
            System.out.println(Solution.computeSolution(sampleGraph, 2));
        }catch (Exception e){
            System.out.println(e);
        }
    }


    /**
     * generates a predefined graph
     * */
    public static Graph<Integer, DefaultEdge> generateGraph() {
        Graph<Integer, DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class);
        for (int counter = 0; counter < DEFAULT_NUMBER_OF_NODES; counter++) {
            sampleGraph.addVertex(counter);
        }
        sampleGraph.addEdge(0, 1);
        for(int counter = 2 ; counter<DEFAULT_NUMBER_OF_NODES - 1 ; counter++){
            sampleGraph.addEdge(counter , counter+1) ;
        }
        /*
        for(DefaultEdge e: sampleGraph.edgeSet()){
            System.out.println(e);
        }
         */
        return sampleGraph;
    }





    // function to sort hashmap by values
    public static HashMap<Integer, Integer> sortByDegree(HashMap<Integer, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }










    public static HashMap<Integer, Integer> vertexAndLevelMap(Graph<Integer , DefaultEdge> graph,
                                                              Integer sourceVertex){
        int minVertex = Integer.MAX_VALUE ;
        int maxVertex = Integer.MIN_VALUE ;
        for(Integer i: graph.vertexSet()){
            if(i<minVertex){ minVertex = i ; }
            if(i>maxVertex){maxVertex = i ; }
        }

        HashMap<Integer , Integer> vertexLevels = new HashMap<>();
        for(int counter = minVertex ; counter<=maxVertex ; counter++){
            vertexLevels.put(counter , 0) ;
        }
        BreadthFirstIterator<Integer , DefaultEdge>
                bfsIterator = new BreadthFirstIterator<>(graph , sourceVertex);
        while(bfsIterator.hasNext()){
            Integer i = bfsIterator.next() ;
            vertexLevels.put(i , bfsIterator.getDepth(i)) ;
        }


        return vertexLevels ;

    }







    // only for undirected graphs
    public static HashMap<Integer , Integer> getVertexAndDegreeMap(Graph<Integer , DefaultEdge> graph){
        Set<Integer> vertexList = graph.vertexSet() ;
        int minVertex= Collections.min(vertexList) ;
        int maxVertex = Collections.max(vertexList) ;

        // initialise the map
        HashMap<Integer , Integer> vertexDegreeMap = new HashMap<>() ;
        for(int counter = minVertex ; counter<=maxVertex ; counter++){
            vertexDegreeMap.put(counter , 0)  ;
        }

        // calculate de
        for(int counter = minVertex ; counter<maxVertex ; counter++){
            for(int counter2 =counter ; counter2<=maxVertex ;  counter2++){
                if(graph.containsEdge(counter , counter2)){

                    /*
                     * because the graph looks at only one vertex for every edge visited , more efficient,  add
                     *  degree to source and destination
                     * */
                    vertexDegreeMap.put(counter , vertexDegreeMap.get(counter) + 1) ;
                    vertexDegreeMap.put(counter2 , vertexDegreeMap.get(counter2) + 1) ;
                }
            }
        }
        return vertexDegreeMap ;
    }









    public static ArrayList<Integer> getConnectedVertices(Graph<Integer, DefaultEdge> graph, int desiredNumberOfConnected){
        Boolean[] visited = new Boolean[graph.vertexSet().size()] ;
        HashMap<Integer , Integer> vertexDegreeMap = SolutionHeuristics.getVertexAndDegreeMap(graph) ;
        ArrayList<Integer> toBeReturned = new ArrayList<>() ;
        //choose a random vertex
        int maximumDegreeNeighbour  = (Integer) graph.vertexSet().toArray()[(int)(Math.random()*graph.vertexSet().size())];
        for(Boolean b:visited){b  = false ; }
        for(int vertices:vertexDegreeMap.keySet()){
            if(vertexDegreeMap.get(vertices)>maximumDegreeNeighbour){
                maximumDegreeNeighbour =vertices ;
            }
        }

        return getConnectedVerticesUtil(graph ,
                maximumDegreeNeighbour ,
                desiredNumberOfConnected,
                vertexDegreeMap ,
                0 ,
                toBeReturned ,
                visited
        ) ;



    }








    public static ArrayList<Integer> getConnectedVerticesUtil(Graph<Integer, DefaultEdge> graph,
                                                              int source,
                                                              int desiredNumberOfConnected,
                                                              HashMap<Integer, Integer> vertexDegreeMap,
                                                              int currentConnected,
                                                              ArrayList<Integer> allVerticesToBeReturned,
                                                              Boolean[] visited){
        if(currentConnected < desiredNumberOfConnected){
            visited[source] = true ;
            allVerticesToBeReturned.add(source) ;
            Set<Integer> set = Graphs.neighborSetOf(graph , source) ;

            //choose a random vertex
            int maximumDegreeNeighbour  = (Integer) set.toArray()[(int)Math.random()*set.size()];

            for(Integer neighbours : set){
                if(vertexDegreeMap.get(neighbours) > vertexDegreeMap.get(maximumDegreeNeighbour)
                        && !visited[neighbours]){
                    maximumDegreeNeighbour = neighbours ;
                }

                return getConnectedVerticesUtil(graph ,
                        maximumDegreeNeighbour ,
                        desiredNumberOfConnected ,
                        vertexDegreeMap ,
                        currentConnected + 1 ,
                        allVerticesToBeReturned ,
                        visited);

            }
        }

        return allVerticesToBeReturned;

    }




}
