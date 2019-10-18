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
import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class PaintScreenContext implements ScreenContext {

    private LineType inputLineType = LineType.UNSETTLED_VALLEY;
    private Point2D.Double currentLogicalMousePoint = null;
    private PointedElement pointedElement = new PointedElement();
    private PickedElements pickedElements = new PickedElements();
    private PaintActionInterface paintAction = new Axiom1Action();
    protected ScreenTransform transform = new ScreenTransform();

    private File file = null;

    public PaintScreenContext(Palette palette) {
    }

    public OriPoint getCandidateOriPoint(boolean enableFreePoint) {
        OriPoint candidate = pointedElement.getOriPoint();

        if (candidate == null && enableFreePoint) {
            Point2D.Double mp = currentLogicalMousePoint;
            candidate = new OriPoint(mp.x, mp.y);
        }

        return candidate;
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

    @Override
    public void initialize() {
        pickedElements.clear();
        pointedElement.clear();
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

    @Override
    public ScreenTransform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }

    public PickedElements getPickedElements() {
        return pickedElements;
    }

    public PointedElement getPointedElements() {
        return pointedElement;
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

    public OriFace getPointedOriFace() {
        return pointedElement.getOriFace();
    }

    public OriLine getPointedOriLine() {
        return pointedElement.getOriLine();
    }

    public OriPoint getPointedOriPoint() {
        return pointedElement.getOriPoint();
    }

    public void setPointedOriLine(OriLine oriLine) {
        pointedElement.setOriLine(oriLine);
    }

    public void setPointedOriPoint(OriPoint oriPoint) {
        pointedElement.setOriPoint(oriPoint);
    }

    public void setPointedOriFace(OriFace oriFace) {
        pointedElement.setOriFace(oriFace);
    }

}
