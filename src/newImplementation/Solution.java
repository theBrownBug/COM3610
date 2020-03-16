package newImplementation;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.util.VertexDegreeComparator;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.*;
import java.net.*;
import java.util.*;
class Solution {
    private static int DEFAULT_NUMBER_OF_NODES = 8;


    /**
     * Look at methods to duplicate graphs ()
     *
     *
     *
     * */

    public static int computeSolution(Graph<Integer, DefaultEdge> graph, int c) {
        if (isConnected(graph)) {
            if (graph.vertexSet().size() <= c) {
                //return sum;
               return 0;
            }
            List<Integer> vertexList = returnConnectedVertices(graph , c +1) ;
            int min = Integer.MAX_VALUE;
            for(Integer i : vertexList){
                Graph<Integer, DefaultEdge> duplicate = createDuplicate(graph);
                if(duplicate.removeVertex(i)){
                    int sol = 1+ computeSolution(duplicate , c);
                    if(sol<min){
                        min = sol ;
                    }
                }
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
     * generates a predefined graph
     *
     *
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



    //===================================================================================================================================

    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current,List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx+1, current, solution);
        current.remove(x);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx+1, current, solution);
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new HashSet<Integer>(), res);
        return res;
    }



    //===================================================================================================================================






    public static void main(String args[]){

        Graph<Integer , DefaultEdge> sampleGraph = generateGraph();
        try {
            System.out.println(Solution.computeSolution(sampleGraph, 2));
        }catch (Exception e){
            System.out.println(e);
        }
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
                int counter= 0 ;
                List<Integer> toReturn = new ArrayList<>() ;
                Iterator<Integer> dfsIterator = new DepthFirstIterator<>(graph) ;
                while(dfsIterator.hasNext()){
                    Integer i = dfsIterator.next() ;
                    if(counter<c){
                        toReturn.add(i);
                        counter++ ;
                    }
                    else{
                        break ;
                    }
                }
                return (ArrayList<Integer>) toReturn ;
            }
        }else{
            BiconnectivityInspector<Integer , DefaultEdge> inspector = new BiconnectivityInspector(graph);
            for(Graph<Integer , DefaultEdge> g : inspector.getConnectedComponents()){
                if(g.vertexSet().size()>c){
                    int counter= 0 ;
                    List<Integer> toReturn = new ArrayList<>() ;
                    Iterator<Integer> dfsIterator = new DepthFirstIterator<>(graph) ;
                    while(dfsIterator.hasNext()){
                        Integer i = dfsIterator.next() ;
                        if(counter<c){
                            toReturn.add(i);
                            counter++;
                        }
                        else{
                            break ;
                        }
                    }
                    return (ArrayList<Integer>) toReturn;
                }
            }
        }
        return null ;
    }




}
