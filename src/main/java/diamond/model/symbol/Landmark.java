/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Vertex;
import diamond.view.ui.screen.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Landmark extends Symbol<Vertex> {
    private Vertex vertex;
    private final static Color COLOR_EDGE = Color.black;
    private final static Color COLOR_BODY = Color.white;
    public static final int SIZE_LANDMARK_BODY = 10;
    public static final int SIZE_LANDMARK_EDGE = 10;

    final public static BasicStroke STROKE_LANDMARK_BODY = new BasicStroke(
            2.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    final public static BasicStroke STROKE_LANDMARK_EDGE = new BasicStroke(
            6.0f,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    @Override
    public void draw(Graphics2D g2d) {
        double scale = G2DUtil.getScale(g2d);
        int size = (int) (SIZE_LANDMARK_EDGE / scale);
        int half = size >> 1;
        g2d.setColor(COLOR_EDGE);
        g2d.setStroke(STROKE_LANDMARK_EDGE);
        int x = (int) vertex.x;
        int y = (int) vertex.y;
        g2d.drawOval(x - half, y - half, SIZE_LANDMARK_EDGE,
                SIZE_LANDMARK_EDGE);
        g2d.setColor(COLOR_BODY);
        g2d.setStroke(STROKE_LANDMARK_BODY);
        size = (int) (SIZE_LANDMARK_BODY / scale);
        half = size >> 1;
        g2d.drawOval(x - half, y - half, size, size);
    }

    @Override
    public void set(Vertex cyborg) {
        this.vertex = cyborg;
    }

    @Override
    public void flip(Cp cp) {
        cp.getSymbolsVertex().remove(vertex);
    }

}
