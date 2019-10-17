/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import diamond.model.geom.element.cp.Cp;
import diamond.view.screen.ScreenTransform;

/**
 * @author long_
 *
 */
public class Diagram {
    private Cp cp = new Cp();
    private ScreenTransform transform = new ScreenTransform(0, 0);
    private Clipper clipper;

    public Diagram() {
    }

    public Diagram(Diagram diagram) {
        this.cp = new Cp(diagram.cp);
        this.transform = new ScreenTransform(diagram.transform);
        this.clipper = new Clipper(diagram.clipper);
    }

    public Clipper getClipper() {
        return this.clipper;
    }

    public void setClipper(Clipper clipper) {
        this.clipper = clipper;
    }

    public ScreenTransform getTransform() {
        return transform;
    }

    public void setTransform(ScreenTransform transform) {
        this.transform = transform;
    }

    public Cp getCp() {
        return cp;
    }

    public void setCp(Cp cp) {
        this.cp = cp;
    }

}
