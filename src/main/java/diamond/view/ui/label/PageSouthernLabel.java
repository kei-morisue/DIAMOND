/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.label;

import javax.swing.JLabel;

import diamond.view.screen.draw.style.color.OriFaceColor;

/**
 * @author long_
 *
 */
public class PageSouthernLabel extends JLabel {
    public PageSouthernLabel(int page) {
        super(String.valueOf(page), JLabel.CENTER);
        setBackground(OriFaceColor.ORI_FACE_FRONT);
        setOpaque(true);

    }
}
