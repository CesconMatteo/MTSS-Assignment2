package it.unipd.mtss;

import static org.junit.Assert.fail;

import org.junit.Test;

public class EItemTest {

    @Test (expected = IllegalArgumentException.class)
    public void Construct_ElementWithEmptyName () {
        new EItemImpl("", 2, ItemType.Keyboard);     
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void Construct_ElementWithWhiteSpacesName () {
        new EItemImpl("  ", 2, ItemType.Motherboard);
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void Construct_ElementWithNegativePrice () {
        new EItemImpl("a", -5, ItemType.Mouse);
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void Construct_ElementWithPriceEqualToZero () {
        new EItemImpl("a", 0, ItemType.Mouse);
        fail();
    }

}
