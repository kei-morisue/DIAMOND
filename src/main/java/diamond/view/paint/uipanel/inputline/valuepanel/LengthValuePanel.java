/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.uipanel.inputline.valuepanel;

import diamond.paint.byvalue.LengthMeasuringAction;
import diamond.paint.core.GraphicMouseAction;
import diamond.resource.string.StringKey.LABEL;
import diamond.view.paint.uipanel.inputline.valuepanel.textfield.LengthTextField;

/**
 * @author long_
 *
 */
public class LengthValuePanel extends ValuePanel {

    @Override
    protected TextField createTextField() {
        return new LengthTextField();
    }

    @Override
    protected GraphicMouseAction createMeasuringActionListner() {
        return new LengthMeasuringAction();
    }

    @Override
    protected LABEL getLabelKey() {
        return LABEL.LENGTH;
    }

}
