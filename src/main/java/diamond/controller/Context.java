/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.util.Observable;
import java.util.Observer;

import diamond.controller.action.paint.AbstractPaintAction;
import diamond.controller.action.paint.PaintAxiom1;
import diamond.controller.action.paint.PaintLazy;
import diamond.controller.mouse.Picker;
import diamond.controller.mouse.Pointer;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.Graphics;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.PointerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentType;

/**
 * @author Kei Morisue
 *
 */
public class Context extends Observable implements Observer {
    private Diagram diagram;
    private AbstractPaintAction paintAction = new PaintLazy();
    private Vertex mouseLocation;
    private SegmentType type = SegmentType.CREASE_MOUNTAIN;
    private Picker picker = new Picker();
    private Pointer pointer = new Pointer();

    @Deprecated
    public Context() {
    }

    public Context(Diagram diagram) {
        this.diagram = diagram;
        this.diagram.addObserver(this);
        this.pointer.addObserver(this);
        this.paintAction = new PaintAxiom1(this);
        this.paintAction.addObserver(this);
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
        setChanged();
        notifyObservers();
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

    public AbstractPaintAction getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(AbstractPaintAction paintAction) {
        paintAction.addObserver(this);
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

    public <T extends Cyborg & Graphics> PickerCyborg<T> getPicker(
            Class<T> type) {
        return picker.get(type);
    }

    public Pointer getPointer() {
        return pointer;
    }

    public <T extends Cyborg & Graphics> PointerCyborg<T> getPointer(
            Class<T> type) {
        return pointer.get(type);
    }

}
