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
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
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
    public LineType memorizedLineType = LineType.AUX;

    private Stack<OriPoint> pickedOriPoints = new Stack<>();
    private Stack<OriLine> pickedOriLines = new Stack<>();

    public boolean isFinished;

    private LinkedList<Cp> creasePatterns = new LinkedList<Cp>();
    private int stepNo = 0;

    public CoodinateTransform coordinateTransform = new CoodinateTransform(0,
            0);

    public PaintActionInterface paintAction = new Axiom1Action();

    public void setCreasePatterns(LinkedList<Cp> creasePatterns) {
        this.creasePatterns = creasePatterns;
    }

    public PaintContext() {
        creasePatterns.add(new Cp());
    }

    public OriPoint getCandidateOriPoint(boolean enableFreePoint) {
        OriPoint candidate = pointedOriPoint;

        if (candidate == null && enableFreePoint) {
            Point2D.Double mp = currentLogicalMousePoint;
            candidate = new OriPoint(mp.x, mp.y);
        }

        return candidate;
    }

    public Cp getCP() {
        while (stepNo >= creasePatterns.size()) {
            Cp last = creasePatterns.getLast();
            creasePatterns.add(new Cp(last));
            notifyObservers();
        }
        return creasePatterns.get(stepNo);
    }

    public LinkedList<Cp> getCreasePatterns() {
        return this.creasePatterns;
    }

    public int getStepNo() {
        return this.stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
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
