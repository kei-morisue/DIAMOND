/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuBar;

import diamond.Config;
import diamond.view.ui.menu.FileDiagram;
import diamond.view.ui.menu.Option;
import diamond.view.ui.panel.PreviewBase;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Icon;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class PreviewFrame extends JDialog {
	Container contentPane = getContentPane();
	PreviewBase preview;
	PaintScreen paintScreen;

	public PreviewFrame(
			PaintScreen paintScreen,
			PreviewBase preview) {
		this.paintScreen = paintScreen;
		this.preview = preview;
		setTitle(Label.get("preview_frame_title"));
		setModal(true);
		setSize(new Dimension(Config.PREVIEW_FRAME_WIDTH,
				Config.PREVIEW_FRAME_HEIGHT));
		setLocationRelativeTo(null);

		buildContents();
		preview.build();
		buildMenu();
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new FileDiagram(paintScreen, preview));
		menuBar.add(new Option(paintScreen));
		setJMenuBar(menuBar);
	}

	private void buildContents() {
		contentPane.setLayout(new BorderLayout());
		contentPane.add(preview, BorderLayout.CENTER);
		contentPane.add(new PageSwitch(
				true),
				BorderLayout.EAST);
		contentPane.add(new PageSwitch(
				false),
				BorderLayout.WEST);
	}

	private class PageSwitch extends JButton {
		private int direction;

		public PageSwitch(boolean isNext) {
			setBackground(Color.white);
			setFocusable(false);
			this.direction = isNext ? 1 : -1;
			if (isNext) {
				Icon.set(this, "right.png");
			} else {
				Icon.set(this, "left.png");
			}
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(
						ActionEvent e) {
					preview.nextPage(direction);

				}
			});
		}
	}

}