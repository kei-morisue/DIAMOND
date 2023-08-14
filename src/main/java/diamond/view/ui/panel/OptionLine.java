/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import diamond.controller.action.paint.state.angle.State1;
import diamond.view.draw.shape.Konst;
import diamond.view.util.Label;

/**
 * @author Kei Morisue
 *
 */
public class OptionLine extends JPanel {

	public OptionLine() {
		setLayout(new GridLayout(6, 1));

		try {
			addSpin("clipping_scale", Konst.class, "CLIP", 100, 50,
					90, 5);
			addSpin("curtosis_arrow", Konst.class, "CURTOSIS_ARROW_HEAD",
					20, 5,
					14, 1);
			addSpin("arrow_size", Konst.class, "SIZE_ARROW_HEAD",
					100, 10,
					60, 5);
			addSpin("arrow_body_width", Konst.class,
					"ARROW_WIDTH_PCT",
					600, 50,
					300, 50);
			addSpin("angle_grid", State1.class, "DELTA_NUM", 20, 2,
					8, 1);
		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void addSpin(
			String name,
			Class<?> c,
			String fieldName,
			int maximum,
			int minimum,
			int init,
			int step)
			throws NoSuchFieldException,
			SecurityException,
			IllegalArgumentException,
			IllegalAccessException {
		JPanel item0 = new JPanel();
		add(item0);
		JLabel label = new JLabel(Label.get(name));

		java.lang.reflect.Field field = c.getField(fieldName);
		int curr = (int) field.get(null);
		SpinnerNumberModel model
				= new SpinnerNumberModel(curr, minimum, maximum, step);

		item0.add(label);
		JSpinner spinner = new JSpinner(model);
		item0.add(spinner);
		JButton reset = new JButton(Label.get("style_reset"));
		item0.add(reset);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(
					ChangeEvent e) {
				try {
					field.set(null, spinner.getValue());
				} catch (IllegalArgumentException | IllegalAccessException
						| SecurityException e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {
				try {
					field.set(null, init);
				} catch (IllegalArgumentException | IllegalAccessException
						| SecurityException e1) {
					e1.printStackTrace();
				}
				spinner.setValue(init);
			}
		});
	}

}
