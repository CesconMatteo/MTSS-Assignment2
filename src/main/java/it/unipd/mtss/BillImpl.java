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
        int procCount = 0, mouseCount = 0, keyboardCount = 0;
        if (itemsOrdered.size() < 1) {
            throw new IllegalArgumentException("Not less than 1 item!");
        }
        if (itemsOrdered.size() > 30) {
            throw new IllegalArgumentException("Not more than 30 items!");
        }
        for (EItem it : itemsOrdered) {
            sum += it.getPrice();
            if (it.getType() == ItemType.Processor) {
                procCount++;
            }
            if (it.getType() == ItemType.Mouse) {
                mouseCount++;
            }
        }
        sum = checkProcessor(sum, procCount, itemsOrdered);
        sum = checkMouse(sum, mouseCount, itemsOrdered);
        sum = checkMousesEqualsToKeyboards(sum, mouseCount, keyboardCount, itemsOrdered);

        if(sum > 1000){
         sum *= 0.9;
        }


        return sum;
    }

    protected static double checkProcessor (double sum, int procCount, List<EItem> itemsOrdered)
            throws IllegalArgumentException {
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

    protected static double checkMouse (double sum, int mouseCount, List<EItem> itemsOrdered)
            throws IllegalArgumentException {
        if (sum <= 0) {
            throw new IllegalArgumentException();
        }
        if (mouseCount > 10) {
            double lowest = Double.POSITIVE_INFINITY;
            for (EItem it2 : itemsOrdered) {
                if (it2.getPrice()<lowest && it2.getType()==ItemType.Mouse){
                    lowest = it2.getPrice();
                }
            }
            return sum - lowest;
        }
        return sum;
    }

    protected static double checkMousesEqualsToKeyboards (double sum, int mouseCount,
            int keyboardCount, List<EItem> itemsOrdered) throws IllegalArgumentException {
        if (sum <= 0) {
            throw new IllegalArgumentException();
        }
        if (mouseCount == keyboardCount && mouseCount > 0) {
            double lowest = Double.POSITIVE_INFINITY;
            for (EItem it2 : itemsOrdered) {
                if (it2.getPrice()<lowest) {
                    lowest = it2.getPrice();
                }
            }
            return sum - lowest;
        }
        return sum;
    }
}