/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.controller.paint.context;

import java.awt.geom.Point2D;
import java.io.File;

import diamond.controller.paint.action.Axiom1Action;
import diamond.controller.paint.action.PaintActionInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriPoint;
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

    private File fileInformation = null;

    public PaintScreenContext(Palette palette) {
    }

    public OriPoint getCandidateOriPoint() {
        OriPoint candidate = pointedElement.getOriPoint();
        if (candidate == null) {
            Point2D.Double mp = currentLogicalMousePoint;
            candidate = new OriPoint(mp.x, mp.y);
        }
        return candidate;
    }

    public PaintActionInterface getPaintAction() {
        return paintAction;
    }

    public void setPaintAction(PaintActionInterface paintAction) {
        this.paintAction = paintAction;
    }

    public File getFileInformation() {
        return fileInformation;
    }

    public void setFileInformation(File file) {
        this.fileInformation = file;
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
    public void initialize() {
        pickedElements.clear();
        pointedElement.clear();
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

}
