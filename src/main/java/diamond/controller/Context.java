/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Observable;
import java.util.Observer;

import diamond.controller.action.paint.AbstractPaintAction;
import diamond.controller.action.paint.PaintLazy;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.diagram.step.Step;

/**
 * @author Kei Morisue
 *
 */
public class Context extends Observable implements Observer {
    private Diagram diagram;
    private AbstractPaintAction paintAction = new PaintLazy();

    @Deprecated
    public Context() {
    }

    public Context(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        this.paintAction.addObserver(this);
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        for (Step step : diagram.getSteps()) {
            //            step.update();
        }
        initialize();
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

    public AbstractPaintAction getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(AbstractPaintAction paintAction) {
        paintAction.addObserver(this);
        this.paintAction = paintAction;
    }

}
