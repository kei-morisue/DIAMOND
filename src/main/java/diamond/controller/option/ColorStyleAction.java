/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.option;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

/**
 * @author long_
 *
 */
public abstract class ColorStyleAction<T extends Component>
        implements ActionListener {

    protected abstract String getTitle();

    protected abstract Color getColorStyle();

    protected abstract void setColorStyle(Color color);

    @Override
    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unchecked")
        T component = (T) (e.getSource());
        Color chosenColor = JColorChooser.showDialog(
                component,
                getTitle(), getColorStyle());
        if (chosenColor == null) {
            return;
        }
        setColorStyle(chosenColor);
        component.setBackground(chosenColor);
    }

}
