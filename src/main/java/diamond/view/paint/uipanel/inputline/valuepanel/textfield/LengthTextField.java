/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.inputline.valuepanel.textfield;

import diamond.paint.byvalue.AbstractValueInputListener;
import diamond.paint.byvalue.LengthValueInputListener;
import diamond.paint.byvalue.ValueDB;
import diamond.view.paint.uipanel.inputline.valuepanel.TextField;

/**
 * @author long_
 *
 */
public class LengthTextField extends TextField {

    protected double getMeasuredValue() {
        return ValueDB.getInstance().getLength();
    }

    @Override
    protected AbstractValueInputListener getValueInputListener() {
        return new LengthValueInputListener();
    }

}
