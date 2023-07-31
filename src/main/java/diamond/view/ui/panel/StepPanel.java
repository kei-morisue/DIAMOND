/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diamond.controller.action.Palette;
import diamond.view.button.IconButton;
import diamond.view.ui.screen.ModelScreen;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class StepPanel extends JPanel {
	private JPanel stepControl = new JPanel();
	private JPanel modelControl = new JPanel();

	public StepPanel(Palette palette, ModelScreen modelScreen) {
		setLayout(new BorderLayout());
		add(modelScreen, BorderLayout.CENTER);
		add(modelControl, BorderLayout.SOUTH);
		add(stepControl, BorderLayout.NORTH);

		buildStepControl(palette, modelScreen);
		buildModelControl(palette, modelScreen);

	}

	private void buildModelControl(
			Palette palette,
			ModelScreen modelScreen) {
		modelControl.setLayout(new GridLayout(1, 5));
		modelControl.add(new IconButton("insert.png", e -> {
			palette.insert();
			modelScreen.repaint();
		}));
		modelControl.add(new IconButton("destroy.png", e -> {
			if (JOptionPane.showConfirmDialog(null,
					Label.get("destroy_cp")) == 0
					&& palette.size() != 1) {
				palette.remove();
			}
			modelScreen.repaint();
		}));
	}

	private void buildStepControl(
			Palette palette,
			ModelScreen modelScreen) {
		stepControl.setLayout(new GridLayout(1, 5));
		stepControl.add(new IconButton("left.png", e -> {
			palette.prev();
			modelScreen.repaint();
		}));
		stepControl.add(new IconButton("dleft.png", e -> {
			palette.first();
			modelScreen.repaint();
		}));
		stepControl.add(new IconButton("dright.png", e -> {
			palette.last();
			modelScreen.repaint();
		}));
		stepControl.add(new IconButton("right.png", e -> {
			palette.next();
			modelScreen.repaint();
		}));
	}
}
