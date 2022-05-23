package it.unipd.mtss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
    Bill o;

    @Before
    public void setup () {
        o = new BillImpl();
    }

    @Test
    public void GetOrderPrice_ExpectedResults () {
        List<EItem> itemList = new ArrayList<EItem>();
        itemList.addAll(Collections.nCopies(3, new EItemImpl("1", 10, ItemType.Keyboard)));
        itemList.addAll(Collections.nCopies(7, new EItemImpl("1", 5, ItemType.Mouse)));
        itemList.addAll(Collections.nCopies(5, new EItemImpl("1", 50, ItemType.Processor)));
        double sum = o.getOrderPrice(itemList, new UserImpl(1, "s", "n", "e", "a", LocalDate.of(2000, 3, 3)));
        assertEquals(315, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void GetOrderPrice_WithZeroItems () {
        List<EItem> itemList = new ArrayList<EItem>();
        o.getOrderPrice(itemList, new UserImpl(1, "s", "n", "f", " f", LocalDate.of(2000, 3, 3)));
        fail();
    }
    
    @Test
    public void CheckProcessor_ZeroProcessors () {
        double sum = BillImpl.checkProcessor(30.0,0, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }
    
    @Test
    public void CheckProcessor_LessThenZeroProcessor () {
        double sum = BillImpl.checkProcessor(30.0,-4, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckProcessor_LessThenFiveProcessor () {
        EItem e  = new EItemImpl("1", 10, ItemType.Processor);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(3, e));
        double sum = BillImpl.checkProcessor(30.0,2,itemList);
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckProcessor_MoreThenFiveProcessor () {
        int counter = 10;
        EItem e  = new EItemImpl("1", 10, ItemType.Processor);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(counter, e));
        double sum = BillImpl.checkProcessor(100.0, counter,itemList);
        assertEquals(95, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckProcessor_NegativeSum () {
        BillImpl.checkProcessor(-45.0, 10, new ArrayList<EItem>());
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckProcessor_SumEqualToZero () {
        BillImpl.checkProcessor(0.0, 10, new ArrayList<EItem>());
        fail();
    }
    
        @Test
    public void CheckMouse_ZeroMouses () {
        double sum = BillImpl.checkMouse(30.0,0, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }
    
    @Test
    public void CheckMouse_LessThenZeroMouse () {
        double sum = BillImpl.checkMouse(30.0,-4, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckMouse_LessThenTenMouses () {
        EItem e  = new EItemImpl("1", 10, ItemType.Mouse);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(6, e));
        double sum = BillImpl.checkMouse(30.0, 2, itemList);
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckMouse_MoreThenTenMouses () {
        int counter = 11;
        EItem e  = new EItemImpl("1", 10, ItemType.Mouse);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(counter, e));
        double sum = BillImpl.checkMouse(110.0, counter, itemList);
        assertEquals(100, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckMouse_NegativeSum () {
        BillImpl.checkMouse(-45.0, 10, new ArrayList<EItem>());
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckMouse_SumEqualToZero () {
        BillImpl.checkMouse(0.0, 10, new ArrayList<EItem>());
        fail();
    }
}

