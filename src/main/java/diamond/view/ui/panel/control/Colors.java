/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel.control;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class Colors extends JPanel {
    private ButtonGroup buttonGroup = new ButtonGroup();

    public Colors(Context context) {
        //        setLayout(new GridLayout(3, 1));
        //        add(new Color(
        //                SegmentType.CREASE_VALLEY,
        //                context,
        //                buttonGroup));
        //        add(new Color(
        //                SegmentType.CREASE_MOUNTAIN,
        //                context,
        //                buttonGroup));
        //        add(new Color(
        //                SegmentType.CREASE,
        //                context,
        //                buttonGroup));
        //        Util.setBorder(this, Labels.get("input_color"));
    }
}
