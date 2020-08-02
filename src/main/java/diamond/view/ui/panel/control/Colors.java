/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d1.EdgeType;
import diamond.view.resource.string.Labels;
import diamond.view.ui.button.Color;
import diamond.view.ui.panel.Util;

/**
 * @author Kei Morisue
 *
 */
public class Colors extends JPanel {
    private ButtonGroup buttonGroup = new ButtonGroup();

    public Colors(Context context) {
        setLayout(new GridLayout(3, 1));
        add(new Color(
                EdgeType.CREASE_VALLEY,
                context,
                buttonGroup));
        add(new Color(
                EdgeType.CREASE_MOUNTAIN,
                context,
                buttonGroup));
        add(new Color(
                EdgeType.CREASE,
                context,
                buttonGroup));
        Util.setBorder(this, Labels.get("input_color"));
    }
}
