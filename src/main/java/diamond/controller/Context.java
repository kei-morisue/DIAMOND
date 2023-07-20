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
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.fold.FaceOrderEstimator;
import diamond.model.cyborg.fold.Folder;
import diamond.model.cyborg.util.CpBuilder;
import diamond.view.ui.panel.East;
import diamond.view.ui.screen.AbstractScreen;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.ScreenTransform;

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

	private FoldedScreen foldedScreen;
	private East east;

	public void initialize() {
		picker.clear();
	}

	public Context() {
	}

	public Context(int i) {
		this.palette = new Palette(i);
	}

	public Cp getCp() {
		Vector<Cp> cps = palette.getCps();
		if (currentStep >= cps.size()) {
			return cps.lastElement();
		}
		return cps.get(currentStep);
	}

	public Cp getPrevCp() {
		Vector<Cp> cps = palette.getCps();
		return cps.get(currentStep - 1);
	}

	public void fold() {
		Cp cp = getCp();
		Folder.fold(cp);
		if (currentStep != 0) {
			Cp cp0 = getPrevCp();
			FaceOrderEstimator.infer(cp0, cp);
		}
	}

	public void insertCp() {
		saveTransform();
		Vector<Cp> cps = palette.getCps();
		cps.add(currentStep + 1, CpBuilder.buildNext(this, getCp()));
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

	public void repaint() {
		getPaintScreen().repaint();
		foldedScreen.repaint();
	}

	public Palette getPalette() {
		return this.palette;
	}

	@Deprecated
	public void setPalette(Palette palette) {
		this.palette = palette;
	}

	public AbstractScreen getPaintScreen() {
		return east.getPaintScreen();
	}

	public void setPaintScreen(String screenName) {
		east.setPaintScreen(screenName);
	}

	public void setEast(East east) {
		this.east = east;
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
		cp.setTransform(new ScreenTransform(foldedScreen.getTransform()));
	}
}
