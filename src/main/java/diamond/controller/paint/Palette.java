/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint;

import java.util.LinkedList;
import java.util.Observable;

import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.origami.OriModel;

/**
 * @author long_
 *
 */
public class Palette extends Observable {
    private LinkedList<Cp> creasePatterns = new LinkedList<Cp>();
    private int stepNo = 0;

    public Palette() {
        creasePatterns.add(new Cp());
    }

    public int size() {
        return creasePatterns.size();
    }

    public OriModel getOriModel() {
        OriModel oriModel = getCP().getOriModel();
        if (oriModel == null) {
            return new OriModel(getCP());
        }
        return oriModel;
    }

    public void setCreasePatterns(LinkedList<Cp> creasePatterns) {
        this.creasePatterns = creasePatterns;
    }

    public Cp getCP() {
        while (stepNo >= creasePatterns.size()) {
            Cp last = creasePatterns.getLast();
            creasePatterns.add(new Cp(last));
            setChanged();
            notifyObservers();
        }
        return creasePatterns.get(stepNo);
    }

    public LinkedList<Cp> getCreasePatterns() {
        return this.creasePatterns;
    }

    public void remove(Cp cp) {
        int index = creasePatterns.indexOf(cp);
        if (index == creasePatterns.size() - 1) {
            stepNo -= 1;
        }
        if (index != -1) {
            creasePatterns.remove(cp);
        }
    }

    public int getStepNo() {
        return this.stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
        setChanged();
        notifyObservers();

    }
}
