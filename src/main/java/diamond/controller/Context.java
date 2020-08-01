/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller;

import diamond.model.cyborg.diagram.Diagram;

/**
 * @author Kei Morisue
 *
 */
public class Context {
    private Diagram diagram;
    //private AbstractPaintAction paintAction;

    @Deprecated
    public Context() {
    }

    public Context(Diagram diagram) {
        this.diagram = diagram;
    }

    public Diagram getDiagram() {
        return diagram;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
}
