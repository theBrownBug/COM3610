package newImplementation;

import org.jgrapht.*;
import org.jgrapht.graph.*;

import java.io.*;
import java.net.*;
import java.util.*;
class Solution {
    private static int DEFAULT_NUMBER_OF_NODES = 5 ;

    public static void main(String args[]) throws URISyntaxException {
        Graph<Integer , DefaultEdge> sampleGraph = new SimpleGraph<>(DefaultEdge.class) ;
        for(int counter = 0 ; counter< DEFAULT_NUMBER_OF_NODES ; counter++){
            sampleGraph.addVertex(counter) ;
        }
    }


}