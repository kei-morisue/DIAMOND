/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import diamond.view.ui.button.PaintActionButton;

/**
 * @author Kei Morisue
 *
 */
public class AbstractTab extends JPanel {
    protected void addButton(ButtonGroup buttonGroup,
            PaintActionButton buttonFlip, String iconBaseName) {
        add(buttonFlip);
        buttonGroup.add(buttonFlip);
        buttonFlip.setIcons(iconBaseName);
    }

}
