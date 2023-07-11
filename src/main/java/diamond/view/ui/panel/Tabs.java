/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class Tabs extends JPanel implements KeyListener {
	private ButtonGroup buttons = new ButtonGroup();
	private CardLayout card = new CardLayout();
	private JPanel panel = new JPanel();

	public Tabs(Context context) {
		panel.setFocusable(true);
		panel.addKeyListener(this);
		panel.setLayout(card);

		JPanel subPanel1 = new JPanel();
		JPanel subPanel2 = new JPanel();
		subPanel1.setLayout(new GridLayout(3, 1));
		subPanel2.setLayout(new GridLayout(2, 1));

		subPanel1.add(new PaintPatternPanel(context, buttons));
		subPanel1.add(new TabAlterLineType(context, buttons));
		subPanel1.add(new PaintColorPanel(context));
		subPanel2.add(new TabManageFaces(context, buttons));
		subPanel2.add(new TabPaintSymbols(context, buttons));

		panel.add(subPanel1);
		panel.add(subPanel2);
		add(panel);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!e.isControlDown()) {
			return;
		}
		if (e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_LEFT
				&& e.getKeyCode() != KeyEvent.VK_RIGHT) {
			return;
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.card.next(panel);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.card.previous(panel);
		}
	}

	private JPanel visiblePanel() {
		JPanel tab = null;
		for (Component comp : panel.getComponents()) {
			if (comp.isVisible() == true) {
				tab = (JPanel) comp;
			}
		}
		return tab;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
