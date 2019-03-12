/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import diamond.controller.paint.state.SettleUnsettleLineTypeState;

/**
 * @author long_
 *
 */
public class SettleUnsettleLineAction extends AbstractLineTypeFlipAction {
    @Override
    protected void setPaintState() {
        setActionState(new SettleUnsettleLineTypeState());
    }
}
