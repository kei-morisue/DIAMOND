/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint;

import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

import diamond.action.GraphicMouseAction;

/**
 * @author long_
 *
 */
public class PaintContext {
    private boolean isVisible;
    private Point2D.Double logicalMousePoint;
    public GraphicMouseAction mouseAction;

    public void setLogicalMousePoint(Point2D.Double logicalMousePoint) {
        this.logicalMousePoint = logicalMousePoint;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Point2D.Double getMousePoint() {
        return this.logicalMousePoint;
    }

    public GraphicMouseAction getMouseAction() {
        return this.mouseAction;
    }

    public void setMouseAction(GraphicMouseAction mouseAction) {
        this.mouseAction = mouseAction;
    }

    public Vector2d pickCandidateVertex() {
        Vector2d candidateVertex = new Vector2d(0, 0);
        return candidateVertex;
    }
}
