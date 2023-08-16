/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import diamond.controller.action.io.ExportImageAction;
import diamond.controller.action.io.ExportSvgAction;
import diamond.model.fold.Diagram;
import diamond.view.ui.panel.PreviewBase;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class FileDiagram extends JMenu {

	private PaintScreen paintScreen;
	private PreviewBase preview;

	public FileDiagram(
			PaintScreen paintScreen,
			PreviewBase preview) {
		super(Label.get("preview_menu_file"));
		this.paintScreen = paintScreen;
		this.preview = preview;
		add(buildSaveImage());
		add(buildSaveSvg());
	}

	private JMenuItem buildSaveImage() {
		JMenuItem item = new JMenuItem(Label.get("preview_menu_save_image"));
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		Diagram diagram = paintScreen.getDiagram();
		item.addActionListener(
				new ExportImageAction(diagram, item, preview));
		return item;
	}

	private JMenuItem buildSaveSvg() {
		JMenuItem item = new JMenuItem(Label.get("preview_menu_save_svg"));
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				ActionEvent.CTRL_MASK));
		Diagram diagram = paintScreen.getDiagram();
		item.addActionListener(
				new ExportSvgAction(
						diagram,
						item));
		return item;
	}

}