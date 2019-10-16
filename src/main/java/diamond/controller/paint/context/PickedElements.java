/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.util.Stack;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class PickedElements {

    private Stack<OriPoint> oriPoints = new Stack<>();
    private Stack<OriLine> oriLines = new Stack<>();
    private Stack<OriFace> oriFaces = new Stack<>();

    public PickedElements() {

    }

    public void clear() {
        oriLines.clear();
        oriPoints.clear();
        oriFaces.clear();
    }

    public Stack<OriPoint> getOriPoints() {
        return oriPoints;
    }

    public void setOriPoints(Stack<OriPoint> oriPoints) {
        this.oriPoints = oriPoints;
    }

    public Stack<OriLine> getOriLines() {
        return oriLines;
    }

    public void setOriLines(Stack<OriLine> oriLines) {
        this.oriLines = oriLines;
    }

    public Stack<OriFace> getOriFaces() {
        return oriFaces;
    }

    public void setOriFaces(Stack<OriFace> oriFaces) {
        this.oriFaces = oriFaces;
    }

}
