/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * @author Kei Morisue
 *
 */
public abstract class PreviewBase extends JPanel {
	protected int pageNo = 0;
	protected JPanel page;

	public void build() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.BLACK));
		setLayout(new BorderLayout());
		buildContent();
	}

	private void buildContent() {
		add(buildPage(), BorderLayout.CENTER);
		add(buildNorthernLabel(pageNo + 1), BorderLayout.NORTH);
		add(buildSouthernLabel(pageNo + 1), BorderLayout.SOUTH);
	}

	abstract protected JLabel buildNorthernLabel(
			int pageNo);

	abstract protected JLabel buildSouthernLabel(
			int pageNo);

	abstract protected JPanel buildPage();

	public void nextPage(
			int pages) {
		pageNo = Math.max(0, pageNo + pages);
		pageNo = Math.min(pageNo, maxPageIdx());
		removeAll();
		build();
		validate();
		repaint();
	}

	public void setToTop() {
		pageNo = 0;
		removeAll();
		build();
		validate();
		repaint();
	}

	abstract public int maxPageIdx();

}