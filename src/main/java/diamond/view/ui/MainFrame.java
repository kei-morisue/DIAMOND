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
import diamond.view.ui.menu.File;
import diamond.view.ui.panel.PaintPanel;
import diamond.view.ui.screen.ModelScreen;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
	// TODO STUB
	private Palette palette = new Palette(200.0);

	private ModelScreen modelScreen = new ModelScreen();

	private PaintPanel paint = new PaintPanel(palette, modelScreen);

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
		panel.add(modelScreen);
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new File(palette));
		setJMenuBar(menuBar);
	}

}
