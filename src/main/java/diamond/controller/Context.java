/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.awt.geom.Point2D;

import diamond.controller.action.LazyPaintAction;
import diamond.controller.action.PaintActionInterface;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class Context {
    private Pallete pallete = new Pallete();
    private int currentStep = 0;
    private PaintActionInterface paintAction = new LazyPaintAction();
    private EdgeType inputType = EdgeType.UNSETTLED_VALLEY;
    private Point2D.Double mousePoint = new Point2D.Double();
    private CyborgPicker picker = new CyborgPicker();
    private CyborgPointer pointer = new CyborgPointer();

    private PaintScreen paintScreen;
    private FoldedScreen foldedScreen;

    public void initialize() {
        pointer.clear();
        picker.clear();
    }

    public Cp getCp() {
        return pallete.getCps().get(currentStep);
    }

    public Pallete getPallete() {
        return this.pallete;
    }

    public void setPallete(Pallete pallete) {
        this.pallete = pallete;
    }

    public PaintScreen getPaintScreen() {
        return this.paintScreen;
    }

    public void setPaintScreen(PaintScreen paintScreen) {
        this.paintScreen = paintScreen;
    }

    public FoldedScreen getFoldedScreen() {
        return this.foldedScreen;
    }

    public void setFoldedScreen(FoldedScreen foldedScreen) {
        this.foldedScreen = foldedScreen;
    }

    public PaintActionInterface getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(PaintActionInterface paintAction) {
        this.paintAction = paintAction;
    }

    public EdgeType getInputType() {
        return inputType;
    }

    public void setInputType(EdgeType inputType) {
        this.inputType = inputType;
    }

    public Point2D.Double getMousePoint() {
        return mousePoint;
    }

    public void setMousePoint(Point2D.Double mousePoint) {
        this.mousePoint = mousePoint;
    }

    public CyborgPicker getPicker() {
        return picker;
    }

    public CyborgPointer getPointer() {
        return pointer;
    }

    public void setPointer(CyborgPointer pointer) {
        this.pointer = pointer;
    }
}
