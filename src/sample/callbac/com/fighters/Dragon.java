package sample.callbac.com.fighters;

import javax.swing.text.html.parser.Element;

public class Dragon implements ElementPower {

    int elementMask;

    @Override
    public int getElementPower() {
        return elementMask;
    }
}
