package newImplementation.Test;
import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class GraphGenerator {

    public static Graph<Integer , DefaultEdge> randomGraphGenerator(int upperLimitVertices , int upperLimitEdges){
        int numberOfVertices = (int)Math.floor(Math.random()* upperLimitVertices) ;
        int numberOfEdges = (int)Math.floor(Math.random()*upperLimitEdges) ;
        GnmRandomGraphGenerator<Integer , DefaultEdge> randomGraphGenerator = new GnmRandomGraphGenerator<>(numberOfVertices , numberOfEdges) ;
        Graph<Integer , DefaultEdge> sample = new SimpleGraph<>(
                SupplierUtil.createIntegerSupplier() , SupplierUtil.createDefaultEdgeSupplier() , false) ;
        randomGraphGenerator.generateGraph(sample, null);
        return sample ;
    }
}