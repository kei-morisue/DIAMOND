/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import diamond.model.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.view.resource.size.Cyborg;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        for (Vertex v : cp.getVertices()) {
            draw(g2d, v);
        }
        for (HalfEdge he : cp.getHes()) {
            draw(g2d, he);
        }
        for (Face he : cp.getFaces()) {
            draw(g2d, he);
        }

    }

    public static void draw(Graphics2D g2d, Vertex v) {
        double size = Cyborg.VERTEX_SIZE / G2DUtil.getScale(g2d);
        g2d.setColor(diamond.view.resource.color.Vertex.NEUTRAL);
        g2d.fill(new Ellipse2D.Double(
                v.x - size * 0.5,
                v.y - size * 0.5,
                size,
                size));
    }

    public static void draw(Graphics2D g2d, Face f) {

    }

    public static void draw(Graphics2D g2d, HalfEdge he) {

    }

}
