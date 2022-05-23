////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

@SuppressWarnings("unused")
public class UserImpl implements User {
    private long id;
    private String surname;
    private String name;
    private LocalDate birthDate;
    private String email;
    private String address;

    protected boolean areArgumentsValid(long _id, 
    String s, String n, String e,String a,
    LocalDate date) throws DateTimeException, IllegalArgumentException {
        if(LocalDate.now().isAfter(date)
        && date.isAfter(
        LocalDate.of(1899, 12, 31))){
            if(!s.isEmpty() && !s.isBlank()){
                if(!n.isEmpty() && !n.isBlank()){
                    if(!e.isEmpty() && !e.isBlank()){
                        if(!a.isEmpty() && !a.isBlank()){
                            return true;
                        }
                    }
                }
            }
            throw new IllegalArgumentException("Invalid arguments");
        }
        throw new DateTimeException("Invalid Date");
    }

    public UserImpl(long _id, String s, String n, String e,String a,
        LocalDate date) throws DateTimeException, IllegalArgumentException {
        if (areArgumentsValid(_id, s, n, e, a, date)) {
            id = _id;
            surname = s;
            name = n;
            email = e;
            address = a;
            birthDate = date;
        }
    }

    @Override
    public long getId () {
        return id;
    }

    @Override
    public LocalDate getDate () {
        return birthDate;
    }
}
