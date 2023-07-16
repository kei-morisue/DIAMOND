/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import diamond.model.fold.Fold;
import diamond.view.draw.Drawer;
import diamond.view.draw.color.ColorProviderFlat;
import diamond.view.draw.color.ColorProviderFolded;
import diamond.view.draw.shape.ShapeProviderFlat;
import diamond.view.draw.shape.ShapeProviderFolded;
import diamond.view.ui.screen.ScreenFold;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
	// TODO STUB
	private Fold fold = new Fold("test.cp");
//	private Fold fold = new Fold();
	private ColorProviderFlat colorProvider1 = new ColorProviderFlat();
	private ColorProviderFolded colorProvider2 = new ColorProviderFolded(fold);
	private ShapeProviderFlat shapeProvider1 = new ShapeProviderFlat();
	private ShapeProviderFolded shapeProvider2 = new ShapeProviderFolded(fold);
	private Drawer drawer1 = new Drawer(fold, colorProvider1, shapeProvider1);
	private Drawer drawer2 = new Drawer(fold, colorProvider2, shapeProvider2);

	private ScreenFold screen1 = new ScreenFold(drawer1);
	private ScreenFold screen2 = new ScreenFold(drawer2);

	public MainFrame() {
		setVisible(true);
		Icon.set(this, "./src/main/resources/icon/defox.png");
		setTitle(Label.get("main_frame_title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Container panel = getContentPane();
		panel.setLayout(new GridLayout(1, 2, 100, 0));

		screen2.link(screen1);
		panel.add(screen1);
		panel.add(screen2);
	}

}
