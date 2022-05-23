////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;
import java.util.List;

public class BillImpl implements Bill {
    
    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user)
            throws IllegalArgumentException {
        Double sum = 0.0;
        int procCount = 0;
        if (itemsOrdered.size() < 1) {
            throw new IllegalArgumentException("Not less than 1 item!");
        }
        for (EItem it : itemsOrdered) {
            sum += it.getPrice();
            if (it.getType() == ItemType.Processor) {
                procCount++;
            }
        }
        sum = checkProcessor(sum, procCount, itemsOrdered);
        return sum;
    }

    protected static double checkProcessor (double sum, int procCount,
            List<EItem> itemsOrdered) throws IllegalArgumentException {
        if (sum <= 0) {
            throw new IllegalArgumentException();
        }
        if (procCount > 5) {
            double lowest = Double.POSITIVE_INFINITY;
            for (EItem it2 : itemsOrdered) {
                if (it2.getPrice()<lowest && it2.getType()==ItemType.Processor){
                    lowest = it2.getPrice();
                }
            }
            return sum - (lowest/2);
        }
        return sum;
    }
}