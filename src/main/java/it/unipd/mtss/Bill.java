////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;
import java.util.List;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user);
}