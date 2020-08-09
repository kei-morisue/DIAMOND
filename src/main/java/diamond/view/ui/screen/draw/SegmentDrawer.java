/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.mouse.PickerCyborg;
import diamond.controller.mouse.PointerCyborg;
import diamond.model.cyborg.diagram.Diagram;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.style.StyleSegment;

/**
 * @author Kei Morisue
 *
 */
public class SegmentDrawer {
    private StyleSegment styleSegment;
    private float scale = 1.0f;

    public SegmentDrawer(Graphics2D g2d, Context context) {
        Diagram diagram = context.getDiagram();
        scale = (float) G2DUtil.getScale(g2d);
        this.styleSegment = diagram.getStyleSegment();
    }

    public void draw(Graphics2D g2d, PickerCyborg<AbstractSegment> segments) {
        g2d.setColor(StyleSegment.PICKED);
        for (AbstractSegment s0 : segments.get()) {
            draw(g2d, s0);
        }
    }

    public void draw(Graphics2D g2d, PointerCyborg<AbstractSegment> segments) {
        g2d.setColor(StyleSegment.POINTED);
        AbstractSegment s0 = segments.get();
        if (s0 == null) {
            return;
        }
        draw(g2d, s0);
    }

    public void draw(Graphics2D g2d, AbstractSegment segment) {
        g2d.setStroke(styleSegment.strokeCrease(scale, segment.getType()));
        g2d.draw(ShapeBuilder.build(segment));
    }
}
