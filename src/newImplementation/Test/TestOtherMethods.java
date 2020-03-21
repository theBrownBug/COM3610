package newImplementation.Test;
import newImplementation.* ;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import static org.junit.Assert.assertEquals ;
import static org.junit.Assert.assertNotEquals;
public class TestOtherMethods {

    @Test

    /**
     *  graph
     *  0-1
     *  2-3-4-5-6-7
     *
     */
    public void testReturnConnectedVertices(){
        Graph<Integer, DefaultEdge> sample = new SimpleGraph<>(DefaultEdge.class) ;
        addVertex(sample , 8);
        sample.addEdge(0 , 1);
        for(int counter = 2 ; counter< sample.vertexSet().size() -1 ; counter++){
            sample.addEdge(counter , counter +1 );
        }



    }



    public void addVertex(Graph<Integer, DefaultEdge> graph , int limit){
        for(int counter =0 ; counter< limit ; counter++){
            graph.addVertex(counter) ;
        }
    }

}
