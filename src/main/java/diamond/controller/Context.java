/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Observable;
import java.util.Observer;

import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Context<T extends F<T>> extends Observable implements Observer {
    private Diagram<T> diagram;
    private int stepIndex = 0;
    //    private AbstractPaintAction paintAction = new PaintLazy();

    @Deprecated
    public Context() {
    }

    public Context(Diagram<T> diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        //        this.paintAction.addObserver(this);
    }

    public void next(int steps) {
        stepIndex = Math.min(
                Math.max(steps + stepIndex, 0),
                diagram.getSteps().size() - 1);
    }

    public Diagram<T> getDiagram() {
        return diagram;
    }

    public void initialize() {
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public Step<T> getStep() {
        return diagram.getStep(stepIndex);//TODO
    }

    public int getStepIdndex() {
        return stepIndex;
    }

}
