/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint;

import java.awt.geom.Point2D;
import java.io.File;
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

    private LineType inputLineType = LineType.UNSETTLED_VALLEY;

    private Point2D.Double currentLogicalMousePoint = null;

    private OriPoint pointedOriPoint = null;
    private OriLine pointedOriLine = null;
    private OriFace pointedOriFace = null;

    private Stack<OriPoint> pickedOriPoints = new Stack<>();
    private Stack<OriLine> pickedOriLines = new Stack<>();
    private Stack<OriFace> pickedOriFaces = new Stack<>();
    private Stack<OriVertex> pickedOriVertices = new Stack<>();

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
