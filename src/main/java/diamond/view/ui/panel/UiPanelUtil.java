/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import diamond.controller.paint.PaintContext;
import diamond.view.resource.string.StringKey.LABEL;
import diamond.view.ui.button.ArrowPaintButton;
import diamond.view.ui.button.LandmarkPaintButton;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author long_
 *
 */
public class UiPanelUtil {
    public static void setBorder(JPanel panel, String title) {
        TitledBorder border = new TitledBorder(
                new LineBorder(Color.GRAY, 2),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP);
        panel.setBorder(border);
    }

    public static void addPaintActionButton(
            JPanel parent,
            ButtonGroup buttonGroup,
            LABEL label,
            PaintContext context) {
        JRadioButton button = new PaintActionButton(label, context);
        parent.add(button);
        buttonGroup.add(button);
        if (label == LABEL.AXIOM1) {
            button.setSelected(true);
        }
    }

    public static void addArrowPaintButton(
            JPanel parent,
            ButtonGroup buttonGroup,
            LABEL label,
            PaintContext context) {
        JRadioButton button = new ArrowPaintButton(label, context);
        parent.add(button);
        buttonGroup.add(button);
    }

    public static void addLandmarkPaintButton(
            JPanel parent,
            ButtonGroup buttonGroup,
            LABEL label,
            PaintContext context) {
        JRadioButton button = new LandmarkPaintButton(label, context);
        parent.add(button);
        buttonGroup.add(button);
    }
}
