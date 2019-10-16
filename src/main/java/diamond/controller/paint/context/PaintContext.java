/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.context;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.Stack;

import diamond.controller.paint.action.Axiom1Action;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class PaintContext extends ScreenContext {

    private LineType inputLineType = LineType.UNSETTLED_VALLEY;

    private Point2D.Double currentLogicalMousePoint = null;

    private OriPoint pointedOriPoint = null;
    private OriLine pointedOriLine = null;
    private OriFace pointedOriFace = null;

    private PickedElements pickedElements = new PickedElements();

    private PaintActionInterface paintAction = new Axiom1Action();

    private File file = null;

    public PaintContext(Palette palette) {
        super(palette);
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
        return pickedElements.getOriLines();
    }

    public Stack<OriPoint> getPickedPoints() {
        return pickedElements.getOriPoints();
    }

    public Stack<OriFace> getPickedOriFaces() {
        return pickedElements.getOriFaces();
    }

    public void popLatestPickedPoint() {
        if (getPickedPoints().isEmpty()) {
            return;
        }
        getPickedPoints().pop();
    }

    public void pushPoint(OriPoint p) {
        getPickedPoints().push(p);
    }

    public void initialize() {
        screen.repaint();
        pickedElements.clear();
        pointedOriLine = null;
        pointedOriPoint = null;
    }

    public PaintActionInterface getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(PaintActionInterface paintAction) {
        this.paintAction = paintAction;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public OriFace getPointedOriFace() {
        return pointedOriFace;
    }

    public void setPointedOriFace(OriFace pointedOriFace) {
        this.pointedOriFace = pointedOriFace;
    }

    public OriLine getPointedOriLine() {
        return pointedOriLine;
    }

    public void setPointedOriLine(OriLine pointedOriLine) {
        this.pointedOriLine = pointedOriLine;
    }

    public OriPoint getPointedOriPoint() {
        return pointedOriPoint;
    }

    public void setPointedOriPoint(OriPoint pointedOriPoint) {
        this.pointedOriPoint = pointedOriPoint;
    }

    public Point2D.Double getCurrentLogicalMousePoint() {
        return currentLogicalMousePoint;
    }

    public void setCurrentLogicalMousePoint(
            Point2D.Double currentLogicalMousePoint) {
        this.currentLogicalMousePoint = currentLogicalMousePoint;
    }

    public LineType getInputLineType() {
        return inputLineType;
    }

    public void setInputLineType(LineType inputLineType) {
        this.inputLineType = inputLineType;
    }

}
