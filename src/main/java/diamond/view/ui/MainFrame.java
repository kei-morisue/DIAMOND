/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import diamond.controller.action.Palette;
import diamond.model.fold.Cp;
import diamond.view.draw.Drawer;
import diamond.view.draw.color.ColorProviderFlat;
import diamond.view.draw.shape.ShapeProviderFlat;
import diamond.view.ui.menu.File;
import diamond.view.ui.panel.Paint;
import diamond.view.ui.screen.ModelScreen;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
	// TODO STUB
	private Palette palette = new Palette();
	private Cp fold = new Cp(200.0);
	private ColorProviderFlat colorProvider1 = new ColorProviderFlat();
	private ShapeProviderFlat shapeProvider1 = new ShapeProviderFlat();
	private Drawer drawer1 = new Drawer(fold, colorProvider1, shapeProvider1);

	private ModelScreen screen1 = new ModelScreen(drawer1);

	private Paint paint = new Paint(palette);

	public MainFrame() {
		setVisible(true);
		Icon.set(this, "defox.png");
		setTitle(Label.get("main_frame_title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		buildMenu();

		buildPanel();
	}

	private void buildPanel() {
		Container panel = getContentPane();
		panel.setLayout(new GridLayout(1, 2));

		panel.add(paint);
		panel.add(screen1);
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new File(palette));
		setJMenuBar(menuBar);
	}

}
