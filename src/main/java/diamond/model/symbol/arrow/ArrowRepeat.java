/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.symbol.arrow;

import java.util.Vector;

import diamond.model.cyborg.Cp;
import diamond.model.symbol.arrow.body.Repeat;
import diamond.model.symbol.arrow.head.Valley;

/**
 * @author Kei Morisue
 *
 */
public class ArrowRepeat extends AbstractArrow {
    private Cp cp0;
    private Cp cp1;
    private Vector<Cp> cps;

    public ArrowRepeat(Cp cp0, Cp cp1, Vector<Cp> cps) {
        this.cp0 = cp0;
        this.cp1 = cp1;
        this.cps = cps;
        buildBody();
        buildTail();
        arrowTail.setTail(true);
        buildHead();
        setScale(0.5);
        flip(null);//TODO
    }

    @Override
    protected void buildHead() {
        arrowHead = new Valley();
    }

    @Override
    protected void buildTail() {
        arrowTail = new diamond.model.symbol.arrow.head.Repeat(cp0, cp1, cps);
    }

    @Override
    protected void buildBody() {
        body = new Repeat();
    }

    @Deprecated
    public Cp getCp0() {
        return cp0;
    }

    @Deprecated
    public void setCp0(Cp cp0) {
        this.cp0 = cp0;
    }

    @Deprecated
    public Cp getCp1() {
        return cp1;
    }

    @Deprecated
    public void setCp1(Cp cp1) {
        this.cp1 = cp1;
    }

    @Deprecated
    public Vector<Cp> getCps() {
        return cps;
    }

    @Deprecated
    public void setCps(Vector<Cp> cps) {
        this.cps = cps;
    }
}
