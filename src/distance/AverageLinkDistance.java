package distance;
import clustering.Cluster;
import data.Data;
import data.Example;

public class AverageLinkDistance implements ClusterDistance {
    
    public double computeDistance(Cluster c1, Cluster c2, Data d) {
        double totalDistance = 0;
        int validComparisons = 0;
        
        for (Integer id1 : c1) {
            Example e1 = d.getExample(id1);
            
            for (Integer id2 : c2) {
                Example e2 = d.getExample(id2);
                try {
                    totalDistance += e1.distance(e2);
                    validComparisons++;
                } catch (data.InvalidSizeException e) {
                    System.err.println("Errore nel calcolo della distanza: " + e.getMessage());
                }
            }
        }
        
        if (validComparisons == 0) {
            return Double.MAX_VALUE;
        }
        
        return totalDistance / validComparisons;
    }
}
