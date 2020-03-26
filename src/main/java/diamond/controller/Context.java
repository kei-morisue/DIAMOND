/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.controller.action.AbstractPaintAction;
import diamond.model.cyborg.Diagram;
import diamond.model.cyborg.Step;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;

/**
 * @author Kei Morisue
 *
 */
public class Context {
    private Diagram diagram = new Diagram();
    private int currentStep = 0;
    private AbstractPaintAction paintAction;

    public Context() {
    }

    public Step getCurrentStep() {
        return diagram.getSteps().get(currentStep);
    }

    public StyleFace getStyleFace() {
        return diagram.getStyleFace();
    }

    public StyleSegment getSegmentSty() {
        return diagram.getStyleSegment();
    }
}
