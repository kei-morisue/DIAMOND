/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Kei Morisue
 *
 */
public class Util {
    public static void setBorder(JPanel panel, String label) {
        TitledBorder border = new TitledBorder(
                new LineBorder(Color.GRAY, 2),
                label,
                TitledBorder.LEFT,
                TitledBorder.TOP);
        panel.setBorder(border);
    }

}
