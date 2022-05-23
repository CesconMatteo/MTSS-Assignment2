////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ItemTypeTest {
    @Test
    public void toStringTest () {
        ItemType t = ItemType.Motherboard;
        assertEquals("Motherboard", t.toString());
    }
}
