/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.ui.label;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * @author long_
 *
 */
public class PageSouthernLabel extends JLabel {
    public PageSouthernLabel(int page) {
        super(String.valueOf(page), JLabel.CENTER);
        setBackground(Color.gray);
        setOpaque(true);

    }
}
