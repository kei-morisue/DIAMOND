/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint;

import java.util.LinkedList;
import java.util.Observable;

import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.origami.OriModel;

/**
 * @author long_
 *
 */
public class Palette extends Observable {
    private LinkedList<Diagram> diagrams = new LinkedList<Diagram>();
    private int stepNo = 0;

    public Palette() {
        diagrams.add(new Diagram());
    }

    public int size() {
        return diagrams.size();
    }

    public OriModel getOriModel() {
        OriModel oriModel = getCP().getOriModel();
        if (oriModel == null) {
            return new OriModel(getCP());
        }
        return oriModel;
    }

    public void setDiagrams(LinkedList<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

    public Cp getCP() {
        return getDiagram().getCp();
    }

    public Diagram getDiagram() {
        while (stepNo >= diagrams.size()) {
            Diagram last = diagrams.getLast();
            diagrams.add(new Diagram(last));
            setChanged();
            notifyObservers();
        }
        return diagrams.get(stepNo);
    }

    public void insertCp() {
        diagrams.add(stepNo + 1, new Diagram(getDiagram()));
        stepNo += 1;
    }

    public LinkedList<Diagram> getDiagrams() {
        return this.diagrams;
    }

    public void remove(Diagram diagram) {
        int index = diagrams.indexOf(diagram);
        if (index == diagrams.size() - 1) {
            stepNo -= 1;
        }
        if (index != -1) {
            diagrams.remove(diagram);
        }
    }

    public int getStepNo() {
        return this.stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }
}
