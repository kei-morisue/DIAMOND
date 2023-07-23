/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class Tabs extends JPanel implements KeyListener {
	private ButtonGroup buttons = new ButtonGroup();
	private CardLayout card = new CardLayout();
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel(Labels.get("tabs_help"));

	public Tabs(Context context) {
		panel.setFocusable(true);
		panel.addKeyListener(this);
		panel.setLayout(new GridLayout(2, 1));

		setLayout(new BorderLayout());
		// add(label, BorderLayout.SOUTH);

		JPanel subPanel1 = new JPanel();
		JPanel subPanel2 = new JPanel();
		subPanel1.setLayout(new GridLayout(3, 1));
		subPanel2.setLayout(new GridLayout(1, 1));

		subPanel1.add(new PaintColorPanel(context));
		subPanel1.add(new TabAlterLineType(context, buttons));
		subPanel1.add(new PaintPatternPanel(context, buttons));
		// subPanel2.add(new TabManageFaces(context, buttons));
		subPanel2.add(new TabPaintSymbols(context, buttons));

		panel.add(subPanel1);
		panel.add(subPanel2);
		add(panel, BorderLayout.CENTER);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		if (e.isControlDown()) {
//			return;
//		}
//		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S
//				|| e.getKeyCode() == KeyEvent.VK_D) {
//			this.card.next(panel);
//			return;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
