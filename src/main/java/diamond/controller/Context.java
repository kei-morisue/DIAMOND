/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.step.Step;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;

/**
 * @author Kei Morisue
 *
 */
public class Context {
    private Diagram diagram;
    private int currentStep = 0;
    //private AbstractPaintAction paintAction;

    @Deprecated
    public Context() {
    }

    public Context(Diagram diagram) {
        this.diagram = diagram;
    }

    public Step getCurrentStep() {
        return diagram.getSteps().get(currentStep);
    }

    public StyleFace getStyleFace() {
        return diagram.getStyleFace();
    }

    public StyleSegment getStyleSegment() {
        return diagram.getStyleSegment();
    }
}
