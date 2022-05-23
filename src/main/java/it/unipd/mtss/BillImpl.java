////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class BillImpl implements Bill {
    
    protected static int bound = 100;
    protected static List<Long> gifted = new ArrayList<Long>();
    protected static Calendar now = Calendar.getInstance(), eighteen, nineteen;

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
        
        if(sum < 10){
            sum += 2;
        }

        now = Calendar.getInstance();
        if (checkMinors(user)) {
            return 0.0;
        }

        return sum;
    }

    protected double checkProcessor (double sum, int procCount, List<EItem> itemsOrdered)
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
            if (lowest != Double.POSITIVE_INFINITY) {
                return sum - (lowest/2);
            }
        }
        return sum;
    }

    protected double checkMouse (double sum, int mouseCount, List<EItem> itemsOrdered)
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
            if (lowest != Double.POSITIVE_INFINITY) {
                return sum - lowest;
            }
        }
        return sum;
    }

    protected double checkMousesEqualsToKeyboards (double sum, int mouseCount,
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

    protected boolean checkMinors(User user) {
        Random rand = new Random();
        if (gifted.size() < 10) {
            if (!gifted.contains(user.getId())) {
                if (Period.between(user.getDate(), LocalDate.now()).getYears() < 18) {
                    if (rand.nextInt(bound) == rand.nextInt(bound)) {
                        eighteen = Calendar.getInstance();
                        eighteen.set(Calendar.HOUR_OF_DAY, 18);
                        eighteen.set(Calendar.MINUTE, 0);
                        nineteen = Calendar.getInstance();
                        nineteen.set(Calendar.HOUR_OF_DAY, 19);
                        nineteen.set(Calendar.MINUTE, 0);
                        if (now.before(nineteen) && now.after(eighteen)) {
                            gifted.add(user.getId());
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}