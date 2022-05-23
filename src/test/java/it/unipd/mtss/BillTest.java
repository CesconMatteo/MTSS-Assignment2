package it.unipd.mtss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
    BillImpl o;
    LocalDate m = LocalDate.of(2010, 3, 2);
    LocalDate a = LocalDate.now().minusYears(18);
    User minor = new UserImpl(1, "s", "n", "e", "a", m);
    User adult = new UserImpl(1, "s", "n", "e", "a", a);
    
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
        double sum = o.checkProcessor(30.0,0, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }
    
    @Test
    public void CheckProcessor_LessThenZeroProcessor () {
        double sum = o.checkProcessor(30.0,-4, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckProcessor_LessThenFiveProcessor () {
        EItem e  = new EItemImpl("1", 10, ItemType.Processor);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(3, e));
        double sum = o.checkProcessor(30.0,2,itemList);
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckProcessor_MoreThenFiveProcessor () {
        int counter = 10;
        EItem e  = new EItemImpl("1", 10, ItemType.Processor);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(counter, e));
        double sum = o.checkProcessor(100.0, counter,itemList);
        assertEquals(95, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckProcessor_NegativeSum () {
        o.checkProcessor(-45.0, 10, new ArrayList<EItem>());
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckProcessor_SumEqualToZero () {
        o.checkProcessor(0.0, 10, new ArrayList<EItem>());
        fail();
    }
    
    @Test
    public void CheckMouse_ZeroMouses () {
        double sum = o.checkMouse(30.0,0, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }
    
    @Test
    public void CheckMouse_LessThenZeroMouse () {
        double sum = o.checkMouse(30.0,-4, new ArrayList<EItem>());
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckMouse_LessThenTenMouses () {
        EItem e  = new EItemImpl("1", 10, ItemType.Mouse);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(6, e));
        double sum = o.checkMouse(30.0, 2, itemList);
        assertEquals(30, sum, 0.0);
    }

    @Test
    public void CheckMouse_MoreThenTenMouses () {
        int counter = 11;
        EItem e  = new EItemImpl("1", 10, ItemType.Mouse);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(counter, e));
        double sum = o.checkMouse(110.0, counter, itemList);
        assertEquals(100, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckMouse_NegativeSum () {
        o.checkMouse(-45.0, 10, new ArrayList<EItem>());
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckMouse_SumEqualToZero () {
        o.checkMouse(0.0, 10, new ArrayList<EItem>());
        fail();
    }

    @Test
    public void CheckMousesEqualsToKeyboards_ZeroMouses () {
        EItem e  = new EItemImpl("1", 10, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>();
        itemList.addAll(Collections.nCopies(3, e));
        double sum = o.checkMousesEqualsToKeyboards(30.0, 0, 3, itemList);
        assertEquals(30.0, sum, 0.0);
    }

    @Test
    public void CheckMousesEqualsToKeyboards_ZeroKeyboard () {
        EItem e  = new EItemImpl("1", 10, ItemType.Mouse);
        List<EItem> itemList = new ArrayList<EItem>();
        itemList.addAll(Collections.nCopies(3, e));
        double sum = o.checkMousesEqualsToKeyboards(30.0, 3, 0, itemList);
        assertEquals(30.0, sum, 0.0);
    }

    @Test
    public void CheckMouseKeyboard_ZeroMouseZeroKeyboard () {
        List<EItem> itemList = new ArrayList<EItem>();
        itemList.add(new EItemImpl("1", 20, ItemType.Motherboard));
        itemList.add(new EItemImpl("2", 10, ItemType.Motherboard));
        itemList.add(new EItemImpl("3", 30, ItemType.Processor));
        double sum = o.checkMousesEqualsToKeyboards(60, 0, 0, itemList);
        assertEquals(60, sum, 0.0);
    }

    @Test
    public void CheckMouseKeyboard_LessThanZeroMousesAndKeyboard () {
        double sum = o.checkMousesEqualsToKeyboards(30.0, -3, -3, new ArrayList<EItem>());
        assertEquals(30.0, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckMouseEqualsToKeyboard_NegativeSum () {
        o.checkMousesEqualsToKeyboards(-45.0, 10, 10, new ArrayList<EItem>());
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CheckMouseKeyboard_SumEqualToZero () {
        o.checkMousesEqualsToKeyboards(0.0, 10, 10, new ArrayList<EItem>());
        fail();
    }

    @Test
    public void CheckMouseKeyboard_ExpectedResults () {
        List<EItem> itemList = new ArrayList<EItem>();
        itemList.add(new EItemImpl("1", 20, ItemType.Mouse));
        itemList.add(new EItemImpl("2", 10, ItemType.Mouse));
        itemList.add(new EItemImpl("3", 30, ItemType.Keyboard));
        itemList.add(new EItemImpl("4", 50, ItemType.Keyboard));
        double sum = o.checkMousesEqualsToKeyboards(110, 2, 2, itemList);
        assertEquals(100, sum, 0.0);
    }

    @Test
    public void GetOrderPrice_GreaterThanOneThousand () {
        EItem e  = new EItemImpl("1", 550, ItemType.Processor);
        List<EItem> itemList = new ArrayList<EItem>();
        itemList.addAll(Collections.nCopies(2, e));
        double sum = o.getOrderPrice(itemList, new UserImpl
        (1, "s", "n", "f", " f", LocalDate.of(2000, 3, 3)));
        assertEquals(990, sum, 0.0);
    }

    @Test
    public void GetOrderPrice_LessThanOneThousand () {
        EItem e = new EItemImpl("n", 100, ItemType.Motherboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(10, e));
        double sum = o.getOrderPrice(itemList, new UserImpl(1, "s", "n", "e", "a", 
        LocalDate.of(2000, 3, 3)));
        assertEquals(1000.0, sum, 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void GetOrderPrice_MoreThanThirtyItems () {
        EItem e = new EItemImpl("1", 4, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(31, e));
        o.getOrderPrice(itemList, new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1993, 3, 8)));
    }

    @Test
    public void GetOrderPrice_PriceMinorThanTen () {
        EItem e = new EItemImpl("1", 4, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(2, e));
        double sum = o.getOrderPrice(itemList, new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1993, 3, 8)));
        assertEquals(10, sum, 0.0);
    }

    @Test
    public void GetOrderPrice_PriceGreaterThanTen () {
        EItem e = new EItemImpl("1", 4, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(3, e));
        double sum = o.getOrderPrice(itemList, new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1993, 3, 8)));
        assertEquals(12, sum, 0.0);
    }

    @Test
    public void CheckMinors_ExpectedResult () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 18);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.gifted.clear();
        BillImpl.bound = 1;
        assertTrue(o.checkMinors(minor));
    }

    @Test
    public void CheckMinors_AgeOverEighteen() {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 18);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.gifted.clear();
        BillImpl.bound = 1;
        assertFalse(o.checkMinors(adult));
    }

    @Test
    public void CheckMinors_TimeAfterNineteen () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 20);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.gifted.clear();
        BillImpl.bound = 1;
        assertFalse(o.checkMinors(minor));
    }

    @Test
    public void CheckMinors_TimeBeforeEighteen () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 15);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.gifted.clear();
        BillImpl.bound = 1;
        assertFalse(o.checkMinors(minor));
    }

    @Test
    public void CheckMinors_NotSorted () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 18);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.gifted.clear();
        BillImpl.bound = Integer.MAX_VALUE;
        assertFalse(o.checkMinors(minor));
    }

    @Test
    public void GetOrderPrice_AlreadyWon () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 18);
        BillImpl.now.set(Calendar.MINUTE, 30);
        EItem e = new EItemImpl("n", 10, ItemType.Motherboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(5, e));
        BillImpl.gifted.clear();
        BillImpl.gifted.add(Long.valueOf(1));
        BillImpl.bound = 1;
        assertEquals(50.0, o.getOrderPrice(itemList, minor), 0.0);
    }

    @Test
    public void CheckMinors_AlreadyWon () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 18);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.bound = 1;
        BillImpl.gifted.clear();
        BillImpl.gifted.add(minor.getId());
        assertFalse(o.checkMinors(minor));
    }

    @Test
    public void CheckMinors_NoGiftLeft () {
        BillImpl.now.set(Calendar.HOUR_OF_DAY, 18);
        BillImpl.now.set(Calendar.MINUTE, 30);
        BillImpl.bound = 1;
        BillImpl.gifted.clear();
        BillImpl.gifted.addAll(Collections.nCopies(10, Long.valueOf(98)));
        assertFalse(o.checkMinors(minor));
    }

    @Test
    public void GetOrderPrice_CheckMinorFalse () {
        Calendar now, eighteen, nineteen;
        now = Calendar.getInstance();
        eighteen = Calendar.getInstance();
        eighteen.set(Calendar.HOUR_OF_DAY, 18);
        eighteen.set(Calendar.MINUTE, 0);
        nineteen = Calendar.getInstance();
        nineteen.set(Calendar.HOUR_OF_DAY, 19);
        nineteen.set(Calendar.MINUTE, 0);
        assumeTrue(now.before(nineteen) && now.after(eighteen));
        EItem e = new EItemImpl("n", 10, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(3, e));
        BillImpl.bound = 1;
        BillImpl.gifted.clear();
        double sum = o.getOrderPrice(itemList, minor);
        assertEquals(0.0, sum, 0.0);
    }

    @Test
    public void CheckProcessor_WrongType () {
        EItem e  = new EItemImpl("1", 10, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(5, e));
        double sum = o.checkProcessor(30.0,7,itemList);
        assertEquals(30.0, sum, 0.0);
    }

    @Test
    public void CheckMouses_WrongType () {
        EItem e  = new EItemImpl("1", 10, ItemType.Keyboard);
        List<EItem> itemList = new ArrayList<EItem>(Collections.nCopies(5, e));
        double sum = o.checkMouse(50.0,12,itemList);
        assertEquals(50.0, sum, 0.0);
    }
}

