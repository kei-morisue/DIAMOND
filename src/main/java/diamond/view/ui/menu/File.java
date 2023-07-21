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

import diamond.controller.action.Palette;
import diamond.controller.action.io.ExportAction;
import diamond.controller.action.io.LoadAction;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class File extends JMenu {
	public File(Palette context) {
		super(Label.get("main_menu_file"));
		add(buildNew(context));
		add(buildOpen(context));
		add(buildSave(context));
	}

	private JMenuItem buildNew(Palette context) {
		JMenuItem item = new JMenuItem(Label.get("main_menu_file_new"));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		return item;
	}

	private JMenuItem buildOpen(Palette context) {
		JMenuItem item = new JMenuItem(Label.get("main_menu_file_open"));
		item.addActionListener(new LoadAction(context, this));
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		return item;
	}

	private JMenuItem buildSave(Palette context) {
		JMenuItem item = new JMenuItem(Label.get("main_menu_file_save"));
		item.addActionListener(new ExportAction(context, this));
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		return item;
	}
}