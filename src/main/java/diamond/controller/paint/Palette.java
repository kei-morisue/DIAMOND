/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint;

import java.util.LinkedList;

import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.orimodel.OriModel;

/**
 * @author long_
 *
 */
public class Palette {
    private LinkedList<Cp> creasePatterns = new LinkedList<Cp>();
    private int stepNo = 0;
    private OriModel oriModel;

    public Palette() {
        creasePatterns.add(new Cp());
    }

    public OriModel getOriModel() {
        if (oriModel == null) {
            return new OriModel(getCP());
        }
        return this.oriModel;
    }

    public void setOriModel(OriModel oriModel) {
        this.oriModel = oriModel;
    }

    public void setCreasePatterns(LinkedList<Cp> creasePatterns) {
        this.creasePatterns = creasePatterns;
    }

    public Cp getCP() {
        while (stepNo >= creasePatterns.size()) {
            Cp last = creasePatterns.getLast();
            creasePatterns.add(new Cp(last));
        }
        return creasePatterns.get(stepNo);
    }

    public LinkedList<Cp> getCreasePatterns() {
        return this.creasePatterns;
    }

    public int getStepNo() {
        return this.stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }
}
