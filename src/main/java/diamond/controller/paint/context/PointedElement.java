/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class PointedElement {

    private OriPoint oriPoint = null;
    private OriLine oriLine = null;
    private OriFace oriFace = null;

    public PointedElement() {

    }

    public void clear() {
        oriLine = null;
        oriPoint = null;
        oriFace = null;
    }

    public OriPoint getOriPoint() {
        return oriPoint;
    }

    public void setOriPoint(OriPoint oriPoint) {
        this.oriPoint = oriPoint;
    }

    public OriLine getOriLine() {
        return oriLine;
    }

    public void setOriLine(OriLine oriLine) {
        this.oriLine = oriLine;
    }

    public OriFace getOriFace() {
        return oriFace;
    }

    public void setOriFace(OriFace oriFace) {
        this.oriFace = oriFace;
    }

}
