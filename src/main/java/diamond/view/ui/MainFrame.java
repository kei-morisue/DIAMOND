/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

import diamond.model.Fold;
import diamond.view.draw.DrawerFlat;
import diamond.view.draw.DrawerFold;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class MainFrame extends JFrame {
	// TODO STUB
	private Fold fold = new Fold();
	private ScreenBase screen = new ScreenBase(new DrawerFlat(fold));
	public ScreenBase screen2 = new ScreenBase(new DrawerFold(fold));

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
