/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.controller.action.PaintActionInterface;
import diamond.model.Cp;
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
    private PaintActionInterface paintAction;
    private EdgeType inputType = EdgeType.UNSETTLED_VALLEY;
    private PaintScreen paintScreen;
    private FoldedScreen foldedScreen;

    public void initialize() {
        //TODO
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
}
