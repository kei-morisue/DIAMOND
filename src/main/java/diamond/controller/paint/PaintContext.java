/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint;

import java.awt.geom.Point2D;
import java.util.Stack;

import diamond.controller.paint.action.Axiom1Action;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class PaintContext extends ScreenContext {

    public LineType inputLineType = LineType.UNSETTLED_VALLEY;

    private Stack<OriPoint> pickedOriPoints = new Stack<>();
    private Stack<OriLine> pickedOriLines = new Stack<>();
    private Stack<OriFace> pickedOriFaces = new Stack<>();
    private Stack<OriVertex> pickedOriVertices = new Stack<>();

    public PaintActionInterface paintAction = new Axiom1Action();
    public Palette palette;

    public PaintContext(Palette palette) {
        this.palette = palette;
    }

    public OriPoint getCandidateOriPoint(boolean enableFreePoint) {
        OriPoint candidate = pointedOriPoint;

        if (candidate == null && enableFreePoint) {
            Point2D.Double mp = currentLogicalMousePoint;
            candidate = new OriPoint(mp.x, mp.y);
        }

        return candidate;
    }

    public Stack<OriLine> getPickedLines() {
        return pickedOriLines;
    }

    public Stack<OriPoint> getPickedPoints() {
        return pickedOriPoints;
    }

    public Stack<OriFace> getPickedOriFaces() {
        return this.pickedOriFaces;
    }

    public void popLatestPickedPoint() {
        if (pickedOriPoints.isEmpty()) {
            return;
        }
        pickedOriPoints.pop();
    }

    public void pushPoint(OriPoint p) {
        pickedOriPoints.push(p);
    }

    public void initialize() {
        screen.repaint();
        pickedOriLines.clear();
        pickedOriPoints.clear();
        pickedOriFaces.clear();
        pointedOriLine = null;
        pointedOriPoint = null;
    }

    public Stack<OriVertex> getPickedOriVertices() {
        return pickedOriVertices;
    }

}
