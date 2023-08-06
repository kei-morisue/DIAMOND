/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import diamond.model.fold.CpHistory;
import diamond.model.fold.Diagram;
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
	private CpHistory history;

	public StepPanel(Diagram palette, ModelScreen modelScreen,
			CpHistory history) {
		this.history = history;
		setLayout(new BorderLayout());
		add(modelScreen, BorderLayout.CENTER);
		add(modelControl, BorderLayout.SOUTH);
		add(stepControl, BorderLayout.NORTH);

		buildStepControl(palette, modelScreen);
		buildModelControl(palette, modelScreen);

	}

	private void buildModelControl(
			Diagram diagram,
			ModelScreen modelScreen) {
		modelControl.setLayout(new GridLayout(1, 5));
		modelControl.add(new IconButton("insert.png", e -> {
			diagram.insert();
			history.reset();
			modelScreen.save();
			modelScreen.repaint();
		}));
		modelControl.add(new IconButton("destroy.png", e -> {
			if (JOptionPane.showConfirmDialog(null,
					Label.get("destroy_cp")) == 0
					&& diagram.size() != 1) {
				diagram.remove();
				history.reset();
				modelScreen.save();
			}
			modelScreen.repaint();
		}));
	}

	private void buildStepControl(
			Diagram diagram,
			ModelScreen modelScreen) {
		stepControl.setLayout(new GridLayout(1, 5));
		stepControl.add(new IconButton("left.png", e -> {
			diagram.prev();
			modelScreen.repaint();
			history.reset();
			modelScreen.save();
		}));
		stepControl.add(new IconButton("dleft.png", e -> {
			diagram.first();
			modelScreen.repaint();
			history.reset();
			modelScreen.save();
		}));
		stepControl.add(new IconButton("dright.png", e -> {
			diagram.last();
			modelScreen.repaint();
			history.reset();
			modelScreen.save();
		}));
		stepControl.add(new IconButton("right.png", e -> {
			diagram.next();
			modelScreen.repaint();
			history.reset();
			modelScreen.save();
		}));
	}
}
