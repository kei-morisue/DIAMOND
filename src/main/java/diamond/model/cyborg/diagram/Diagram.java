/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.diagram;

import java.util.LinkedList;
import java.util.Observable;

import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.diagram.step.StepBuilder;
import diamond.model.cyborg.style.StyleFace;
import diamond.model.cyborg.style.StylePage;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Diagram<T extends F<T>> extends Observable {
    private LinkedList<Step<T>> steps = new LinkedList<>();
    private StyleFace styleFace = new StyleFace();
    private StylePage stylePage = new StylePage();

    @Deprecated
    public Diagram() {
    }

    public Diagram(F<T> size) {
        steps.add(new StepBuilder().step0(size));
    }

    public LinkedList<Step<T>> getSteps() {
        return steps;
    }

    public Step<T> getStep(int i) {
        return steps.get(i);
    }

    @Deprecated
    public void setSteps(LinkedList<Step<T>> steps) {
        this.steps = steps;
    }

    public StyleFace getStyleFace() {
        return styleFace;
    }

    @Deprecated
    public void setStyleFace(StyleFace styleFace) {
        this.styleFace = styleFace;
    }

    public StylePage getStylePage() {
        return stylePage;
    }

    @Deprecated
    public void setStylePage(StylePage stylePage) {
        this.stylePage = stylePage;
    }
}
