/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import diamond.model.fold.Diagram;
import diamond.view.ui.menu.Edit;
import diamond.view.ui.menu.File;
import diamond.view.ui.panel.PaintPanel;
import diamond.view.ui.panel.StepPanel;
import diamond.view.ui.screen.ModelScreen;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
	// TODO STUB
	private Diagram diagram = new Diagram(400.0);

	private ModelScreen modelScreen = new ModelScreen();

	private ButtonGroup paintButtons = new ButtonGroup();
	private PaintScreen paintScreen = new PaintScreen(diagram, modelScreen);
	private PaintPanel paintPanel
			= new PaintPanel(paintScreen, paintButtons);
	private StepPanel stepPanel = new StepPanel(diagram, modelScreen);

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

		panel.add(stepPanel);
		panel.add(paintPanel);
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new File(diagram));
		menuBar.add(new Edit(paintScreen));
		setJMenuBar(menuBar);
	}

}
