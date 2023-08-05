/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.action.paint.PaintAction;
import diamond.controller.action.paint.state.distort.State0;
import diamond.controller.action.screen.PaintScreenAction;
import diamond.model.fold.Cp;
import diamond.model.fold.CpHistory;
import diamond.model.fold.CpSave;
import diamond.model.fold.Diagram;
import diamond.view.draw.CpDrawer;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreen extends ScreenBase {
	private Diagram diagram;
	private ModelScreen modelScreen;
	private CpHistory history = new CpHistory();
	private DrawerBase drawer = new CpDrawer();

	private PaintAction paintAction = new PaintAction(new State0());

	public PaintScreen(Diagram diagram, ModelScreen modelScreen) {
		this.diagram = diagram;
		this.modelScreen = modelScreen;
		modelScreen.link(this);
		PaintScreenAction paintScreenAction = new PaintScreenAction(this);
		addMouseMotionListener(paintScreenAction);
		addMouseListener(paintScreenAction);
		addMouseWheelListener(paintScreenAction);
	}

	@Override
	public void drawComponents(
			Graphics2D g2d) {
		drawer.draw(g2d, diagram.getCp());
		paintAction.onDraw(g2d, this);
		modelScreen.repaint();
	}

	public PaintAction getPaintAction() {
		return paintAction;
	}

	public void setPaintAction(
			PaintAction paintAction) {
		this.paintAction.onRefresh(this);
		this.paintAction = paintAction;
		this.paintAction.onRefresh(this);
	}

	public ModelScreen getModelScreen() {
		return modelScreen;
	}

	public void reset() {
		history.reset();
	}

	private void restore(
			CpSave save) {
		diagram.getCp().restore(save);
	}

	public boolean undo() {
		restore(history.undo());
		paintAction.onRefresh(this);
		return history.canUndo();
	}

	public void redo() {
		restore(history.redo());
		paintAction.onRefresh(this);
	}

	public void save() {
		CpSave save = new CpSave();
		getCp().save(save);
		history.record(save);
	}

	public Cp getCp() {
		return diagram.getCp();
	}

	public int getStepNo() {
		return diagram.getStepNo();
	}
}
