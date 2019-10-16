/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.debug;

import java.awt.Graphics2D;
import java.util.Set;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.draw.StringDrawer;
import diamond.view.screen.draw.style.FontStyle;

/**
 * @author long_
 *
 */
public class Debugger {
    public static void debugPaintContext(Graphics2D g2d,
            PaintContext paintContext) {
        g2d.setFont(FontStyle.DEBUG);

        describe(g2d, paintContext.getPaintAction().getClass().getName(),
                "Paint Action", 10);
        OriModel oriModel = paintContext.palette.getOriModel();
        describe(g2d, oriModel.getFaces().size(), "Faces", 20);
        Set<OriVertex> vertices = oriModel.getVertices();
        describe(g2d, vertices.size(), "Vertices", 30);
        describe(g2d, paintContext.getPointedOriFace(), "OriFace", 50);
    }

    private static void describe(
            Graphics2D g2d,
            Object o,
            String s,
            int height) {
        if (o == null) {
            StringDrawer.describe(g2d, s + " : " + "null", 0, height);
        } else {
            StringDrawer.describe(g2d, s + " : " + o.toString(), 0, height);
        }
    }
}
