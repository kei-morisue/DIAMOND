/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.AddSegment;
import diamond.controller.action.state.PickCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.view.ui.screen.draw.VertexDrawer;

/**
 * @author Kei Morisue
 *
 */
public class PaintAxiom1 extends AbstractPaintActionMouse {
    private Context context;

    public PaintAxiom1(Context context) {
        this.context = context;
        initialize(
                new PickCyborg<Vertex>(context, Vertex.class),
                new PickCyborg<Vertex>(context, Vertex.class),
                new AddSegment(context));
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        VertexDrawer drawer = new VertexDrawer(g2d, context);
        drawer.draw(g2d, context.getPointer(Vertex.class));
        drawer.draw(g2d, context.getPicker(Vertex.class));
    }

    @Override
    protected void onLeftCtrl() {
    }

    @Override
    protected void onRightCtrl() {
    }
}
