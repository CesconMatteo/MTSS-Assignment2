////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;

@SuppressWarnings("unused")
public class EItemImpl implements EItem {

    private String name;
    private double price;
    private ItemType type;

    public EItemImpl(String n, double p, ItemType t)
            throws IllegalArgumentException{
        if (n != "" && !n.isBlank() && p > 0) {
            name = n;
            price = p;
            type = t;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getPrice () {
        return price;
    }

    @Override
    public ItemType getType () {
        return type;
    }
}
