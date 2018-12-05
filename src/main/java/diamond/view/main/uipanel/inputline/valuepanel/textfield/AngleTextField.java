/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel.inputline.valuepanel.textfield;

import diamond.paint.byvalue.AbstractValueInputListener;
import diamond.paint.byvalue.AngleValueInputListener;
import diamond.paint.byvalue.ValueDB;
import diamond.view.main.uipanel.inputline.valuepanel.TextField;

/**
 * @author long_
 *
 */
public class AngleTextField extends TextField {

    protected double getMeasuredValue() {
        return ValueDB.getInstance().getAngle();
    }

    @Override
    protected AbstractValueInputListener getValueInputListener() {
        return new AngleValueInputListener();
    }

}
