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

import diamond.model.fold.CpHistory;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class Edit extends JMenu {
	private PaintScreen paintScreen;
	private CpHistory history;
	private JMenuItem undo = new JMenuItem(Label.get("main_menu_edit_undo"));
	private JMenuItem redo = new JMenuItem(Label.get("main_menu_edit_redo"));

	public Edit(PaintScreen paintScreen, CpHistory history) {
		super(Label.get("main_menu_edit"));
		this.paintScreen = paintScreen;
		this.history = history;
		buildUndo();
		buildRedo();
		add(undo);
		add(redo);
		history.addPropertyChangeListener(e -> setEnabled());
	}

	private void setEnabled() {
		undo.setEnabled(history.canUndo());
		redo.setEnabled(history.canRedo());
	}

	private void buildUndo() {
		undo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undo.addActionListener(new UndoRedoAction(false));
	}

	private void buildRedo() {
		redo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		redo.addActionListener(new UndoRedoAction(true));
	}

	private class UndoRedoAction implements ActionListener {
		private boolean isRedo;

		public UndoRedoAction(boolean isRedo) {
			super();
			this.isRedo = isRedo;
		}

		@Override
		public void actionPerformed(
				ActionEvent e) {
			if (isRedo) {
				paintScreen.redo();
			} else {
				paintScreen.undo();
			}
		}

	}
}