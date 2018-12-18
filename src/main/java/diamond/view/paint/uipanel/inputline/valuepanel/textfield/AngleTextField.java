/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.inputline.valuepanel.textfield;

import diamond.paint.byvalue.AbstractValueInputListener;
import diamond.paint.byvalue.AngleValueInputListener;
import diamond.paint.byvalue.ValueDB;
import diamond.view.paint.uipanel.inputline.valuepanel.TextField;

/**
 * @author long_
 *
 */
public class AngleTextField extends TextField {

    @Override
    protected double getMeasuredValue() {
        return ValueDB.getInstance().getAngle();
    }

    @Override
    protected AbstractValueInputListener getValueInputListener() {
        return new AngleValueInputListener();
    }

}
