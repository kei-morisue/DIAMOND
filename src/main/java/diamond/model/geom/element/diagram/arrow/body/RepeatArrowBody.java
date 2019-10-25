/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram.arrow.body;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.util.Vector;

import diamond.model.geom.element.diagram.Diagram;

/**
 * @author long_
 *
 */
public class RepeatArrowBody extends AbstractArrowBody {
    private Diagram d0;
    private Diagram d1;
    private Vector<Diagram> diagrams;

    @Override
    public double getAngle0(Double p0, Double p1) {
        // TODO 自動生成されたメソッド・スタブ
        return .0;
    }

    @Override
    public double getAngle1(Double p0, Double p1) {
        return .0;
    }

    @Override
    public void flip() {
    }

    @Override
    public void draw(Graphics2D g2d, Double p0, Double p1, boolean isSelected) {
        // TODO 自動生成されたメソッド・スタブ

    }

    public Diagram getD0() {
        return d0;
    }

    @Deprecated
    public void setD0(Diagram d0) {
        this.d0 = d0;
    }

    @Deprecated
    public Diagram getD1() {
        return d1;
    }

    public void setD1(Diagram d1) {
        this.d1 = d1;
    }

    @Deprecated
    public Vector<Diagram> getDiagrams() {
        return diagrams;
    }

    public void setDiagrams(Vector<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

}
