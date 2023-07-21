/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;
import java.util.ArrayList;

import diamond.controller.action.screen.ScreenAction;
import diamond.model.XY;
import diamond.model.fold.Vertex;
import diamond.view.draw.Drawer;

/**
 * @author Kei Morisue
 *
 */
public class ModelScreen extends ScreenBase {

	private Drawer drawer;
	private ArrayList<ModelScreen> linkedScreens = new ArrayList<>();

	public ModelScreen(Drawer drawer) {
		super();
		this.drawer = drawer;
		setFocusable(true);
		ScreenAction action = new ScreenAction(this);
		addMouseMotionListener(action);
		addMouseListener(action);
	}

	@Override
	public void drawComponents(Graphics2D g2d) {
		drawer.draw(g2d);
	}

	public void link(ModelScreen linkedScreen) {
		this.linkedScreens.add(linkedScreen);
		linkedScreen.linkedScreens.add(this);
	}

	public Vertex pickVertex(XY v, double scale) {
		return drawer.pickVertex(v, scale);
	}

	public void clearPicked() {
		drawer.clearPicked();
	}

	public ArrayList<ModelScreen> getLinkedScreens() {
		return linkedScreens;
	}

}
