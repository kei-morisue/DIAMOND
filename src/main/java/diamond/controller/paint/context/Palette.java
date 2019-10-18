/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.util.Observable;
import java.util.Vector;

import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.diagram.Diagram;
import diamond.model.geom.element.origami.OriModel;
import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class Palette extends Observable {
    private Vector<Diagram> diagrams = new Vector<Diagram>();
    private int stepNo = 0;// TODO move to Context

    public Palette() {
        diagrams.add(new Diagram());
    }

    public OriModel getOriModel() {
        OriModel oriModel = getCP().getOriModel();
        if (oriModel == null) {
            return new OriModel(getCP());
        }
        return oriModel;
    }

    public void setDiagrams(Vector<Diagram> diagrams) {
        this.diagrams = diagrams;
    }

    public Cp getCP() {
        return getDiagram().getCp();
    }

    public Diagram getDiagram() {
        while (stepNo >= diagrams.size()) {
            Diagram last = diagrams.lastElement();
            diagrams.add(new Diagram(last));
        }
        return diagrams.get(stepNo);
    }

    public void insertCp() {
        diagrams.add(stepNo + 1, new Diagram(getDiagram()));
        stepNo += 1;
    }

    public Vector<Diagram> getDiagrams() {
        return this.diagrams;
    }

    public void removeCp(Diagram diagram) {
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

    public void setStepNo(int stepNo, Context context) {
        ModelScreenContext modelScreenContext = context.getModelScreenContext();
        ScreenTransform transform = modelScreenContext.getTransform();
        Diagram diagram = context.getPalette().getDiagram();
        diagram.setTransform(transform);
        this.stepNo = stepNo;
        diagram = getDiagram();
        transform = diagram.getTransform();
        modelScreenContext.setTransform(transform);

    }
}
