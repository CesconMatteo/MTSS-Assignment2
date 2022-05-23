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
        if (itemsOrdered.size() < 1) {
            throw new IllegalArgumentException("Not less than 1 item!");
        }
        for (EItem it : itemsOrdered) {
            sum += it.getPrice();
        }
        return sum;
    }
}