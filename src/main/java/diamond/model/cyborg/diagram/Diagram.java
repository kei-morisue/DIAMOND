/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import java.util.LinkedList;
import java.util.Observable;

import diamond.model.cyborg.step.Step;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StylePage;
import diamond.model.cyborg.style.StyleSegment;
import diamond.model.math.Util;

/**
 * @author Kei Morisue
 *
 */
public class Diagram extends Observable {
    private LinkedList<Step> steps = new LinkedList<Step>();
    private int lastStep = 0;
    private StyleFace styleFace = new StyleFace();
    private StyleSegment styleSegment = new StyleSegment();
    private StylePage stylePage = new StylePage();
    private Stars stars = new Stars();

    public Diagram() {
    }

    public LinkedList<Step> getSteps() {
        return steps;
    }

    public void next(int steps) {
        lastStep = Util.window(
                lastStep + steps,
                0,
                this.steps.size() - 1);
        setChanged();
        notifyObservers();
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

    public int getLastStep() {
        return lastStep;
    }

    @Deprecated
    public void setLastStep(int lastStep) {
        this.lastStep = lastStep;
    }

    public Stars getStars() {
        return stars;
    }

    @Deprecated
    public void setStars(Stars stars) {
        this.stars = stars;
    }

    public StylePage getStylePage() {
        return stylePage;
    }

    @Deprecated
    public void setStylePage(StylePage stylePage) {
        this.stylePage = stylePage;
    }
}
