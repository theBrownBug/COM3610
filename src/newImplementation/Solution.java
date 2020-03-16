package newImplementation;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.BiconnectivityInspector;
import org.jgrapht.alg.interfaces.StrongConnectivityAlgorithm;
import org.jgrapht.alg.util.VertexDegreeComparator;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;

import java.io.*;
import java.net.*;
import java.util.*;
class Solution {
    private static int DEFAULT_NUMBER_OF_NODES = 6;


    /**
     * Look at methods to duplicate graphs ()
     *
     *  variables not getting updated
     *
     * */

    public static int computeSolution(Graph<Integer, DefaultEdge> graph, int c) {
        if (!isConnected(graph)) {
            int sum = 0;
            BiconnectivityInspector<Integer, DefaultEdge> inspector = new BiconnectivityInspector<>(graph);
            for (Graph<Integer, DefaultEdge> component : inspector.getConnectedComponents()) {
                if (component.vertexSet().size() > c) {
                    sum += computeSolution(component, c);
                }
            }
            return sum;
        } else {
            if (graph.vertexSet().size() <= c) {
                return 0;
            }
            int min = Integer.MAX_VALUE;

            // gets all the possible combination of vertices that get
            List<Set<Integer>> allPossibleSubsets = getSubsets(new ArrayList<>(graph.vertexSet()) , c+1) ;
            for(Set<Integer> set:allPossibleSubsets){
                List<Integer> list = new ArrayList<>(set);
                Graph<Integer , DefaultEdge> duplicate = createDuplicate(graph) ;
                Collections.sort(list, new VertexDegreeComparator(duplicate, VertexDegreeComparator.Order.DESCENDING));
                for(Integer i : list){
                    if(duplicate.removeVertex(i)){
                        if(computeSolution(duplicate , c)<min){
                            min = computeSolution(duplicate , c) ;
                        }
                    }
                }
            }
            /*
            List<Integer> list = new ArrayList<>(graph.vertexSet());
            Collections.sort(list, new VertexDegreeComparator(graph, VertexDegreeComparator.Order.DESCENDING));
            for (Integer i : list) {
                if (graph.removeVertex(i)) {
                    min += 1;
                    if (computeSolution(graph, c) < min) {
                        min += computeSolution(graph, c);
                    }
                }
            }
            */
            return min;

        }
    }


    public static Graph<Integer , DefaultEdge> createDuplicate(Graph<Integer , DefaultEdge> graph){
        Graph<Integer , DefaultEdge> duplicate  = new SimpleGraph<>(DefaultEdge.class);
        for(Integer i: graph.vertexSet()){
            duplicate.addVertex(i) ;
        }
        for(int i : graph.vertexSet()) {
            for (int counter = 1; counter <= graph.vertexSet().size(); counter++) {
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
        sampleGraph.addEdge(2, 3);
        sampleGraph.addEdge(3, 4);
        sampleGraph.addEdge(4, 5);
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

}
