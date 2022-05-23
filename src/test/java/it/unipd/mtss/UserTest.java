package it.unipd.mtss;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.Test;

public class UserTest {

    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_SurnameNull () {
        new UserImpl(1,"","n","e","a",LocalDate.of(1984, 3, 2));
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_SurnameBlank () {
        new UserImpl(1,"  ","n","e","a",LocalDate.of(1984, 3, 2));
        fail();
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_NameNull () {
        new UserImpl(1,"s","","e","a",LocalDate.of(1984, 3, 2));
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_NameBlank () {
        new UserImpl(1,"s","  ","e","a",LocalDate.of(1984, 3, 2));
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_EmailNull () {
        new UserImpl(1,"s","n","","a",LocalDate.of(1984, 3, 2));
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_EmailBlank () {
        new UserImpl(1,"s","n","  ","a",LocalDate.of(1984, 3, 2));
        fail();
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_AddressNull () {
        new UserImpl(1,"s","n","e","",LocalDate.of(1984, 3, 2));
        fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void CostructWith_AddressBlank () { 
        new UserImpl(1,"s","n","e","  ",LocalDate.of(1984, 3, 2));
        fail();
    }

    @Test (expected = DateTimeException.class)
    public void CostructWith_BirthDayOutOfSuperiorBound() {
        new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1971, 4, 32));
        fail();
    }

    @Test (expected = DateTimeException.class)
    public void ConstructWith_BirthDayOutOfInferiorBound () {
        new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1971, 4, -1));
        fail();
    }

    @Test (expected = DateTimeException.class)
    public void ConstructWith_BirthMonthOutOfSuperiorBound () {
        new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1971, 13, 15));
        fail();
    }

    @Test (expected = DateTimeException.class)
    public void ConstructWith_BirthMonthOutOfInferiorBound () {
        new UserImpl(1, "s", "n", "e", "a", LocalDate.of(1971, 0, 15));
        fail();
    }

    @Test (expected = DateTimeException.class)
    public void ConstructWith_BirthDateOutOfSuperiorBound () {
        new UserImpl(1, "a", "n", "e", "a", LocalDate.of(2100, 3, 5));
        fail();
    }

    @Test (expected = DateTimeException.class)
    public void ConstructWith_BirthDateOutOfInferiorBound () {
        new UserImpl(1, "a", "n", "e", "a", LocalDate.of(1800, 3, 5));
        fail();
    }

    @Test
    public void getDateTest () {
        User u = new UserImpl(2, "f", "n", "e", "a", LocalDate.of(1901, 12, 30));
        assertEquals(LocalDate.of(1901, 12, 30), u.getDate());
    }
}
