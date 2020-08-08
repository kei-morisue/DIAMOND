/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Observable;
import java.util.Observer;

import diamond.controller.action.paint.Lazy;
import diamond.controller.action.paint.PaintActionInterface;
import diamond.controller.mouse.Picker;
import diamond.controller.mouse.Pointer;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentType;

/**
 * @author Kei Morisue
 *
 */
public class Context extends Observable implements Observer {
    private Diagram diagram;
    private PaintActionInterface paintAction = new Lazy();
    private Vertex mouseLocation;
    private SegmentType type;
    private Picker picker = new Picker();
    private Pointer pointer = new Pointer();

    @Deprecated
    public Context() {
    }

    public Context(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        this.pointer.addObserver(this);
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public void initialize() {
        picker.initialize();
        pointer.initialize();
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

    public Vertex getMouseLocation() {
        return mouseLocation;
    }

    public void setPointed(Vertex mouseLocation) {
        this.mouseLocation = mouseLocation;
        setChanged();
        notifyObservers();
    }

    public Picker getPicker() {
        return picker;
    }

    public Pointer getPointer() {
        return pointer;
    }

}
