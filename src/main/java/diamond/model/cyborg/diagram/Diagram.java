/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import java.util.LinkedList;

import diamond.model.cyborg.step.Step;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StyleSegment;

/**
 * @author Kei Morisue
 *
 */
public class Diagram {
    private LinkedList<Step> steps = new LinkedList<Step>();
    private int lastStep = 0;
    private StyleFace styleFace = new StyleFace();
    private StyleSegment styleSegment = new StyleSegment();

    public Diagram() {
    }

    public LinkedList<Step> getSteps() {
        return steps;
    }

    public void next(int steps) {
        lastStep = Math.max(
                0,
                Math.min(
                        lastStep + steps,
                        this.steps.size() - 1));
    }

    public Step getStep() {
        return steps.get(lastStep);
    }

    @Deprecated
    public void setSteps(LinkedList<Step> steps) {
        this.steps = steps;
    }

    public StyleFace getStyleFace() {
        return styleFace;
    }

    @Deprecated
    public void setStyleFace(StyleFace styleFace) {
        this.styleFace = styleFace;
    }

    public StyleSegment getStyleSegment() {
        return styleSegment;
    }

    @Deprecated
    public void setStyleSegment(StyleSegment styleSegment) {
        this.styleSegment = styleSegment;
    }

    @Deprecated
    public int getLastStep() {
        return lastStep;
    }

    @Deprecated
    public void setLastStep(int lastStep) {
        this.lastStep = lastStep;
    }
}
