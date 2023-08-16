/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.model.fold.Diagram;
import diamond.view.ui.PreviewFrame;
import diamond.view.ui.panel.Preview;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class Run extends JMenu {

	private PaintScreen paintScreen;

	public Run(PaintScreen paintScreen) {
		super(Label.get("main_menu_run"));
		this.paintScreen = paintScreen;
		add(buildPreview());

	}

	private JMenuItem buildPreview() {
		JMenuItem item = new JMenuItem(Label.get("main_menu_run_preview"));
		item.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		item.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {
				Diagram diagram = paintScreen.getDiagram();
				Preview preview = new Preview(diagram);
				PreviewFrame frame
						= new PreviewFrame(
								paintScreen,
								preview);
				frame.setVisible(true);
			}
		});
		return item;
	}

}