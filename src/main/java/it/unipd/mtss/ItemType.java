////////////////////////////////////////////////////////////////////
// LUCA BRUGNERA 2014722
// MATTEO CESCON 2009984
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;

public enum ItemType {
    Processor ("Processor"),
    Mouse ("Mouse"),
    Keyboard ("Keyboard"),
    Motherboard ("Motherboard");
    private final String textValue;

    ItemType (final String _textValue) {
        textValue = _textValue;
    }

    @Override
    public String toString () {
        return textValue;
    }
}
