/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import diamond.view.draw.shape.Konst;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class OptionFace extends JPanel {
	private static final String faceFrontLabel = Label.get("face_front_color");
	private static final String faceBackLabel = Label.get("face_back_color");

	public OptionFace() {
		JButton faceFront = new JButton(faceFrontLabel);
		JButton faceBack = new JButton(faceBackLabel);
		faceFront.setBackground(Konst.FACE_FRONT);
		faceBack.setBackground(Konst.FACE_BACK);

		faceFront.setForeground(Color.BLACK);
		faceBack.setForeground(Color.BLACK);
		faceFront.addActionListener(new FaceColorAction(faceFront, true));
		faceBack.addActionListener(new FaceColorAction(faceBack, false));
		setLayout(new GridLayout(2, 1));
		add(faceFront);
		add(faceBack);
	}

	private class FaceColorAction implements ActionListener {
		private boolean isFront;
		private AbstractButton parent;

		public FaceColorAction(AbstractButton parent, boolean isFront) {
			this.isFront = isFront;
			this.parent = parent;
		}

		@Override
		public void actionPerformed(
				ActionEvent e) {
			String title;
			Color initialColor;
			if (isFront) {
				title = faceFrontLabel;
				initialColor = Konst.FACE_FRONT;
			} else {
				title = faceBackLabel;
				initialColor = Konst.FACE_BACK;
			}
			JColorChooser chooser = new JColorChooser(initialColor);
			JDialog dialog = JColorChooser.createDialog(parent, title, true,
					chooser, new OkListner(isFront, chooser),
					new CancelListner());
			dialog.setVisible(true);
		}

		private class OkListner implements ActionListener {
			boolean isFront;
			JColorChooser chooser;

			public OkListner(boolean isFront, JColorChooser chooser) {
				this.isFront = isFront;
				this.chooser = chooser;
			}

			@Override
			public void actionPerformed(
					ActionEvent e) {
				Color chosen = chooser.getColor();
				if (chosen == null) {
					return;
				}
				if (isFront) {
					Konst.FACE_FRONT = chosen;
				} else {
					Konst.FACE_BACK = chosen;
				}
				parent.setBackground(chosen);
			}
		}

		private class CancelListner implements ActionListener {
			@Override
			public void actionPerformed(
					ActionEvent e) {
			}
		}

	}
}