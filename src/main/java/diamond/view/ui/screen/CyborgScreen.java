/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.BasicStroke;

import javax.swing.JPanel;

/**
 * @author Kei Morisue
 *
 */
public abstract class CyborgScreen extends JPanel {
    final protected static int CAP = BasicStroke.CAP_BUTT;
    final protected static int JOIN = BasicStroke.JOIN_ROUND;

    public BasicStroke getLineStroke(float scale) {
        return new BasicStroke(0.0f / scale, CAP, JOIN);
    }

    public BasicStroke getEdgeStroke(float scale) {
        return new BasicStroke(3.0f / scale, CAP, JOIN);
    }

    public BasicStroke getSegStroke(float scale) {
        return new BasicStroke(0.0f / scale, CAP, JOIN);
    }

}
