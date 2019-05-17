/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.head;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.diagram.arrow.body.AbstractArrowBody;

/**
 * @author long_
 *
 */
public abstract class AbstractArrowHead {
    private AbstractArrowBody body;
    private boolean isTail;

    protected AbstractArrowHead(AbstractArrowBody body, boolean isTail) {
        this.body = body;
        this.isTail = isTail;
    }

    protected Double getPosition(Double p0, Double p1) {
        if (isTail) {
            return body.getP0(p0, p1);
        }
        return body.getP1(p0, p1);
    }

    protected double getAngle(Double p0, Double p1) {
        if (isTail) {
            return body.getAngle0(p0, p1);
        }
        return body.getAngle1(p0, p1);
    }

    public void flip() {
        isTail = !isTail;
    }

    public abstract void draw(Graphics2D g2d, Double p0, Double p1,
            boolean isSelected);

    public boolean isTail() {
        return this.isTail;
    }

    @Deprecated
    public AbstractArrowBody getBody() {
        return this.body;
    }

    @Deprecated
    public void setBody(AbstractArrowBody body) {
        this.body = body;
    }

    @Deprecated
    public void setTail(boolean isTail) {
        this.isTail = isTail;
    }

}
