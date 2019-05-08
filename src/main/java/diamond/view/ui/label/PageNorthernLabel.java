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
public class PageNorthernLabel extends JLabel {
    private static final String label = "Powered by "
            + "DIAMOND "
            + "(c) Kei Morisue "
            + "2019";

    public PageNorthernLabel() {
        super(label, JLabel.CENTER);
        setBackground(Color.gray);
        setOpaque(true);
    }
}
