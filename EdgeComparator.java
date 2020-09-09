import java.util.Comparator;
/**
 * enables priority queue by allowing comparisons 
 * @author sterw
 *
 */
public class EdgeComparator implements Comparator<Edge> {
	@Override
	public int compare(Edge arg0, Edge arg1) {
		if (arg0.getWeight() > arg1.getWeight()) {
			return 1;

		} else if (arg0.getWeight() < arg1.getWeight()) {
			return -1;
		}
		return 0;
	}

}
