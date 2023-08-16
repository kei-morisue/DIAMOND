/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import diamond.model.fold.Cp;
import diamond.model.fold.Diagram;
import diamond.view.draw.shape.Konst;
import diamond.view.ui.screen.StepScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class Preview extends PreviewBase {
	private Diagram diagram;

	public Preview(Diagram diagram) {
		this.diagram = diagram;
	}

	@Override
	protected JLabel buildNorthernLabel(
			int pageNo) {
		JLabel label
				= new JLabel(Label.get("preview_frame_header"), JLabel.CENTER);
		label.setBackground(Konst.FACE_FRONT);
		label.setForeground(Konst.FACE_BACK);
		label.setOpaque(true);
		return label;
	}

	@Override
	protected JLabel buildSouthernLabel(
			int pageNo) {
		String text
				= String.valueOf(pageNo) + " / "
						+ String.valueOf(maxPageIdx() + 1);
		JLabel label = new JLabel(text, JLabel.CENTER);
		label.setBackground(Konst.FACE_FRONT);
		label.setForeground(Konst.FACE_BACK);
		label.setOpaque(true);
		return label;
	}

	@Override
	protected JPanel buildPage() {
		JPanel page = new JPanel();
		int r = Konst.PREV_ROW;
		int c = Konst.PREV_COL;
		int n = r * c;
		page.setLayout(new GridLayout(r, c));
		for (int i = 0; i < n; ++i) {
			int j = i + pageNo * n - 1;
			if (j < diagram.size()) {
				if (j == -1) {
					page.add(new StepScreen(diagram.getCp(diagram.size() - 1)));
					continue;
				}
				Cp cp = diagram.getCp(j);
				page.add(new StepScreen(cp, j));
			}
		}

		return page;
	}

	@Override
	public int maxPageIdx() {
		int steps = diagram.size();
		int n = Konst.PREV_ROW * Konst.PREV_COL;
		if (steps < n) {
			return 0;
		}
		int rest = steps - n + 1;
		return (rest / n) + ((rest % n == 0) ? 0 : 1);
	}
}
