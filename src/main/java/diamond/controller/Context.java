/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Observable;
import java.util.Observer;

import diamond.controller.action.paint.Lazy;
import diamond.controller.action.paint.PaintActionInterface;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d1.SegmentType;

/**
 * @author Kei Morisue
 *
 */
public class Context extends Observable implements Observer {
    private Diagram diagram;
    private PaintActionInterface paintAction = new Lazy();//TODO
    private SegmentType type;

    @Deprecated
    public Context() {
    }

    public Context(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public void initialize() {

    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        this.type = type;
    }

    public PaintActionInterface getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(PaintActionInterface paintAction) {
        this.paintAction = paintAction;
    }

}
