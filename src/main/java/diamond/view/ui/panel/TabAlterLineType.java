/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.controller.Context;
import diamond.controller.action.FlipHalfEdgeAction;
import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class TabAlterLineType extends JPanel {
    public TabAlterLineType(Context context, ButtonGroup buttonGroup) {
        PaintActionButton buttonFlip = new PaintActionButton(context,
                new FlipHalfEdgeAction());
        add(buttonFlip);
        buttonGroup.add(buttonFlip);
        buttonFlip.setIcons("flip");
    }
}
