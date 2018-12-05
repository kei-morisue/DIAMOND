/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel.inputline.valuepanel;

import diamond.paint.byvalue.LengthMeasuringAction;
import diamond.paint.core.GraphicMouseAction;
import diamond.resource.StringID;
import diamond.view.main.uipanel.inputline.valuepanel.textfield.LengthTextField;

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
    protected String createStringId() {
        return StringID.UI.LENGTH_ID;
    }

}
