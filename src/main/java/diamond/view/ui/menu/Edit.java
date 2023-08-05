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

import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class Edit extends JMenu {
	private PaintScreen paintScreen;

	public Edit(PaintScreen paintScreen) {
		super(Label.get("main_menu_edit"));
		this.paintScreen = paintScreen;
		add(buildUndo());
		add(buildRedo());
	}

	private JMenuItem buildUndo() {
		JMenuItem item = new JMenuItem(Label.get("main_menu_edit_undo"));
		item.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		item.addActionListener(new UndoAction(item));
		return item;
	}

	private JMenuItem buildRedo() {
		JMenuItem item = new JMenuItem(Label.get("main_menu_edit_redo"));
		item.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		return item;
	}

	private class UndoAction implements ActionListener {
		private JMenuItem item;

		public UndoAction(JMenuItem item) {
			super();
			this.item = item;
		}

		@Override
		public void actionPerformed(
				ActionEvent e) {
			boolean canUndo = paintScreen.undo();
			item.setEnabled(canUndo);
		}

	}
}