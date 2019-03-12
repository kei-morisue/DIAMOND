/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import diamond.controller.paint.state.FlipLineTypeState;

/**
 * @author long_
 *
 */
public class FlipLineTypeAction extends AbstractLineTypeFlipAction {
    protected void setPaintState() {
        setActionState(new FlipLineTypeState());
    }
}
