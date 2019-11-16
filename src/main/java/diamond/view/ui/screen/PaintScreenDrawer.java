/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.model.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenDrawer {
    public static void draw(Graphics2D g2d, Cp cp) {
        for (Vertex v : cp.getVertices()) {
            draw(g2d, v);
        }
    }

    public static void draw(Graphics2D g2d, Vertex v) {

    }

    public static void draw(Graphics2D g2d, Face f) {

    }

    public static void draw(Graphics2D g2d, HalfEdge he) {

    }

}
