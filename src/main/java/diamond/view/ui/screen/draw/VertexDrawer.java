/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.mouse.PickerCyborg;
import diamond.controller.mouse.PointerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.style.StyleVertex;

/**
 * @author Kei Morisue
 *
 */
public class VertexDrawer {
    private float scale = 1.0f;

    public VertexDrawer(Graphics2D g2d, Context context) {
        scale = (float) G2DUtil.getScale(g2d);
    }

    public void draw(Graphics2D g2d, PickerCyborg<Vertex> vs) {
        g2d.setColor(StyleVertex.PICKED);
        for (Vertex v : vs.get()) {
            draw(g2d, v);
        }
    }

    public void draw(Graphics2D g2d, PointerCyborg<Vertex> v) {
        g2d.setColor(StyleVertex.PICKED);
        Vertex v0 = v.get();
        if (v0 == null) {
            return;
        }
        draw(g2d, v0);
    }

    public void draw(Graphics2D g2d, Vertex v) {
        g2d.fill(ShapeBuilder.build(v, StyleVertex.SIZE / scale));
    }

}
