/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.main.uipanel.inputline.valuepanel;

import diamond.paint.byvalue.AngleMeasuringAction;
import diamond.paint.core.GraphicMouseAction;
import diamond.resource.StringID;
import diamond.view.main.uipanel.inputline.valuepanel.textfield.AngleTextField;

/**
 * @author long_
 *
 */
public class AngleValuePanel extends ValuePanel {

    @Override
    protected TextField createTextField() {
        return new AngleTextField();
    }

    @Override
    protected GraphicMouseAction createMeasuringActionListner() {
        return new AngleMeasuringAction();
    }

    @Override
    protected String createStringId() {
        return StringID.UI.ANGLE_ID;
    }

}
