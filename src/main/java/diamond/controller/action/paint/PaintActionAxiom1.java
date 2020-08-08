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
import diamond.view.ui.screen.draw.CyborgDrawer;

/**
 * @author Kei Morisue
 *
 */
public class PaintActionAxiom1 extends AbstractPaintActionMouse {
    private Context context;

    public PaintActionAxiom1(Context context) {
        this.context = context;
        initialize(
                new PickCyborg<Vertex>(context, Vertex.class),
                new PickCyborg<Vertex>(context, Vertex.class),
                new AddSegment(context));
    }

    @Override
    public void onLeftCtrlClick() {
    }

    @Override
    public void onDraw(Graphics2D g2d) {
        CyborgDrawer drawer = new CyborgDrawer(g2d, context);
        drawer.draw(g2d, context.getPointer().get(Vertex.class));
        drawer.draw(g2d, context.getPicker().get(Vertex.class));
    }
}
