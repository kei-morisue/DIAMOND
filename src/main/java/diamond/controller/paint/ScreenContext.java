/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint;

import java.awt.geom.Point2D;
import java.util.Observable;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.origami.OriFace;
import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class ScreenContext extends Observable {
    public Point2D.Double currentLogicalMousePoint;

    public OriPoint pointedOriPoint = null;
    public OriLine pointedOriLine = null;
    public OriFace pointedOriFace = null;

    public ScreenTransform transform = new ScreenTransform(0,
            0);
    public Palette palette;

    public ScreenContext(Palette palette) {
        this.palette = palette;
    }

    public double getScale() {
        return transform.getScale();
    }

    public Diagram getDiagram() {
        return palette.getDiagram();
    }
}