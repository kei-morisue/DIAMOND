/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Stack;

import diamond.controller.paint.action.Axiom1Action;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.CreasePattern;
import diamond.view.paint.screen.coordinate.CoodinateTransform;

/**
 * @author long_
 *
 */
public class PaintContext extends Observable {
    public Point2D.Double currentLogicalMousePoint;

    public OriPoint pointedOriPoint = null;
    public OriLine pointedOriLine = null;

    public LineType inputLineType = LineType.MOUNTAIN;

    private Stack<OriPoint> pickedOriPoints = new Stack<>();
    private Stack<OriLine> pickedOriLines = new Stack<>();

    public boolean isFinished;

    private LinkedList<CreasePattern> creasePatterns = new LinkedList<CreasePattern>();
    private int stepNo = 0;

    public CoodinateTransform coordinateTransform = new CoodinateTransform(0,
            0);

    public PaintActionInterface paintAction = new Axiom1Action();

    public PaintContext() {
        creasePatterns.add(new CreasePattern());
    }

    public OriPoint getCandidateOriPoint(boolean enableFreePoint) {
        OriPoint candidate = pointedOriPoint;

        if (candidate == null && enableFreePoint) {
            Point2D.Double mp = currentLogicalMousePoint;
            candidate = new OriPoint(mp.x, mp.y);
        }

        return candidate;
    }

    public CreasePattern getCP() {
        return creasePatterns.get(stepNo);
    }

    public double getScale() {
        return coordinateTransform.getScale();
    }

    public Stack<OriLine> getPickedLines() {
        return pickedOriLines;
    }

    public Stack<OriPoint> getPickedPoints() {
        return pickedOriPoints;
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
        pickedOriLines.clear();
        pickedOriPoints.clear();

        pointedOriLine = null;
        pointedOriPoint = null;

        isFinished = false;
    }

}
