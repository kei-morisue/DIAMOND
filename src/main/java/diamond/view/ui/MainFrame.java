/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import diamond.model.fold.Fold;
import diamond.view.draw.shape.ShapeProviderFlat;
import diamond.view.draw.shape.ShapeProviderFolded;
import diamond.view.ui.screen.ScreenBase;
import diamond.view.ui.screen.ScreenFold;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
	// TODO STUB
	private Fold fold = new Fold();

	private ScreenBase screen = new ScreenFold(fold, new ShapeProviderFlat());
	private ScreenBase screen2 = new ScreenFold(fold, new ShapeProviderFolded());

	public MainFrame() {
		setVisible(true);
		Icon.set(this, "./src/main/resources/icon/defox.png");
		setTitle(Label.get("main_frame_title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Container panel = getContentPane();
		panel.setLayout(new GridLayout(1, 2, 100, 0));
		panel.add(screen);
		panel.add(screen2);
	}

}
