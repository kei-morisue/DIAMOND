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
}
