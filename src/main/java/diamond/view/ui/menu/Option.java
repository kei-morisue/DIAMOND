/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import diamond.Config;
import diamond.view.ui.panel.OptionFace;
import diamond.view.ui.panel.OptionLine;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class Option extends JMenu {
	JMenuItem item = new JMenuItem(
			Label.get("main_menu_option_style"));

	public Option(PaintScreen paintScreen) {
		super(Label.get("main_menu_option"));
		add(item);
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(
					ActionEvent e) {
				JDialog dialog = new StyleDialog();
				dialog.setVisible(true);

			}
		});
	}

	private class StyleDialog extends JDialog {
		private JTabbedPane tabbedPane = new JTabbedPane();
		private JPanel line = new OptionLine();
		private JPanel face = new OptionFace();

		public StyleDialog() {
			super();
			setTitle(Label.get("style_title"));
			setModal(true);
			setSize(Config.STYLE_DIALOG_WIDTH, Config.STYLE_DIALOG_HEIGHT);
			setLocationRelativeTo(null);

			getContentPane().add(tabbedPane);
			tabbedPane.addTab(Label.get("style_line"), line);
			tabbedPane.addTab(Label.get("style_face"), face);
		}

	}
}
