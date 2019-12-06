/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import java.awt.geom.Point2D;
import java.util.Vector;

import diamond.controller.action.LazyPaintAction;
import diamond.controller.action.PaintActionInterface;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.CpBuilder;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.fold.Folder;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class Context {
    private Palette palette = new Palette();
    private int currentStep = 0;
    private PaintActionInterface paintAction = new LazyPaintAction();
    private EdgeType inputType = EdgeType.UNSETTLED_VALLEY;
    private Point2D.Double mousePoint = new Point2D.Double();
    private CyborgPicker picker = new CyborgPicker();
    private CyborgPointer pointer = new CyborgPointer();

    private PaintScreen paintScreen;
    private FoldedScreen foldedScreen;

    public void initialize() {
        picker.clear();
    }

    public Cp getCp() {
        Vector<Cp> cps = palette.getCps();
        while (currentStep >= cps.size()) {
            Cp lastCp = cps.get(cps.size() - 1);
            cps.add(CpBuilder.buildNext(lastCp));
        }
        return cps.get(currentStep);
    }

    public void fold() {
        Folder.fold(getCp());
    }

    public void insertCp() {
        Vector<Cp> cps = palette.getCps();
        cps.add(currentStep + 1, CpBuilder.buildNext(getCp()));
        currentStep += 1;
    }

    public void removeCp(Cp cp) {
        Vector<Cp> cps = palette.getCps();
        int index = cps.indexOf(cp);
        if (index == cps.size() - 1) {
            currentStep -= 1;
        }
        if (index != -1) {
            cps.remove(cp);
        }
    }

    public Palette getPalette() {
        return this.palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;
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

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        saveTransform();
        this.currentStep = currentStep;
        Cp cp = getCp();
        foldedScreen.setTransform(cp.getTransform());
    }

    public void saveTransform() {
        Cp cp = getCp();
        cp.setTransform(foldedScreen.getTransform());
    }
}
